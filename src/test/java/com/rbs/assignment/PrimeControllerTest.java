package com.rbs.assignment;

import com.rbs.assignment.model.ErrorResponse;
import com.rbs.assignment.model.PrimeNumberResponse;
import com.rbs.assignment.util.PrimeNumberUtilTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.rbs.assignment.exception.GenericErrorCode.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Joydeep Paul
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate;
    private String baseUrl = "http://localhost:";
    private ResponseEntity<PrimeNumberResponse> responseEntity;
    private List<Integer> expectedPrimeNumbers;
    private HttpHeaders httpHeaders;

    /**
     * Test prime number generation based on {upperBound}, negative value,
     * {@link PrimeNumberUtilTest#KNOWN_PRIMES} and 3 different algorithms
     */

    @Before
    public void setUp() {
        httpHeaders = new HttpHeaders();
        restTemplate = new TestRestTemplate();
        expectedPrimeNumbers = Arrays.asList(2, 3, 5, 7);
    }

    @Test
    public void testPrimeGenerationWithDefaultAlgorithm() {
        // Test default algorithm
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);

    }

    @Test
    public void testPrimeGenerationWithXmlPathExtension() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10.xml"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals("application/xml;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }


    @Test
    public void testPrimeGenerationWithSieve() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=sieve"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, expectedPrimeNumbers);
    }

    @Test
    public void testPrimeGenerationWithSlowALoop() {
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=slow"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals("application/xml;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }


    @Test
    // Test PrimeNumberUtilTest.KNOWN_PRIMES
    public void testPrimeGenerationWithKnownSet() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/5000"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, PrimeNumberUtilTest.KNOWN_PRIMES);
    }


    @Test
    public void testPrimeGenerationWithNegativeUpperBound() {
        // Test negative upper bound
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/-5000"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, null);

    }

    @Test
    public void testPrimeGenerationWithJsonPathExtension() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10.json"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }

    @Test
    public void testPrimeGenerationWithBadResourceFormat() {
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(createUrlWithPort("/primes/abc"), HttpMethod.GET, httpEntity, ErrorResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void testPrimeGenerationWithHighUpperBound() {
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(createUrlWithPort("/primes/100000000"), HttpMethod.GET, httpEntity, ErrorResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        assertEquals(GEN_002.getDefaultMessage(), responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void testPrimeGenerationWithHighUpperBoundForSlowLoop() {
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10000000?algorithm=slow"), HttpMethod.GET, httpEntity, ErrorResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        assertEquals(GEN_003.getDefaultMessage(), responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    private void checkBody(ResponseEntity<PrimeNumberResponse> responseEntity, List<Integer> expectedPrimeNumbers) {
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final PrimeNumberResponse response = responseEntity.getBody();
        List<Integer> primes = response.getPrimes();
        assertEquals(expectedPrimeNumbers, primes);
    }

    private String createUrlWithPort(String uri) {
        return baseUrl + port + "/v1" + uri;

    }
}

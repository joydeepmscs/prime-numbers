package com.rbs.assignment;

import com.rbs.assignment.model.PrimeNumberResponse;
import com.rbs.assignment.util.PrimeNumberUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Joydeep Paul
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeControllerTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    private String baseUrl = "http://localhost:";
    ResponseEntity<PrimeNumberResponse> responseEntity;
    private static List<Integer> expectedPrimeNumbers = Arrays.asList(2, 3, 5, 7);


    @Test
    public void testWithXmlPathExtension() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10.xml"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals("application/xml;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }


    @Test
    public void test2() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=fast"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, expectedPrimeNumbers);
    }

    @Test
    public void testWithSlowAlgorithm() {

        httpHeaders.clear();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=slow"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals("application/xml;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }


    @Test
    public void test4() {
        // Test PrimeNumberUtilTest.KNOWN_PRIMES
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/5000"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, PrimeNumberUtilTest.KNOWN_PRIMES);
    }


    @Test
    public void test5() {
        // Test negative upper bound
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/-5000"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        checkBody(responseEntity, null);

    }

    @Test
    public void test6() {
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10.json"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);
    }

    /**
     * Test prime number generation based on {upperBound}, negative value,
     * {@link PrimeNumberUtilTest#KNOWN_PRIMES} and 3 different algorithms
     */
    @Test
    public void testPrimeGeneration() {
        // Test default upper bound
        httpHeaders.clear();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10"), HttpMethod.GET, httpEntity, PrimeNumberResponse.class);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);

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

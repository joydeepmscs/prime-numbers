package com.rbs.assignment;

import com.rbs.assignment.util.PrimeNumberUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertArrayEquals;
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
    HttpHeaders httpHeaders=new HttpHeaders();
    private String baseUrl = "http://localhost:";

    /**
     * Test prime number generation based on {upperBound}, negative value,
     * {@link PrimeNumberUtilTest#KNOWN_PRIMES} and 3 different algorithms
     */
    @Test
    public void testPrimeGeneration() {

        // Test default upper bound
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity =new HttpEntity(null,httpHeaders );
        ResponseEntity<int[]> responseEntity=    restTemplate.exchange(createUrlWithPort("/primes/10"), HttpMethod.GET,httpEntity, int[].class);
        int[] expectedPrimeNumbers = {2, 3, 5, 7};
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(),responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);

        responseEntity=    restTemplate.exchange(createUrlWithPort("/primes/10.xml"), HttpMethod.GET,httpEntity, int[].class);
        assertEquals("application/xml;charset=UTF-8",responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);


        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=1"), HttpMethod.GET,httpEntity, int[].class);
        checkBody(responseEntity, expectedPrimeNumbers);

        httpHeaders.clear();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        httpEntity =new HttpEntity(null,httpHeaders );
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/10/?algorithm=2"), HttpMethod.GET,httpEntity, int[].class);
        System.out.println("expected::"+MediaType.APPLICATION_XML_VALUE);
        assertEquals("application/xml;charset=UTF-8",responseEntity.getHeaders().getContentType().toString());
        checkBody(responseEntity, expectedPrimeNumbers);

        // Test negative upper bound
        responseEntity = restTemplate.exchange(createUrlWithPort("/primes/-5000"), HttpMethod.GET,httpEntity, int[].class);
        expectedPrimeNumbers = new int[]{};
        checkBody(responseEntity, expectedPrimeNumbers);

        // Test PrimeNumberUtilTest.KNOWN_PRIMES
        responseEntity=restTemplate.exchange(createUrlWithPort("/primes/5000"), HttpMethod.GET,httpEntity, int[].class);
        expectedPrimeNumbers = PrimeNumberUtilTest.KNOWN_PRIMES;
        checkBody(responseEntity, expectedPrimeNumbers);
    }

    private void checkBody(ResponseEntity<int[]> responseEntity, int[] expectedPrimeNumbers) {
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final int[] actual = responseEntity.getBody();
        assertEquals(expectedPrimeNumbers.length, actual.length);
        assertArrayEquals(expectedPrimeNumbers, actual);
    }
    private String createUrlWithPort(String uri){
        return baseUrl+ port +uri;

    }
}

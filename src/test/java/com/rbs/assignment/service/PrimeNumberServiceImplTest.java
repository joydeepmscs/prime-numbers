package com.rbs.assignment.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.rbs.assignment.util.PrimeNumberUtilTest.KNOWN_PRIMES;
import static org.junit.Assert.assertEquals;

/**
 * @author Joydeep Paul
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberServiceImplTest {

    @Autowired
    private PrimeNumberService service;

    @Test
    public void testPrimeGenerator() {

        final List<Integer> primes = service.getPrimes(5000);
        assertEquals("'primes' and 'KNOWN_PRIMES' should be equal in size", KNOWN_PRIMES.length, primes.size());

        for (int i = 0; i < KNOWN_PRIMES.length; i++) {
            assertEquals("'primes.get(i)' should equal 'KNOWN_PRIMES[i]'", KNOWN_PRIMES[i], primes.get(i).intValue());
        }
    }
}

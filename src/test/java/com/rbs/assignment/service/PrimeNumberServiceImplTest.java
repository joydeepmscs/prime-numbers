package com.rbs.assignment.service;

import com.rbs.assignment.exception.PrimeNumberProcessingException;
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
@SpringBootTest()
public class PrimeNumberServiceImplTest {

    @Autowired
    private PrimeNumberService service;

    @Test
    public void testPrimeGeneratorUsingFastLoop() throws PrimeNumberProcessingException {

        final List<Integer> primes = service.getPrimesUsingFastLoop(5000);
        assertEquals("'primes' and 'KNOWN_PRIMES' should be equal in size", KNOWN_PRIMES.size(), primes.size());

        for (int i = 0; i < KNOWN_PRIMES.size(); i++) {
            assertEquals("'primes.get(i)' should equal 'KNOWN_PRIMES[i]'", KNOWN_PRIMES.get(i), primes.get(i));
        }
    }

    @Test
    public void testPrimeGeneratorUsingSlowLoop() throws PrimeNumberProcessingException {

        final List<Integer> primes = service.getPrimesUsingSlowLoop(5000);
        assertEquals("'primes' and 'KNOWN_PRIMES' should be equal in size", KNOWN_PRIMES.size(), primes.size());

        for (int i = 0; i < KNOWN_PRIMES.size(); i++) {
            assertEquals("'primes.get(i)' should equal 'KNOWN_PRIMES[i]'", KNOWN_PRIMES.get(i), primes.get(i));
        }
    }

    @Test
    public void testPrimeGeneratorUsingSieve() throws PrimeNumberProcessingException {

        final List<Integer> primes = service.getPrimesUsingSieve(5000);
        assertEquals("'primes' and 'KNOWN_PRIMES' should be equal in size", KNOWN_PRIMES.size(), primes.size());

        for (int i = 0; i < KNOWN_PRIMES.size(); i++) {
            assertEquals("'primes.get(i)' should equal 'KNOWN_PRIMES[i]'", KNOWN_PRIMES.get(i), primes.get(i));
        }
    }

    @Test
    public void testPrimeGeneratorValueLessThanTwo() throws PrimeNumberProcessingException {

        final List<Integer> primes1 = service.getPrimesUsingFastLoop(1);
        final List<Integer> primes2 = service.getPrimesUsingSlowLoop(-100);
        final List<Integer> primes3 = service.getPrimesUsingSieve(1);
        assertEquals(primes1, primes2);
        assertEquals(primes1, primes3);

    }

    @Test(expected = PrimeNumberProcessingException.class)
    public void testPrimeGeneratorForHighUpperBound() throws PrimeNumberProcessingException {

        final List<Integer> primes = service.getPrimesUsingFastLoop(100000000);

    }
}

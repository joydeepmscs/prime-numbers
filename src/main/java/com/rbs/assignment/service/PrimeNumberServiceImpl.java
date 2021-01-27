package com.rbs.assignment.service;

import com.rbs.assignment.exception.PrimeNumberProcessingException;
import com.rbs.assignment.util.PrimeNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rbs.assignment.exception.GenericErrorCode.*;

/**
 * @author Joydeep Paul
 */
@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {
    private final Logger log = LoggerFactory.getLogger(PrimeNumberServiceImpl.class);

    @Override
    public boolean isPrimeSlowLoop(int n) {
        return PrimeNumberUtil.isPrimeBySlowLoop(n);
    }


    @Override
    public boolean isPrimeFastLoop(int n) {
        return PrimeNumberUtil.isPrimeByFastLoop(n);
    }

    @Override
    @Cacheable(value = "primeNumberCache", key="#upperBound")
    public List<Integer> getPrimesUsingSlowLoop(int upperBound) throws PrimeNumberProcessingException {
        log.debug("****** Inside getPrimesUsingSlowLoop ******");
        List<Integer> primeNumbers;
        try {
            if (upperBound < 2) {
                log.info("The number is less than 2");
                return Collections.emptyList();

            }
            UpperBoundValidation(upperBound, "slow");

            // using Java 8 Stream API and multi-threading using parallel.
            primeNumbers = IntStream.rangeClosed(2, upperBound).parallel()
                    .filter(this::isPrimeSlowLoop)
                    .boxed()
                    .collect(Collectors.toList());


        } catch (PrimeNumberProcessingException e) {
            throw new PrimeNumberProcessingException(e.getCode(), e.getErrorMessage());
        } catch (Exception e) {
            log.error("exception occurred while generating prime numbers :: {}", e.getMessage());
            throw new PrimeNumberProcessingException(GEN_001.getCode(), GEN_001.getDefaultMessage());
        }
        return primeNumbers;
    }

    @Override
    @Cacheable(value = "primeNumberCache", key="#upperBound")
    public List<Integer> getPrimesUsingSieve(int upperBound) throws PrimeNumberProcessingException {
        log.debug("****** Inside getPrimesUsingSieve ******");
        List<Integer> primeNumbers;
        try {
            if (upperBound < 2) {
                log.info("The number is less than 2");
                return Collections.emptyList();
            }
            UpperBoundValidation(upperBound, "sieve");

            boolean[] sieve = PrimeNumberUtil.createSieve(upperBound);

            // using Java 8 Stream API and multi-threading using parallel.
            primeNumbers = IntStream.rangeClosed(2, upperBound).parallel()
                    .filter(n -> sieve[n])
                    .boxed()
                    .collect(Collectors.toList());


        } catch (PrimeNumberProcessingException e) {
            throw new PrimeNumberProcessingException(e.getCode(), e.getErrorMessage());
        } catch (Exception e) {
            log.error("exception occurred while generating prime numbers :: {}", e.getMessage());
            throw new PrimeNumberProcessingException(GEN_001.getCode(), GEN_001.getDefaultMessage());
        }
        return primeNumbers;
    }

    @Override
    @Cacheable(value = "primeNumberCache", key="#upperBound")
    public List<Integer> getPrimesUsingFastLoop(int upperBound) throws PrimeNumberProcessingException {
        log.debug("****** Inside getPrimesUsingFastLoop ******");
        List<Integer> primeNumbers;
        try {
            if (upperBound < 2) {
                log.info("The number is less than 2");
                return Collections.emptyList();
            }

            UpperBoundValidation(upperBound, "fast");

            // using Java 8 Stream API and multi-threading using parallel.
            primeNumbers = IntStream.rangeClosed(2, upperBound).parallel()
                    .filter(this::isPrimeFastLoop)
                    .boxed()
                    .collect(Collectors.toList());


        } catch (PrimeNumberProcessingException e) {
            throw new PrimeNumberProcessingException(e.getCode(), e.getErrorMessage());
        } catch (Exception e) {
            log.error("exception occurred while generating prime numbers :: {}", e.getMessage());
            throw new PrimeNumberProcessingException(GEN_001.getCode(), GEN_001.getDefaultMessage());
        }

        return primeNumbers;
    }

    private void UpperBoundValidation(int n, String algorithm) throws PrimeNumberProcessingException {
        if (n > 1000000 & algorithm.equals("slow")) {
            log.warn("upper bound greater than 10^6 is not accepted for slow algorithm. Value entered: {} ", n);
            throw new PrimeNumberProcessingException(GEN_003.getCode(), GEN_003.getDefaultMessage());
        }
        if (n > 10000000) {
            log.warn("upper bound greater than 10^7 is not accepted. Value entered: {} ", n);
            throw new PrimeNumberProcessingException(GEN_002.getCode(), GEN_002.getDefaultMessage());
        }
    }
}

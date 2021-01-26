package com.rbs.assignment.service;

import com.rbs.assignment.util.PrimeNumberUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Joydeep Paul
 */
@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Override
    public boolean isPrimeSlow(int n) {
        return PrimeNumberUtil.isPrimeBySlow(n);
    }


    @Override
    public boolean isPrimeFastLoop(int n) {
        return PrimeNumberUtil.isPrimeByFast(n);
    }

    @Override
    public List<Integer> getPrimes(int upperBound) {
        if (upperBound < 2) {
            return Collections.emptyList();
        }

        // Naive example using Java 8 Stream API and multi-threading using parallel.
        // Could have more thread control using ForkJoinPool directly
        return IntStream.rangeClosed(2, upperBound).parallel()
                .filter(this::isPrimeSlow)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getPrimesUsingSieve(int upperBound) {
        return PrimeNumberUtil.isPrimeBySieve(upperBound);
    }

    @Override
    public List<Integer> getPrimesUsingFastLoop(int upperBound) {
        if (upperBound < 2) {
            return Collections.emptyList();
        }

        // Naive example using Java 8 Stream API and multi-threading using parallel.
        return IntStream.rangeClosed(2, upperBound).parallel()
                .filter(this::isPrimeFastLoop)
                .boxed()
                .collect(Collectors.toList());
    }
}

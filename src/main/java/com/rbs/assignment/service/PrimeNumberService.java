package com.rbs.assignment.service;

import com.rbs.assignment.util.PrimeNumberUtil;

import java.util.List;

/**
 * Service for functions related to prime-number calculation
 *
 * @author Joydeep Paul
 */
public interface PrimeNumberService {

    /**
     * @see PrimeNumberUtil#isPrimeBySlow(int)
     */
    boolean isPrimeSlow(int n);

    /**
     * @see PrimeNumberUtil#isPrimeByFast(int)
     */
    boolean isPrimeFastLoop(int n);

    /**
     * Returns a {@link List} of prime numbers given an (inclusive) upper bound
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @return a {@link List} of prime numbers given an (inclusive) upper bound
     */
    List<Integer> getPrimes(int upperBound);
    List<Integer> getPrimesUsingSieve(int upperBound);
    List<Integer> getPrimesUsingFastLoop(int upperBound);
}

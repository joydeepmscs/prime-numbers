package com.rbs.assignment.service;

import com.rbs.assignment.exception.PrimeNumberProcessingException;
import com.rbs.assignment.util.PrimeNumberUtil;

import java.util.List;

/**
 * Service for functions related to prime-number calculation
 *
 * @author Joydeep Paul
 */
public interface PrimeNumberService {

    /**
     * @see PrimeNumberUtil#isPrimeBySlowLoop(int)
     */
    boolean isPrimeSlowLoop(int n);

    /**
     * @see PrimeNumberUtil#isPrimeByFastLoop(int)
     */
    boolean isPrimeFastLoop(int n);

    /**
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @return a {@link List} of prime numbers given an (inclusive) upper bound
     * @see PrimeNumberUtil#isPrimeBySlowLoop(int)
     */
    List<Integer> getPrimesUsingSlowLoop(int upperBound) throws PrimeNumberProcessingException;

    /**
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @return a {@link List} of prime numbers given an (inclusive) upper bound
     * @see PrimeNumberUtil#isPrimeBySieve(int)
     */

    List<Integer> getPrimesUsingSieve(int upperBound) throws PrimeNumberProcessingException;

    /**
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @return a {@link List} of prime numbers given an (inclusive) upper bound
     * @see PrimeNumberUtil#isPrimeByFastLoop(int)
     */
    List<Integer> getPrimesUsingFastLoop(int upperBound) throws PrimeNumberProcessingException;
}

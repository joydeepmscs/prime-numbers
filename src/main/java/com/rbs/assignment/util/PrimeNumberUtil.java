package com.rbs.assignment.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for prime number calculation
 *
 * @author Joydeep Paul
 */
public class PrimeNumberUtil {

    /**
     * A method to determine if {@code n} is a prime number using sqrt n iteration.
     *
     * @param n The number which shall be determined to be prime or non-prime
     * @return {@code true} if {@code n} is prime, otherwise {@code false}
     */
    public static boolean isPrimeByFastLoop(int n) {
        if (n <= 1) {
            // numbers less than 2 are not considered prime
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method to determine if {@code n} is a prime number using n iteration.
     *
     * @param n The number which shall be determined to be prime or non-prime
     * @return {@code true} if {@code n} is prime, otherwise {@code false}
     */
    public static boolean isPrimeBySlowLoop(int n) {

        if (n <= 1) {
            // numbers less than 2 are not considered prime
            return false;
        }

        //
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns list of prime number for a given range using sieve of eratosthenes
     *
     * @param n The inclusive upper bound to limit the size of the returned primes
     * @return a {@link List} of prime numbers given an (inclusive) upper bound
     */
    public static List<Integer> isPrimeBySieve(int n) {
        final List<Integer> primeNumbers = new ArrayList<>();
        if (n <= 1) {
            return primeNumbers;
        }

        boolean sieve[] = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            sieve[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i) { // making 2p, 3p, ... false
                    sieve[j] = false;
                }

            }
        }
        // add all prime numbers to list
        for (int i = 2; i <= n; i++) {
            if (sieve[i] == true)
                primeNumbers.add(i);
        }
        return primeNumbers;
    }

}

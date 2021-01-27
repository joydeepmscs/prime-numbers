package com.rbs.assignment.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class for prime number calculation
 *
 * @author Joydeep Paul
 */
public class PrimeNumberUtil {

    /**
     * creates a sieve which contains boolean prime and non-prime numbers.
     *
     * @param n
     * @return
     */
    public static boolean[] createSieve(int n) {
        boolean sieve[] = new boolean[n + 1];
        Arrays.fill(sieve, 2, sieve.length, true);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i) { // making 2p, 3p, ... false
                    sieve[j] = false;
                }

            }
        }
        return sieve;
    }

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

}

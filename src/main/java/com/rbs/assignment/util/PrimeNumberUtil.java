package com.rbs.assignment.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for prime number calculation
 *
 * @author Joydeep Paul
 */
public class PrimeNumberUtil {

    static final boolean[] sieve = new boolean[200];

    static {
        Arrays.fill(sieve, 2, sieve.length, true);

        for (int i = 2; i<=Math.sqrt(sieve.length); i++) {
            if (sieve[i]) {
                for (int j = i*i; j < sieve.length; j+=i) {       // making 2p, 3p, ... false
                    sieve[j] = false;
                }

            }
        }
    }

    /**
     * A method to determine if {@code n} is a prime number.
     * This method first attempts to check for simple known prime and non-prime numbers, afterward
     * it uses trial-by-division using an incrementing odd number to iterate by.
     *
     * @param n The number which shall be determined to be prime or non-prime
     * @return {@code true} if {@code n} is prime, otherwise {@code false}
     */
    public static boolean isPrimeByFast(int n) {
        if (n <= 1) {
            // numbers less than 2 are not considered prime
            return false;
        }

        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    /**
     * A method to determine if {@code n} is a prime number.
     * This method is similar to {@link #isPrimeByFast(int)}, but instead of incrementing by each odd number,
     * it increments by each odd number that is not a multiple of 3. This saves loop iterations.
     *
     * @param n The number which shall be determined to be prime or non-prime
     * @return {@code true} if {@code n} is prime, otherwise {@code false}
     */
    public static boolean isPrimeBySlow(int n) {

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
     * A method to determine if {@code n} is a prime number.
     * This method is similar to {@link #isPrimeBySieve(int)}, but also checks a sieve which
     * contains numerous pre-calculated prime and non-prime numbers.
     *
     * @param n The number which shall be determined to be prime or non-prime
     * @return {@code true} if {@code n} is prime, otherwise {@code false}
     */
    public static List<Integer> isPrimeBySieve(int n) {
        final List<Integer> primeNumbers= new ArrayList<>();
        if(n<= 1){
            return primeNumbers;
        }

        boolean sieve[] = new boolean[n+1];
        for(int i=0;i<n;i++) {
            sieve[i] = true;
        }
        for (int i = 2; i<=Math.sqrt(n); i++) {
            if (sieve[i]) {
                for (int j = i*i; j <= n; j+=i) {       // making 2p, 3p, ... false
                    sieve[j] = false;
                }

            }
        }
        // add all prime numbers to list
        for(int i = 2; i <= n; i++)
        {
            if(sieve[i] == true)
                primeNumbers.add(i);
        }
        return primeNumbers;
    }

}

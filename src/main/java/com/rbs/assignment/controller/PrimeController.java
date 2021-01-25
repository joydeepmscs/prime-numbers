package com.rbs.assignment.controller;

import com.rbs.assignment.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller which serves as an entry-point for requests for prime number information.
 *
 * @author Joydeep Paul
 */
@RestController
public class PrimeController {

    @Autowired
    private PrimeNumberService primeNumberService;

    /**
     * API Operation that returns a list of prime numbers from 2 to the upperBound (inclusive)
     *
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @param algorithm  The algorithm to use. Defaults to Sieve
     * @return a list of prime numbers from 2 to the upperBound (inclusive)
     */

    @RequestMapping(method = RequestMethod.GET, value = "/primes/{upperbound}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.TEXT_HTML_VALUE},consumes = MediaType.ALL_VALUE)
    List<Integer> primes(@PathVariable("upperbound") int upperBound,
                         @RequestParam(value = "algorithm", required = false, defaultValue = "0") int algorithm) {
        List<Integer> primeNumbers=null;
        switch (algorithm) {
            case 1:
                primeNumbers = primeNumberService.getPrimesUsingFastLoop(upperBound);
                break;
            case 2:
                primeNumbers = primeNumberService.getPrimes(upperBound);
                break;
            default:
                primeNumbers = primeNumberService.getPrimesUsingSieve(upperBound);
        }
        return primeNumbers;
    }

}


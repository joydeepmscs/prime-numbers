package com.rbs.assignment.controller;

import com.rbs.assignment.model.PrimeNumberResponse;
import com.rbs.assignment.service.PrimeNumberService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.GET, value = "/primes/{upperbound}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},consumes = MediaType.ALL_VALUE)
    ResponseEntity<PrimeNumberResponse> primes(@PathVariable("upperbound") int upperBound,
                                              @ApiParam(value = "algorithm",allowableValues = "fast,slow,sieve") @RequestParam(value = "algorithm",required = false,defaultValue = "sieve") String algorithm) {

        List<Integer> primeNumbers=null;
        switch (algorithm) {
            case "fast":
                primeNumbers = primeNumberService.getPrimesUsingFastLoop(upperBound);
                break;
            case "slow":
                primeNumbers = primeNumberService.getPrimes(upperBound);
                break;
            default:
                primeNumbers = primeNumberService.getPrimesUsingSieve(upperBound);
        }
        PrimeNumberResponse primeNumberResponse= new PrimeNumberResponse();
        primeNumberResponse.setInitials(upperBound);
        primeNumberResponse.setPrimes(primeNumbers);
        ResponseEntity<PrimeNumberResponse> responseEntity= new ResponseEntity<>(primeNumberResponse, HttpStatus.OK);
        return responseEntity;
    }

}


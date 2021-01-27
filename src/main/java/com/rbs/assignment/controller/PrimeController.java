package com.rbs.assignment.controller;

import com.rbs.assignment.exception.PrimeNumberProcessingException;
import com.rbs.assignment.model.PrimeNumberResponse;
import com.rbs.assignment.service.PrimeNumberService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rbs.assignment.exception.GenericErrorCode.GEN_001;

/**
 * REST Controller which serves as an entry-point for requests for prime number information.
 *
 * @author Joydeep Paul
 */
@RestController
public class PrimeController {
    private final Logger log = LoggerFactory.getLogger(PrimeController.class);
    @Autowired
    private PrimeNumberService primeNumberService;

    /**
     * API Operation that returns a list of prime numbers from 2 to the upperBound (inclusive)
     *
     * @param upperBound The inclusive upper bound to limit the size of the returned primes
     * @param algorithm  The algorithm to use. Defaults to Sieve
     * @return a list of prime numbers from 2 to the upperBound (inclusive)
     */

    @RequestMapping(method = RequestMethod.GET, value = "/primes/{upperbound}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.ALL_VALUE)
    ResponseEntity<PrimeNumberResponse> getPrimes(@PathVariable("upperbound") int upperBound,
                                                  @ApiParam(value = "algorithm", allowableValues = "default,sieve,slow") @RequestParam(value = "algorithm", required = false, defaultValue = "default") String algorithm) throws PrimeNumberProcessingException {
        log.debug("****** Starting getPrimes ******");
        ResponseEntity<PrimeNumberResponse> responseEntity = null;
        log.info("Prime numbers upperbound value: {} and chosen algorithm: {}", upperBound, algorithm);
        List<Integer> primeNumbers = null;
        switch (algorithm) {
            case "sieve":
                primeNumbers = primeNumberService.getPrimesUsingSieve(upperBound);
                break;
            case "slow":
                primeNumbers = primeNumberService.getPrimesUsingSlowLoop(upperBound);
                break;
            default:
                primeNumbers = primeNumberService.getPrimesUsingFastLoop(upperBound);

        }
        PrimeNumberResponse primeNumberResponse = new PrimeNumberResponse();
        primeNumberResponse.setInitials(upperBound);
        primeNumberResponse.setPrimes(primeNumbers);
        responseEntity = new ResponseEntity<>(primeNumberResponse, HttpStatus.OK);
        log.debug("****** Ending getPrimes ******");
        return responseEntity;
    }

}


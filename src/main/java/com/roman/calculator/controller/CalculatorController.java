package com.roman.calculator.controller;

import com.roman.calculator.service.CalculationService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Log
@RestController
@RequestMapping(value = "/api")
@Validated
public class CalculatorController {

    @Autowired
    private CalculationService calculationService;

    @Value("${max.digits}")
    private int maxDigits;

    private static final String FLOAT_PATTERN = "[+-]?([0-9]*[.])?[0-9]+";

    @GetMapping("/add/{a}/{b}/{c}")
    public ResponseEntity<BigDecimal> add(
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("a") String a,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("b") String b,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("c") String c) {
        BigDecimal result = calculationService.add(new BigDecimal(a), new BigDecimal(b), new BigDecimal(c));
        if (resultExceedsMaxSize(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/subtract/{a}/{b}/{c}")
    public ResponseEntity<BigDecimal> subtract(
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("a") String a,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("b") String b,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("c") String c) {
        BigDecimal result = calculationService.subtract(new BigDecimal(a), new BigDecimal(b), new BigDecimal(c));
        if (resultExceedsMaxSize(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/multiply/{a}/{b}/{c}")
    public ResponseEntity<BigDecimal> multiply(
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("a") String a,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("b") String b,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("c") String c) {
        BigDecimal result = calculationService.multiply(new BigDecimal(a), new BigDecimal(b), new BigDecimal(c));
        if (resultExceedsMaxSize(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/divide/{a}/{b}")
    public ResponseEntity<BigDecimal> divide(
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("a") String a,
            @Valid @NotBlank @Pattern(regexp = FLOAT_PATTERN) @PathVariable("b") String b) {
        BigDecimal result = calculationService.divide(new BigDecimal(a), new BigDecimal(b));
        if (resultExceedsMaxSize(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Returns true if the result value exceeds max digits size.
     *
     * @param result BigDecimal result value
     * @return true if value exceeds otherwise false
     */
    private boolean resultExceedsMaxSize(BigDecimal result) {
        if (result != null && result.toString().length() > maxDigits) {
            log.severe("Result exceeds max value " + maxDigits);
            return true;
        }
        return false;
    }
}

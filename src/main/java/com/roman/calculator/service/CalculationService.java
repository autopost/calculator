package com.roman.calculator.service;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Performs calculation for  finance needs using BigDecimal.
 * We use BigDecimal type to avoid of rounding problems.
 */
@Data
@Log
@Service
public class CalculationService {

    /**
     * Returns sum of three input BigDecimal parameters.
     *
     * @param a BigDecimal input parameter
     * @param b BigDecimal input parameter
     * @param c BigDecimal input parameter
     * @return BigDecimal sum of three values
     */
    @Cacheable("result")
    public BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c) {
        log.info("Summation of three values: " + a.toString() + " " + b.toString() + " " + c.toString());
        return a.add(b).add(c);
    }

    /**
     * Subtracts three input BigDecimal values.
     *
     * @param a BigDecimal input parameter
     * @param b BigDecimal input parameter
     * @param c BigDecimal input parameter
     * @return BigDecimal result of subtraction of three values
     */
    @Cacheable("result")
    public BigDecimal subtract(BigDecimal a, BigDecimal b, BigDecimal c) {
        log.info("Subtraction of three values: " + a.toString() + " " + b.toString() + " " + c.toString());
        return a.subtract(b).subtract(c);
    }

    /**
     * Multiplies three input BigDecimal values.
     *
     * @param a BigDecimal input parameter
     * @param b BigDecimal input parameter
     * @param c BigDecimal input parameter
     * @return BigDecimal result of multiplying of three values
     */
    @Cacheable("result")
    public BigDecimal multiply(BigDecimal a, BigDecimal b, BigDecimal c) {
        log.info("Multiply of three values: " + a.toString() + " " + b.toString() + " " + c.toString());
        return a.multiply(b).multiply(c);
    }

    /**
     * Divides two BigDecimal values.
     *
     * @param a BigDecimal input parameter
     * @param b BigDecimal input parameter
     * @return BigDecimal resut of dividing two values
     */
    @Cacheable("result")
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        log.info("Dividing of two values: " + a.toString() + " " + b.toString());
        return a.divide(b, RoundingMode.HALF_UP);
    }
}

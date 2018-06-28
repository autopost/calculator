package com.roman.calculator.controller;

import com.roman.calculator.service.CalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculationServiceTest {
    private CalculationService calculationService;

    @Before
    public void setUp() {
        calculationService = new CalculationService();
    }

    @Test
    public void add() throws Exception {
        Assert.assertEquals(new BigDecimal("6"),
                calculationService.add(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")));

        Assert.assertEquals(new BigDecimal("6.01"),
                calculationService.add(new BigDecimal("1.01"), new BigDecimal("2"), new BigDecimal("3")));

        Assert.assertEquals(new BigDecimal("3.99"),
                calculationService.add(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")));

        Assert.assertEquals(new BigDecimal("0.99"),
                calculationService.add(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("0")));
    }

    @Test
    public void subtract() throws Exception {
        Assert.assertEquals(new BigDecimal("3"),
                calculationService.subtract(new BigDecimal("5"), new BigDecimal("1"), new BigDecimal("1")));

        Assert.assertEquals(new BigDecimal("6.01"),
                calculationService.subtract(new BigDecimal("11.01"), new BigDecimal("2"), new BigDecimal("3")));

        Assert.assertEquals(new BigDecimal("-6.01"),
                calculationService.subtract(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")));
    }

    @Test
    public void multiply() throws Exception {
        Assert.assertEquals(new BigDecimal("5"),
                calculationService.multiply(new BigDecimal("5"), new BigDecimal("1"), new BigDecimal("1")));

        Assert.assertEquals(new BigDecimal("33.03"),
                calculationService.multiply(new BigDecimal("11.01"), new BigDecimal("1"), new BigDecimal("3")));

        Assert.assertEquals(new BigDecimal("-6.06"),
                calculationService.multiply(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")));

    }

    @Test
    public void divide() throws Exception {
        Assert.assertEquals(new BigDecimal("5"),
                calculationService.divide(new BigDecimal("5"), new BigDecimal("1")));

        Assert.assertEquals(new BigDecimal("16.7"),
                calculationService.divide(new BigDecimal("33.4"), new BigDecimal("2")));

        Assert.assertEquals(new BigDecimal("-7.751"),
                calculationService.divide(new BigDecimal("-23.254"), new BigDecimal("3")));

    }

    @Test(expected = ArithmeticException.class)
    public void divideZero() throws Exception {
        calculationService.divide(new BigDecimal("5"), new BigDecimal("0"));
    }

}
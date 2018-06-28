package com.roman.calculator.controller;

import com.roman.calculator.service.CalculationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CalculationService calculationService;

    @Test
    public void add() throws Exception {

        given(calculationService.add(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("6"));
        MvcResult mvcResult = mvc.perform(get("/api/add/{a}/{b}/{c}", "1", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("6", mvcResult.getResponse().getContentAsString());

        given(calculationService.add(new BigDecimal("1.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("6.01"));
        mvcResult = mvc.perform(get("/api/add/{a}/{b}/{c}", "1.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("6.01", mvcResult.getResponse().getContentAsString());

        given(calculationService.add(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("4.99"));
        mvcResult = mvc.perform(get("/api/add/{a}/{b}/{c}", "-1.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("4.99", mvcResult.getResponse().getContentAsString());

        mvc.perform(get("/api/add/{a}/{b}", "1.01", "2"))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/add/{a}/{b}/{c}", "1", "2", "sss"))
                .andExpect(status().is(400));
    }

    @Test
    public void subtract() throws Exception {

        given(calculationService.subtract(new BigDecimal("5"), new BigDecimal("1"), new BigDecimal("1")))
                .willReturn(new BigDecimal("3"));
        MvcResult mvcResult = mvc.perform(get("/api/subtract/{a}/{b}/{c}", "5", "1", "1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("3", mvcResult.getResponse().getContentAsString());

        given(calculationService.subtract(new BigDecimal("11.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("6.01"));
        mvcResult = mvc.perform(get("/api/subtract/{a}/{b}/{c}", "11.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("6.01", mvcResult.getResponse().getContentAsString());

       given(calculationService.subtract(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("-6.01"));
        mvcResult = mvc.perform(get("/api/subtract/{a}/{b}/{c}", "-1.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("-6.01", mvcResult.getResponse().getContentAsString());

        mvc.perform(get("/api/subtract/{a}/{b}", "1.01", "2"))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/subtract/{a}/{b}/{c}", "1", "2", "sss"))
                .andExpect(status().is(400));
    }

    @Test
    public void multiply() throws Exception {

        given(calculationService.multiply(new BigDecimal("5"), new BigDecimal("1"), new BigDecimal("1")))
                .willReturn(new BigDecimal("5"));
        MvcResult mvcResult = mvc.perform(get("/api/multiply/{a}/{b}/{c}", "5", "1", "1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("5", mvcResult.getResponse().getContentAsString());

        given(calculationService.multiply(new BigDecimal("11.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("66.06"));
        mvcResult = mvc.perform(get("/api/multiply/{a}/{b}/{c}", "11.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("66.06", mvcResult.getResponse().getContentAsString());

        given(calculationService.multiply(new BigDecimal("-1.01"), new BigDecimal("2"), new BigDecimal("3")))
                .willReturn(new BigDecimal("-6.06"));
        mvcResult = mvc.perform(get("/api/multiply/{a}/{b}/{c}", "-1.01", "2", "3"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("-6.06", mvcResult.getResponse().getContentAsString());

        mvc.perform(get("/api/multiply/{a}/{b}", "1.01", "2"))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/multiply/{a}/{b}/{c}", "1.01", "2", null))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/multiply/{a}/{b}/{c}", "1", "2", "sss"))
                .andExpect(status().is(400));
    }
    @Test
    public void divide() throws Exception {

        given(calculationService.divide(new BigDecimal("5"), new BigDecimal("1")))
                .willReturn(new BigDecimal("5"));
        MvcResult mvcResult = mvc.perform(get("/api/divide/{a}/{b}", "5", "1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("5", mvcResult.getResponse().getContentAsString());

        given(calculationService.divide(new BigDecimal("22.22"), new BigDecimal("2")))
                .willReturn(new BigDecimal("11.11"));
        mvcResult = mvc.perform(get("/api/divide/{a}/{b}", "22.22", "2"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("11.11", mvcResult.getResponse().getContentAsString());

        given(calculationService.divide(new BigDecimal("-22.22"), new BigDecimal("2")))
                .willReturn(new BigDecimal("-11.11"));
        mvcResult = mvc.perform(get("/api/divide/{a}/{b}", "-22.22", "2"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("-11.11", mvcResult.getResponse().getContentAsString());

        mvc.perform(get("/api/divide/{a}", "1.01"))
                .andExpect(status().isNotFound());

        mvc.perform(get("/api/divide/{a}/{b}", "1", "sss"))
                .andExpect(status().is(400));
    }


}

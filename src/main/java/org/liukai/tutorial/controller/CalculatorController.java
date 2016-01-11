package org.liukai.tutorial.controller;

import org.liukai.tutorial.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/calculator")
public class CalculatorController {
    @Resource
    private CalculatorService calculatorService;

    @RequestMapping(value = "/cal2")
    @ResponseBody
    public String cal2(String expression) {

        String result;
        System.out.println("hello");

        try {
            result = calculatorService.cal2(expression);
        } catch (Exception e) {
            e.printStackTrace();
            result = "ERROR";
        }
        return result;
    }

}

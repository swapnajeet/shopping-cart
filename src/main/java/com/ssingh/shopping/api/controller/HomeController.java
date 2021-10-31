package com.ssingh.shopping.api.controller;


import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Operation(hidden = true)
    @GetMapping
    public ModelAndView redirectToSwaggerUI() {
        return new ModelAndView("redirect:/swagger-ui/index.html?configUrl=/v1/api-docs/swagger-config#/");
    }
}
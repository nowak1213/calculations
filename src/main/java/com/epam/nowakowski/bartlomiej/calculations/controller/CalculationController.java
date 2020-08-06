package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.model.ClientWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculations")
public class CalculationController {

   @GetMapping
   public String get(@RequestBody ClientWrapper clients) {
      return "First approach";
   }
}

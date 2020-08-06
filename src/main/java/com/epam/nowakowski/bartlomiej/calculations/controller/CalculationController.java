package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.model.request.ClientWrapper;
import com.epam.nowakowski.bartlomiej.calculations.model.response.CalculationResult;
import com.epam.nowakowski.bartlomiej.calculations.service.CalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculations")
public class CalculationController {

   private final CalculationService calculationService;

   public CalculationController(CalculationService calculationService) {
      this.calculationService = calculationService;
   }

   @GetMapping
   public CalculationResult get(@RequestBody ClientWrapper clientWrapper) {
      return calculationService.performCalculation(clientWrapper.getClients().getClient());
   }
}

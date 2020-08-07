package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.exception.MissingFieldsDeserializationException;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Client;
import com.epam.nowakowski.bartlomiej.calculations.model.request.ClientWrapper;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Clients;
import com.epam.nowakowski.bartlomiej.calculations.model.response.CalculationResult;
import com.epam.nowakowski.bartlomiej.calculations.service.CalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping(value = "/calculations")
public class CalculationController {

   private final CalculationService calculationService;

   public CalculationController(CalculationService calculationService) {
      this.calculationService = calculationService;
   }

   @GetMapping
   public CalculationResult get(@RequestBody ClientWrapper clientWrapper) {
      List<Client> clientList = Optional.ofNullable(clientWrapper.getClients())
            .map(throwExceptionIfEmptyClientList())
            .orElseThrow(MissingFieldsDeserializationException::new);
      return calculationService.performCalculation(clientList);
   }

   private Function<Clients, List<Client>> throwExceptionIfEmptyClientList() {
      return clients -> {
         if (clients.getClient().isEmpty()) {
            throw new MissingFieldsDeserializationException();
         }
         return clients.getClient();
      };
   }
}

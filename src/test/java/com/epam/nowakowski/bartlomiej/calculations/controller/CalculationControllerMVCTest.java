package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.model.Balance;
import com.epam.nowakowski.bartlomiej.calculations.model.Client;
import com.epam.nowakowski.bartlomiej.calculations.model.ClientWrapper;
import com.epam.nowakowski.bartlomiej.calculations.model.Clients;
import com.epam.nowakowski.bartlomiej.calculations.model.Info;
import com.epam.nowakowski.bartlomiej.calculations.model.Transaction;
import com.epam.nowakowski.bartlomiej.calculations.model.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CalculationController.class)
public class CalculationControllerMVCTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   void shouldSuccessfullySendRequestAndGet200() throws Exception {

      ClientWrapper clientWrapper = prepareCorrectBody();

      mockMvc.perform(
            get("/calculation").content(objectMapper.writeValueAsString(clientWrapper)).contentType("application/json"))
            .andExpect(status().isOk());
   }

   private ClientWrapper prepareCorrectBody() {
      Client client = prepareClientInfo();
      List<Client> clientList = Collections.singletonList(client);
      Clients clients = new Clients(clientList);
      return new ClientWrapper(clients);
   }

   private Client prepareClientInfo() {
      Info info = new Info("anyName", "anySurname");
      LocalDate localDate = LocalDate.of(2010, 10, 10);
      localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
      Balance balance = new Balance(new BigDecimal(10), "PLN", localDate);

      Transaction transaction = new Transaction(Type.INCOME, "anyDesc", localDate, new BigDecimal(10), "PLN");
      return new Client(info, balance, Collections.singletonList(transaction));
   }
}
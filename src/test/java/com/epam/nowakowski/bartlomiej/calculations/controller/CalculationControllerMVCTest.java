package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.model.request.Balance;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Client;
import com.epam.nowakowski.bartlomiej.calculations.model.request.ClientWrapper;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Clients;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Info;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Transaction;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Type;
import com.epam.nowakowski.bartlomiej.calculations.service.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

   @MockBean
   private CalculationService calculationService;

   @Test
   void shouldSuccessfullySendRequestAndGet200() throws Exception {
      ClientWrapper clientWrapper = prepareCorrectBody();

      mockMvc.perform(get("/calculations")
            .content(objectMapper.writeValueAsString(clientWrapper))
            .contentType("application/json"))
            .andExpect(status().isOk());
   }

   @Test
   void shouldReturn400WhenClientsObjectNull() throws Exception {
      ClientWrapper clientWrapper = new ClientWrapper(null);

      mockMvc.perform(get("/calculations")
            .content(objectMapper.writeValueAsString(clientWrapper))
            .contentType("application/json"))
            .andExpect(status().isBadRequest());
   }

   @Test
   void shouldReturn400WhenClientsListEmpty() throws Exception {
      List<Client> clientList = new ArrayList<>();
      Clients clients = new Clients(clientList);
      ClientWrapper clientWrapper = new ClientWrapper(clients);

      mockMvc.perform(get("/calculations")
            .content(objectMapper.writeValueAsString(clientWrapper))
            .contentType("application/json"))
            .andExpect(status().isBadRequest());
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
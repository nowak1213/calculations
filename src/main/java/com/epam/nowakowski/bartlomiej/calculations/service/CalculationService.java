package com.epam.nowakowski.bartlomiej.calculations.service;

import com.epam.nowakowski.bartlomiej.calculations.model.request.Client;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Transaction;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Type;
import com.epam.nowakowski.bartlomiej.calculations.model.response.CalculationResult;
import com.epam.nowakowski.bartlomiej.calculations.model.response.ClientCalculatedInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationService {

   public CalculationResult performCalculation(final List<Client> clients) {
      List<ClientCalculatedInfo> result = clients.stream()
            .map(this::calculate)
            .collect(Collectors.toList());

      return new CalculationResult(result);
   }

   private ClientCalculatedInfo calculate(Client client) {
      String name = client.getInfo().getName();
      String surname = client.getInfo().getSurname();
      BigDecimal income = sumTransactionType(client, Type.INCOME);
      BigDecimal expenditures = sumTransactionType(client, Type.OUTCOME);
      BigDecimal turnout = income.add(expenditures);
      BigDecimal balance = client.getBalance().getTotal().add(income).subtract(expenditures);
      return new ClientCalculatedInfo(name, surname, balance, turnout, income, expenditures);
   }

   private BigDecimal sumTransactionType(Client client, Type type) {
      return client.getTransactions().stream().filter(a -> a.getType() == type)
            .map(Transaction::getValue)
            .reduce(new BigDecimal("0.0"), BigDecimal::add);
   }
}

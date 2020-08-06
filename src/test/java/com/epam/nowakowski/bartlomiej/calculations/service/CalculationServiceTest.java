package com.epam.nowakowski.bartlomiej.calculations.service;

import com.epam.nowakowski.bartlomiej.calculations.model.request.Balance;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Client;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Info;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Transaction;
import com.epam.nowakowski.bartlomiej.calculations.model.request.Type;
import com.epam.nowakowski.bartlomiej.calculations.model.response.CalculationResult;
import com.epam.nowakowski.bartlomiej.calculations.model.response.ClientCalculatedInfo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationServiceTest {

   private static final LocalDate ANY_DATE = LocalDate.now();

   private static final String ANY_CURRENCY = "PLN";

   private static final String ANY_DESCRIPTION = "anyDescription";

   private static final String FIRST_CLIENT_NAME = "Adam";

   public static final String FIRST_CLIENT_SURNAME = "Nowak";

   private static final String SECOND_CLIENT_NAME = "Ala";

   private static final String SECOND_CLIENT_SURNAME = "Kowalska";

   CalculationService systemUnderTest = new CalculationService();

   @Test
   void shouldPerformCalculationOnOnePersonWhichIncomesAreHigherThanOutcomes() {
      Client client = prepareDataForFirstClient();

      CalculationResult result = systemUnderTest.performCalculation(Collections.singletonList(client));

      ClientCalculatedInfo calculatedFirstClient = result.getClientCalculatedInfo().get(0);
      checkFirstClientData(calculatedFirstClient);
   }

   @Test
   void shouldPerformCalculationOnOnePersonWhichIncomesAreLowerThanOutcomes() {
      Client client = prepareDataForSecondClient();

      CalculationResult result = systemUnderTest.performCalculation(Collections.singletonList(client));

      ClientCalculatedInfo calculatedSecondClient = result.getClientCalculatedInfo().get(0);
      checkSecondClientData(calculatedSecondClient);
   }

   @Test
   void shouldPerformCalculationOnTwoPeople() {
      Client firstClient = prepareDataForFirstClient();
      Client secondClient = prepareDataForSecondClient();

      CalculationResult result = systemUnderTest.performCalculation(Arrays.asList(firstClient, secondClient));

      List<ClientCalculatedInfo> calculatedClients = result.getClientCalculatedInfo();
      checkFirstClientData(calculatedClients.get(0));
      checkSecondClientData(calculatedClients.get(1));
   }

   private Client prepareDataForFirstClient() {
      Info info = new Info(FIRST_CLIENT_NAME, FIRST_CLIENT_SURNAME);
      Balance balance = new Balance(new BigDecimal(1000), ANY_CURRENCY, ANY_DATE);
      Transaction transactionIncome1 = new Transaction(Type.INCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(4000),
            ANY_CURRENCY);
      Transaction transactionIncome2 = new Transaction(Type.INCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(1000),
            ANY_CURRENCY);
      Transaction transactionOutcome1 = new Transaction(Type.OUTCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(2000),
            ANY_CURRENCY);
      return new Client(info, balance, Arrays.asList(transactionIncome1, transactionIncome2, transactionOutcome1));
   }

   private Client prepareDataForSecondClient() {
      Info info = new Info(SECOND_CLIENT_NAME, SECOND_CLIENT_SURNAME);
      Balance balance = new Balance(new BigDecimal(1000), ANY_CURRENCY, ANY_DATE);
      Transaction transactionIncome1 = new Transaction(Type.INCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(4000),
            ANY_CURRENCY);
      Transaction transactionOutcome1 = new Transaction(Type.OUTCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(2000),
            ANY_CURRENCY);
      Transaction transactionOutcome2 = new Transaction(Type.OUTCOME, ANY_DESCRIPTION, ANY_DATE, new BigDecimal(4000),
            ANY_CURRENCY);
      return new Client(info, balance, Arrays.asList(transactionIncome1, transactionOutcome1, transactionOutcome2));
   }

   private void checkFirstClientData(ClientCalculatedInfo clientResult) {
      assertThat(clientResult.getName()).isEqualTo(FIRST_CLIENT_NAME);
      assertThat(clientResult.getSurname()).isEqualTo(FIRST_CLIENT_SURNAME);
      assertThat(clientResult.getCurrentBalance().doubleValue()).isEqualTo(4000.00);
      assertThat(clientResult.getTurnout().doubleValue()).isEqualTo(7000.00);
      assertThat(clientResult.getIncomes().doubleValue()).isEqualTo(5000.00);
      assertThat(clientResult.getExpenditures().doubleValue()).isEqualTo(2000.00);
   }

   private void checkSecondClientData(ClientCalculatedInfo clientResult) {
      assertThat(clientResult.getName()).isEqualTo(SECOND_CLIENT_NAME);
      assertThat(clientResult.getSurname()).isEqualTo(SECOND_CLIENT_SURNAME);
      assertThat(clientResult.getCurrentBalance().doubleValue()).isEqualTo(-1000.00);
      assertThat(clientResult.getTurnout().doubleValue()).isEqualTo(10000.00);
      assertThat(clientResult.getIncomes().doubleValue()).isEqualTo(4000.00);
      assertThat(clientResult.getExpenditures().doubleValue()).isEqualTo(6000.00);
   }
}

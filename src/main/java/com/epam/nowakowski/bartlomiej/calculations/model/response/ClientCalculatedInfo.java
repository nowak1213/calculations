package com.epam.nowakowski.bartlomiej.calculations.model.response;

import java.math.BigDecimal;
import java.util.Objects;

public class ClientCalculatedInfo {

   private final String name;

   private final String surname;

   private final BigDecimal currentBalance;

   private final BigDecimal turnout;

   private final BigDecimal incomes;

   private final BigDecimal expenditures;

   public ClientCalculatedInfo(String name, String surname, BigDecimal currentBalance, BigDecimal turnout,
         BigDecimal incomes, BigDecimal expenditures) {
      this.name = name;
      this.surname = surname;
      this.currentBalance = currentBalance;
      this.turnout = turnout;
      this.incomes = incomes;
      this.expenditures = expenditures;
   }

   public String getName() {
      return name;
   }

   public String getSurname() {
      return surname;
   }

   public BigDecimal getCurrentBalance() {
      return currentBalance;
   }

   public BigDecimal getTurnout() {
      return turnout;
   }

   public BigDecimal getIncomes() {
      return incomes;
   }

   public BigDecimal getExpenditures() {
      return expenditures;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      ClientCalculatedInfo that = (ClientCalculatedInfo) o;
      return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects
            .equals(currentBalance, that.currentBalance) && Objects.equals(turnout, that.turnout) && Objects
            .equals(incomes, that.incomes) && Objects.equals(expenditures, that.expenditures);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, surname, currentBalance, turnout, incomes, expenditures);
   }

   @Override
   public String toString() {
      return "ClientCalculatedInfo{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", currentBalance="
            + currentBalance + ", turnout=" + turnout + ", incomes=" + incomes + ", expenditures=" + expenditures + '}';
   }
}

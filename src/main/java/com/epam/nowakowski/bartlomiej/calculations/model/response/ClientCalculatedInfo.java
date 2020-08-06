package com.epam.nowakowski.bartlomiej.calculations.model.response;

import java.math.BigDecimal;
import java.util.Objects;

//TODO: Add serializer into BigDecimal
public class ClientCalculatedInfo {

   private String name;

   private String surname;

   private BigDecimal currentBalance;

   private BigDecimal turnout;

   private BigDecimal incomes;

   private BigDecimal expenditures;

   public ClientCalculatedInfo() {
   }

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

   public void setName(String name) {
      this.name = name;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public void setCurrentBalance(BigDecimal currentBalance) {
      this.currentBalance = currentBalance;
   }

   public void setTurnout(BigDecimal turnout) {
      this.turnout = turnout;
   }

   public void setIncomes(BigDecimal incomes) {
      this.incomes = incomes;
   }

   public void setExpenditures(BigDecimal expenditures) {
      this.expenditures = expenditures;
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

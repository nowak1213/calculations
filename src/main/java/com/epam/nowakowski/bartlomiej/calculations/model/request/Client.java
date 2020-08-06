package com.epam.nowakowski.bartlomiej.calculations.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Client {

   @JsonProperty(required = true)
   private final Info info;

   @JsonProperty(required = true)
   private final Balance balance;

   @JsonProperty(required = true)
   private final List<Transaction> transactions;

   public Client(final Info info, final Balance balance, final List<Transaction> transactions) {
      this.info = info;
      this.balance = balance;
      this.transactions = transactions;
   }

   public Info getInfo() {
      return info;
   }

   public Balance getBalance() {
      return balance;
   }

   public List<Transaction> getTransactions() {
      return transactions;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final Client client = (Client) o;
      return Objects.equals(info, client.info) && Objects.equals(balance, client.balance) && Objects
            .equals(transactions, client.transactions);
   }

   @Override
   public int hashCode() {
      return Objects.hash(info, balance, transactions);
   }

   @Override
   public String toString() {
      return "Client{" + "info=" + info + ", balance=" + balance + ", transactions=" + transactions + '}';
   }
}

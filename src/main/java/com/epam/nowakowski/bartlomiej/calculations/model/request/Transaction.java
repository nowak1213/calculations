package com.epam.nowakowski.bartlomiej.calculations.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

   @JsonProperty(required = true)
   private final Type type;

   @JsonProperty(required = true)
   private final String description;

   @JsonProperty(required = true)
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
   private final LocalDate date;

   @JsonProperty(required = true)
   private final BigDecimal value;

   @JsonProperty(required = true)
   private final String currency;

   @JsonCreator
   public Transaction(final Type type, final String description, final LocalDate date, final BigDecimal value,
         final String currency) {
      this.type = type;
      this.description = description;
      this.date = date;
      this.value = value;
      this.currency = currency;
   }

   public Type getType() {
      return type;
   }

   public String getDescription() {
      return description;
   }

   public LocalDate getDate() {
      return date;
   }

   public BigDecimal getValue() {
      return value;
   }

   public String getCurrency() {
      return currency;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final Transaction that = (Transaction) o;
      return type == that.type && Objects.equals(description, that.description) && Objects.equals(date, that.date)
            && Objects.equals(value, that.value) && Objects.equals(currency, that.currency);
   }

   @Override
   public int hashCode() {
      return Objects.hash(type, description, date, value, currency);
   }

   @Override
   public String toString() {
      return "Transaction{" + "type=" + type + ", description='" + description + '\'' + ", date=" + date + ", value="
            + value + ", currency='" + currency + '\'' + '}';
   }
}

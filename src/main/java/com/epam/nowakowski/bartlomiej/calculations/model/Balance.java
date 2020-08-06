package com.epam.nowakowski.bartlomiej.calculations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Balance {

   @JsonProperty(required = true)
   private final BigDecimal total;

   @JsonProperty(required = true)
   private final String currency;

   @JsonProperty(required = true)
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
   private final LocalDate date;

   @JsonCreator
   public Balance(final BigDecimal total, final String currency, final LocalDate date) {
      this.total = total;
      this.currency = currency;
      this.date = date;
   }

   public BigDecimal getTotal() {
      return total;
   }

   public String getCurrency() {
      return currency;
   }

   public LocalDate getDate() {
      return date;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final Balance balance = (Balance) o;
      return Objects.equals(total, balance.total) && Objects.equals(currency, balance.currency) && Objects
            .equals(date, balance.date);
   }

   @Override
   public int hashCode() {
      return Objects.hash(total, currency, date);
   }

   @Override
   public String toString() {
      return "Balance{" + "total=" + total + ", currency='" + currency + '\'' + ", date=" + date + '}';
   }
}

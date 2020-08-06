package com.epam.nowakowski.bartlomiej.calculations.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
   INCOME("income"),
   OUTCOME("outcome");

   private final String name;

   Type(final String name) {
      this.name = name;
   }

   @JsonValue
   public String getName() {
      return name;
   }
}

package com.epam.nowakowski.bartlomiej.calculations.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Info {

   @JsonProperty(required = true)
   private final String name;

   @JsonProperty(required = true)
   private final String surname;

   public Info(final String name, final String surname) {
      this.name = name;
      this.surname = surname;
   }

   public String getName() {
      return name;
   }

   public String getSurname() {
      return surname;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      final Info info = (Info) o;
      return Objects.equals(name, info.name) && Objects.equals(surname, info.surname);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, surname);
   }

   @Override
   public String toString() {
      return "Info{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
   }
}

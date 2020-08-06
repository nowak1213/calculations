package com.epam.nowakowski.bartlomiej.calculations.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Clients {

   @JsonProperty(required=true)
   List<Client> client;

   @JsonCreator
   public Clients(final List<Client> client) {
      this.client = client;
   }

   public List<Client> getClient() {
      return client;
   }
}

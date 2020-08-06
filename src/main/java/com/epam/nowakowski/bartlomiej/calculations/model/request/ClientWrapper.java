package com.epam.nowakowski.bartlomiej.calculations.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientWrapper {

   @JsonProperty(required=true)
   private Clients clients;

   @JsonCreator
   public ClientWrapper(final Clients clients) {
      this.clients = clients;
   }

   public Clients getClients() {
      return clients;
   }
}

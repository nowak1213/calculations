package com.epam.nowakowski.bartlomiej.calculations.model.response;

import java.util.List;
import java.util.Objects;

public class CalculationResult {

   private final List<ClientCalculatedInfo> clientCalculatedInfo;

   public CalculationResult(final List<ClientCalculatedInfo> clientCalculatedInfo) {
      this.clientCalculatedInfo = clientCalculatedInfo;
   }

   public List<ClientCalculatedInfo> getClientCalculatedInfo() {
      return clientCalculatedInfo;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      CalculationResult that = (CalculationResult) o;
      return Objects.equals(clientCalculatedInfo, that.clientCalculatedInfo);
   }

   @Override
   public int hashCode() {
      return Objects.hash(clientCalculatedInfo);
   }

   @Override
   public String toString() {
      return "CalculationResult{" + "clientCalculatedInfo=" + clientCalculatedInfo + '}';
   }
}

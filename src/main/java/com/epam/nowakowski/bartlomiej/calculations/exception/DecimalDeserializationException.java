package com.epam.nowakowski.bartlomiej.calculations.exception;

public class DecimalDeserializationException extends RuntimeException {

   private static final String EXCEPTION_MSG = "Error during number deserialization";

   public DecimalDeserializationException() {
      super(EXCEPTION_MSG);
   }
}

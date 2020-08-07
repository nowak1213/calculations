package com.epam.nowakowski.bartlomiej.calculations.exception;

public class MissingFieldsDeserializationException extends RuntimeException {

   private static final String EXCEPTION_MSG = "Error during number deserialization";

   public MissingFieldsDeserializationException() {
      super(EXCEPTION_MSG);
   }
}

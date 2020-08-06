package com.epam.nowakowski.bartlomiej.calculations;

import com.epam.nowakowski.bartlomiej.calculations.exception.DecimalDeserializationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.vavr.control.Try;

import java.math.BigDecimal;

public class DecimalDeserializer extends StdDeserializer<BigDecimal> {

   public DecimalDeserializer() {
      this(null);
   }

   public DecimalDeserializer(Class<BigDecimal> value) {
      super(value);
   }

   @Override
   public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
      return Try.of(jsonParser::getValueAsString)
            .map(value -> value.replace(",", "."))
            .map(BigDecimal::new)
            .onFailure(NumberFormatException.class, ex -> {
               throw new DecimalDeserializationException();
            })
            .get();
   }
}

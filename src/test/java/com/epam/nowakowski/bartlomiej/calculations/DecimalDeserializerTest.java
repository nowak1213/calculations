package com.epam.nowakowski.bartlomiej.calculations;

import com.epam.nowakowski.bartlomiej.calculations.exception.DecimalDeserializationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DecimalDeserializerTest {

   @Mock
   JsonParser jsonParser;

   @Mock
   DeserializationContext context;

   DecimalDeserializer systemUnderTest = new DecimalDeserializer(BigDecimal.class);

   @Test
   void shouldChangeCommaToDot() throws IOException {
      String actualValue = "123,45";
      when(jsonParser.getValueAsString()).thenReturn(actualValue);

      BigDecimal result = systemUnderTest.deserialize(jsonParser, context);

      assertThat(result).isEqualTo(new BigDecimal("123.45"));
   }

   @Test
   void shouldThrowDecimalDeserializationExceptionWhenInvalidInput() throws IOException {
      String actualValue = "anyString";
      when(jsonParser.getValueAsString()).thenReturn(actualValue);

      Throwable result = catchThrowable(() -> systemUnderTest.deserialize(jsonParser, context));

      assertThat(result).isExactlyInstanceOf(DecimalDeserializationException.class);
   }
}

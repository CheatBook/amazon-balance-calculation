package com.cheatbook.amazon_balance_calculation.deserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    String date = p.getText()
                   .trim();
    if (date.isEmpty()) {
      return null; // 空文字の場合は null を返す
    }
    try {
      return LocalDate.parse(date, formatter);
    } catch (Exception e) {
      throw new IOException("Date parsing error: " + e.getMessage(), e);
    }
  }

}

package com.bootcamp.demo.demo_sb_stockchart.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TStockRecordTypeConverter implements Converter<String, TStockRecordType> {
  @Override
  public TStockRecordType convert(@NonNull String value) {
    if (value.isEmpty()) {
      return null;
    }
    return TStockRecordType.fromValue(value);
  }
}

package com.logosty.template.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author logosty(ganyingle)
 * @date 2019-08-12 21:29 description: stringList 转换 string 的工具类，且可用在 mysql entity 注解中
 */
public class StringListToStringConverter implements AttributeConverter<List<String>, String> {

  private static final String DEFAULT_SEP = ",";

  public static String convertToString(List<String> attribute) {
    return convertToString(attribute, DEFAULT_SEP);
  }

  public static String convertToString(List<String> attribute, String sep) {
    return StringUtils.join(attribute, sep);
  }

  public static List<String> convertToStringList(String dbData) {
    return convertToStringList(dbData, DEFAULT_SEP);
  }

  public static List<String> convertToStringList(String dbData, String sep) {
    if (StringUtils.isEmpty(dbData)) {
      return new ArrayList<>();
    }
    return Arrays.asList(dbData.split(sep));
  }

  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    return convertToString(attribute);
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    return convertToStringList(dbData);
  }

}

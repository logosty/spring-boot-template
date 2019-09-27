package com.logosty.template.common.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.stereotype.Component;

/**
 * @author logosty(ganyingle)
 * @date 2019-08-13 17:17
 */

@Component
public class MysqlConfig extends MySQL5InnoDBDialect {

  @Override
  public String getTableTypeString() {
    return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
  }

}


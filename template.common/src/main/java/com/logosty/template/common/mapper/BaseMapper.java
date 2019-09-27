package com.logosty.template.common.mapper;

import java.util.Date;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 14:45
 */
public interface BaseMapper {

  default Long dateToLong(Date date) {
    if (date == null) {
      return null;
    }
    return date.getTime();
  }

  default Date longToDate(Long along) {
    if (along == null) {
      return null;
    }
    return new Date(along);
  }
}

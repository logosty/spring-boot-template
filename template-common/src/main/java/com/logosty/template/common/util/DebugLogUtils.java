package com.logosty.template.common.util;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;

/**
 * @author logosty(ganyingle) on 2019/9/27 16:04
 * description: 采样打印日志工具类
 */
public class DebugLogUtils {

  public static boolean debugLog(Logger log, int percent, String payload, Object... objects) {
    if (RandomUtils.nextInt(0, percent) == 0) {
      log.info(payload, objects);
      return true;
    }
    return false;
  }
}

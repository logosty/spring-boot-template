package com.logosty.template.common.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;

/**
 * @author logosty(ganyingle) on 2019/9/27 16:04
 * description: 采样打印日志工具类
 */
public class DebugLogUtils {

  private static String DEFAULT_METHOD_PAYLOAD = "[{}] class do [{}]--> ";

  private static Set<String> whitelistKeys = ImmutableSet.<String>builder()
      .add("2db4796e04d34849866bb21cc5ce89f77ec1fdca")    //shunmin
      .add("5fd73f4b9d42fd7202d69cdf3a6b0403")            //shunmin
      .add("7ec4e2295dbe03eacceb0b8a70d557bd")            //yingle
      .build();

  public static boolean debugLog(Logger log, int percent, String payload, Object... objects) {
    return debugLog(null, log, percent, payload, objects);
  }

  public static boolean debugLog(String key, Logger log, int percent, String payload,
      Object... objects) {
    if (RandomUtils.nextInt(0, percent) == 0 || whitelistKeys.contains(key)) {
      doLog(log, false, payload, objects);
      return true;
    }
    return false;
  }

  public static boolean debugLog(Logger log, int percent, String payload, Supplier... suppliers) {
    return debugLog(null, log, percent, payload, suppliers);
  }

  public static boolean debugLog(String key, Logger log, int percent, String payload,
      Supplier... suppliers) {
    if (RandomUtils.nextInt(0, percent) == 0 || whitelistKeys.contains(key)) {
      doLog(log, false, payload, Arrays.stream(suppliers).map(Supplier::get).toArray());
      return true;
    }
    return false;
  }

  public static boolean debugLogWithMethod(Logger log, int percent, String payload,
      Supplier... suppliers) {
    return debugLogWithMethod(null, log, percent, payload, suppliers);
  }

  public static boolean debugLogWithMethod(String key, Logger log, int percent, String payload,
      Supplier... suppliers) {

    if (RandomUtils.nextInt(0, percent) == 0 || whitelistKeys.contains(key)) {
      doLog(log, true, payload, Arrays.stream(suppliers).map(Supplier::get).toArray());
      return true;
    }
    return false;
  }

  public static boolean debugLogWithMethod(Logger log, int percent, String payload,
      Object... objects) {
    return debugLogWithMethod(null, log, percent, payload, objects);
  }

  public static boolean debugLogWithMethod(String key, Logger log, int percent, String payload,
      Object... objects) {

    if (RandomUtils.nextInt(0, percent) == 0 || whitelistKeys.contains(key)) {
      doLog(log, true, payload, objects);
      return true;
    }
    return false;
  }

  private static void doLog(Logger log, boolean isPrintMethod, String payload, Object... objects) {
    if (!isPrintMethod) {
      log.info(payload, objects);
      return;
    }

    payload = DEFAULT_METHOD_PAYLOAD + payload;

    String className = Thread.currentThread().getStackTrace()[4].getClassName();
    String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

    Builder<Object> builder = ImmutableList.builder();
    builder.add(ClassUtils.getShortClassName(className));
    builder.add(methodName);
    builder.addAll(Arrays.stream(objects).collect(Collectors.toList()));

    log.info(payload, builder.build().toArray());
  }

}
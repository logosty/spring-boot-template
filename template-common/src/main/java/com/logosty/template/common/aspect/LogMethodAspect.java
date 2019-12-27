package com.logosty.template.common.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;
import com.logosty.template.common.aspect.annotation.LogMethod;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StopWatch;

/**
 * @author logosty(ganyingle)
 * @date 2019/12/17 20:06
 */
@Component
@Aspect
@Slf4j
public class LogMethodAspect {

  private static ImmutableMap<Integer, String> repeatStringMap;

  {
    ImmutableMap.Builder<Integer, String> builder = ImmutableMap.builder();
    String base = "[{}] ";

    builder.put(0, "");
    for (int i = 1; i < 10; i++) {
      builder.put(i, StringUtils.repeat(base, i));
    }
    repeatStringMap = builder.build();
  }


  @Pointcut("@annotation(com.logosty.template.common.aspect.annotation.LogMethod)")
  private void pointcut() {
  }

  /**
   * 定制一个环绕通知
   */
  @Around("pointcut() && @annotation(logMethod)")
  public void advice(ProceedingJoinPoint joinPoint, LogMethod logMethod) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    String targetName = joinPoint.getTarget().getClass().getName(); // 目标类全路径名
    String shortName = ClassUtils.getShortName(targetName);
    String methodName = joinPoint.getSignature().getName(); // 目标方法名（正在访问的方法）

    Object[] arguments = joinPoint.getArgs();
    String argString = repeatStringMap.get(arguments.length);

    try {
      if (logMethod.beginPercent() > 0 && RandomUtils.nextInt(0, logMethod.beginPercent()) == 0) {
        log.info("##begin--> [{}] do [{}] with: " + argString,
            buildObjects(logMethod.doJson(), arguments, shortName, methodName));
      }
    } catch (Exception e) {
      log.warn("print begin error: [{}]", e.getMessage());
    }

    joinPoint.proceed();

    stopWatch.stop();
    try {
      if (logMethod.endPercent() > 0 && RandomUtils.nextInt(0, logMethod.endPercent()) == 0) {
        log.info("##end--> [{}] do [{}] cost [{}] with: " + argString,
            buildObjects(logMethod.doJson(), arguments, shortName, methodName,
                stopWatch.getTotalTimeMillis()));
      }
    } catch (Exception e) {
      log.warn("print end error: [{}]", e.getMessage());
    }


  }

  public static Object[] buildObjects(boolean doJson, Object[] arguments, Object... objects) {
    Builder<Object> builder = ImmutableList.builder();
    builder.addAll(Arrays.stream(objects).collect(Collectors.toList()));

    if (doJson) {
      builder.addAll(Arrays.stream(arguments).map(JSON::toJSONString).collect(Collectors.toList()));
    } else {
      builder.addAll(Arrays.stream(arguments).collect(Collectors.toList()));
    }

    return builder.build().toArray();
  }
}

package com.logosty.template.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author logosty(ganyingle)
 * @date 2019/12/17 20:02
 * description: 采样打印接口请求参数注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogMethod {

  /**
   * do not print when value <= 0
   * use RandomUtils.nextInt(0, logMethod.endPercent()) == 0)
   */
  int beginPercent() default 1;

  /**
   * do not print when value <= 0
   * use RandomUtils.nextInt(0, logMethod.endPercent()) == 0)
   */
  int endPercent() default 1;

  boolean doJson() default true;

}

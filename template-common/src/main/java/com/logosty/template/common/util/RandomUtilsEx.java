package com.logosty.template.common.util;

import com.alibaba.fastjson.JSON;
import java.util.Collection;
import java.util.function.ToDoubleFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author logosty(ganyingle) on 2019/10/16 11:32
 * description: 权重采样工具
 */
@Slf4j
public class RandomUtilsEx {

  private static final double EPS = 1e-8;

  /**
   * 带权随机
   *
   * @param objs 待随机取的集合
   * @param weightMapper 获取权重的方法
   * @param <T> 集合类型
   * @return 随机结果
   */
  public static <T> T next(Collection<T> objs, ToDoubleFunction<T> weightMapper) {

    double sum = objs.stream().mapToDouble(weightMapper).sum();

    double point = RandomUtils.nextDouble(0, sum);
    double preSum = .0;

    for (T obj : objs) {
      preSum += weightMapper.applyAsDouble(obj);
      if (preSum + EPS > point) {
        // + EPS 表示 double 精度下的 >=
        return obj;
      }
    }

    log.error("not found next objs: {}, point: {}", JSON.toJSONString(objs), point);
    return null;
  }

}

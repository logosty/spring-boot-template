package com.logosty.template.common.util;

import java.util.concurrent.Executor;

/**
 * @author logosty(ganyingle) on 2019/9/27 16:04
 * description: 线程池执行工具类
 */
public class ExecutorUtils {

  public static void exec(Executor executor, Runnable runnable) {
    try {
      executor.execute(runnable);
    } catch (Exception e) {
      runnable.run();
    }
  }
}

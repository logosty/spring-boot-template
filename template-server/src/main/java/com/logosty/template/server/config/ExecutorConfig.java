package com.logosty.template.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 15:32 description: 线程池配置
 */
@Configuration
public class ExecutorConfig {
  @Bean(name = "templateExecutor")
  public AsyncTaskExecutor templateExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(30);
    executor.setMaxPoolSize(60);
    executor.setQueueCapacity(-1);
    executor.setKeepAliveSeconds(60);
    executor.setThreadNamePrefix("TemplateExecutor-");
    executor.initialize();
    return executor;
  }

  @Bean(name = "sageExecutor")
  public AsyncTaskExecutor sageExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(100);
    executor.setMaxPoolSize(200);
    executor.setQueueCapacity(-1);
    executor.setKeepAliveSeconds(60);
    executor.setThreadNamePrefix("SageExecutor-");
    executor.initialize();
    return executor;
  }
}

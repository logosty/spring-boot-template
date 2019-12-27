package com.logosty.template.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 带概率的责任链
 *
 * @author logosty(ganyingle) on 2019/11/29 17:34
 */
@Slf4j
public class ChainOfResponsibilityUtils {

  public static <T> GetHandlerProcessor<T> newGetInstance() {
    return GetHandlerProcessor.newInstance();
  }

  public static class GetHandlerProcessor<T> {

    private List<Handler<T>> handlers = new ArrayList<>();
    private Handler<T> last = null;
    private Predicate<T> predicate = (o) -> true;
    private int logPercent = 1000;

    public static <T> GetHandlerProcessor<T> newInstance() {
      return new GetHandlerProcessor<>();
    }

    public GetHandlerProcessor<T> setPredicate(Predicate<T> predicate) {
      this.predicate = predicate;
      return this;
    }

    public GetHandlerProcessor<T> setLogPercent(int logPercent) {
      this.logPercent = logPercent;
      return this;
    }

    public GetHandlerProcessor<T> setNextHandler(double ratio, Supplier<T> supplier,
        String handlerName) {
      Handler<T> handler = Handler.<T>builder()
          .predicate(this.predicate)
          .logPercent(this.logPercent)
          .ratio(ratio)
          .supplier(supplier)
          .name("handler:" + handlers.size() + ":" + handlerName)
          .build();
      handlers.add(handler);
      if (last != null) {
        last.setNext(handler);
      }
      last = handler;
      return this;
    }

    public GetHandlerProcessor<T> setNextHandler(double ratio, Supplier<T> supplier) {
      return this.setNextHandler(ratio, supplier, "");
    }

    public T get() {
      return this.getResult();
    }

    public T requireNonNullElseGet(Supplier<T> orElseGetter) {
      return Objects.requireNonNullElseGet(this.getResult(), orElseGetter);
    }

    private T getResult() {
      Handler<T> handler = RandomUtilsEx.next(handlers, Handler::getRatio);
      if (handler == null) {
        return null;
      }
      return handler.get();
    }

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Handler<T> {

    private String name;
    private int logPercent;

    private double ratio;
    private Handler<T> next;

    private Predicate<T> predicate;
    private Supplier<T> supplier;

    private Runnable runnable;

    public T get() {
      T items = supplier.get();
      if (items != null && predicate.test(items)) {

        DebugLogUtils.debugLog(log, logPercent, "handler [{}] do [{}] used ratio [{}]",
            name, Thread.currentThread().getStackTrace()[1].getMethodName(), ratio);

        return items;
      }

      return next == null ? null : next.get();
    }

  }

}



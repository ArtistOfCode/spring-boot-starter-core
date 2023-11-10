package com.codeartist.component.core.support.trace;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 默认链路追踪实现
 *
 * @author J.N.AI
 * @date 2023/8/4
 */
public class NoopTracers implements Tracers {

    @Override
    public <T> T startSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> extractor) {
        return supplier.get();
    }

    @Override
    public <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier) {
        return supplier.get();
    }

    @Override
    public <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> injector) {
        return supplier.get();
    }
}

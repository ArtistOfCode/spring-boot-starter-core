package com.codeartist.component.core.support.trace;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 链路追踪
 *
 * @author J.N.AI
 * @date 2023/8/4
 */
public interface Tracers {

    <T> T startSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> extractor);

    <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier);

    <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> injector);
}

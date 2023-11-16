package com.codeartist.component.core.support.trace;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 链路追踪
 * <p>
 * TODO 修改链路追踪封装，现在的实现不清晰
 *
 * @author AiJiangnan
 * @date 2023/8/4
 */
public interface Tracers {

    <T> T startSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> extractor);

    <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier);

    <T> T startScopedSpan(String name, Map<String, String> tags, Supplier<T> supplier, Map<String, String> injector);
}

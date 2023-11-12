package com.codeartist.component.core.test.util;

import com.codeartist.component.core.entity.ICode;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.core.support.test.AbstractJUnit5RunnerTests;
import com.codeartist.component.core.test.entity.DemoCode;
import com.codeartist.component.core.util.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * 业务断言测试类
 *
 * @author J.N.AI
 * @date 2023-11-12
 */
class AssertTest extends AbstractJUnit5RunnerTests {

    private static final String defaultMessage = "Test message";
    private static final ICode defaultCode = new DemoCode();

    @Test
    void state() {
        assertDoesNotThrow(() -> Assert.state(false, defaultMessage));
        assertDoesNotThrow(() -> Assert.state(false, defaultCode));
        assertDoesNotThrow(() -> Assert.state(false, () -> new IllegalStateException(defaultMessage)));

        assertThrows(BusinessException.class, () -> Assert.state(true, defaultMessage));
        assertThrows(BusinessException.class, () -> Assert.state(true, defaultCode));
        assertThrows(IllegalStateException.class, () -> Assert.state(true,
                () -> new IllegalStateException(defaultMessage)));
    }

    @Test
    void notNull() {
        assertDoesNotThrow(() -> Assert.notNull(new Object(), defaultMessage));
        assertDoesNotThrow(() -> Assert.notNull(new Object(), defaultCode));
        assertDoesNotThrow(() -> Assert.notNull(new Object(), () -> new IllegalStateException(defaultMessage)));

        assertThrows(BusinessException.class, () -> Assert.notNull(null, defaultMessage));
        assertThrows(BusinessException.class, () -> Assert.notNull(null, defaultCode));
        assertThrows(IllegalStateException.class, () -> Assert.notNull(null,
                () -> new IllegalStateException(defaultMessage)));
    }

    @Test
    void notEmpty() {
        assertDoesNotThrow(() -> Assert.notEmpty(Collections.singleton("ele"), defaultMessage));
        assertDoesNotThrow(() -> Assert.notEmpty(Collections.singleton("ele"), defaultCode));
        assertDoesNotThrow(() -> Assert.notEmpty(Collections.singleton("ele"),
                () -> new IllegalStateException(defaultMessage)));

        assertThrows(BusinessException.class, () -> Assert.notEmpty(Collections.emptySet(), defaultMessage));
        assertThrows(BusinessException.class, () -> Assert.notEmpty(Collections.emptySet(), defaultCode));
        assertThrows(IllegalStateException.class, () -> Assert.notEmpty(Collections.emptySet(),
                () -> new IllegalStateException(defaultMessage)));
    }

    @Test
    void isEmpty() {
        assertDoesNotThrow(() -> Assert.isEmpty(Collections.emptySet(), defaultMessage));
        assertDoesNotThrow(() -> Assert.isEmpty(Collections.emptySet(), defaultCode));
        assertDoesNotThrow(() -> Assert.isEmpty(Collections.emptySet(),
                () -> new IllegalStateException(defaultMessage)));

        assertThrows(BusinessException.class, () -> Assert.isEmpty(Collections.singleton("ele"), defaultMessage));
        assertThrows(BusinessException.class, () -> Assert.isEmpty(Collections.singleton("ele"), defaultCode));
        assertThrows(IllegalStateException.class, () -> Assert.isEmpty(Collections.singleton("ele"),
                () -> new IllegalStateException(defaultMessage)));
    }

    @Test
    void notBlank() {
        assertDoesNotThrow(() -> Assert.notBlank("ele", defaultMessage));
        assertDoesNotThrow(() -> Assert.notBlank("ele", defaultCode));
        assertDoesNotThrow(() -> Assert.notBlank("ele",
                () -> new IllegalStateException(defaultMessage)));

        assertThrows(BusinessException.class, () -> Assert.notBlank(" ", defaultMessage));
        assertThrows(BusinessException.class, () -> Assert.notBlank(" ", defaultCode));
        assertThrows(IllegalStateException.class, () -> Assert.notBlank(" ",
                () -> new IllegalStateException(defaultMessage)));
    }
}

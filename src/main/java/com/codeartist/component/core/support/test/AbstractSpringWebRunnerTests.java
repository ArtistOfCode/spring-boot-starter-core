package com.codeartist.component.core.support.test;

import org.mockito.BDDMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.nio.charset.StandardCharsets;

/**
 * SpringBoot Web单元测试
 *
 * @author AiJiangnan
 * @date 2020/7/15
 */
@ActiveProfiles({"junit", "local"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractSpringWebRunnerTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MockMvc mockMvc;

    protected <T> void mock(T t, T ret) {
        BDDMockito.given(t).willReturn(ret);
    }

    protected ResultHandler print() {
        return result -> logger.info(new String(result.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8));
    }
}

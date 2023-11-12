package com.codeartist.component.core.support.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * SpringBoot单元测试
 *
 * @author AiJiangnan
 * @date 2020/7/15
 */
@ActiveProfiles({"junit", "local"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractSpringRunnerTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
}

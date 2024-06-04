package com.codeartist.component.core.entity.enums;

import com.codeartist.component.core.SpringContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 环境配置
 *
 * @author AiJiangnan
 * @date 2022/7/15
 */
@AllArgsConstructor
public enum Environments {

    LOCAL(ProfileConst.LOCAL_PROFILE, Group.LOCAL_GROUP, "global.environment.local"),
    TEST(ProfileConst.TEST_PROFILE, Group.TEST_GROUP, "global.environment.test"),
    PROD(ProfileConst.PROD_PROFILE, Group.PROD_GROUP, "global.environment.prod"),
    ;

    @Getter
    private final String profile;
    @Getter
    private final Group group;
    private final String name;

    public boolean not() {
        return !is();
    }

    public boolean is() {
        return SpringContext.acceptsProfiles(this.getProfile());
    }

    public String getName() {
        return SpringContext.getMessage(this.name, new Object[0]);
    }

    @Getter
    @AllArgsConstructor
    public enum Group {
        LOCAL_GROUP("local"),
        TEST_GROUP("test"),
        PROD_GROUP("prod"),
        ;

        private final String name;
    }

    public interface ProfileConst {

        String LOCAL_PROFILE = "local";
        String TEST_PROFILE = "test";
        String PROD_PROFILE = "prod";
        String NOT_PROD_PROFILE = "!prod";
    }
}

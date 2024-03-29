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
@Getter
@AllArgsConstructor
public enum Environments {

    LOCAL(ProfileConst.LOCAL_PROFILE, Group.LOCAL_GROUP, "本地环境"),
    TEST(ProfileConst.TEST_PROFILE, Group.TEST_GROUP, "测试环境"),
    PROD(ProfileConst.PROD_PROFILE, Group.PROD_GROUP, "生产环境"),
    ;

    private final String profile;
    private final Group group;
    private final String name;

    public boolean not() {
        return !is();
    }

    public boolean is() {
        return SpringContext.acceptsProfiles(this.getProfile());
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

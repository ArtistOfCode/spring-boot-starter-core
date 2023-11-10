package com.codeartist.component.core.entity.enums;

import com.codeartist.component.core.util.SpringContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 环境配置
 *
 * @author 艾江南
 * @date 2022/7/15
 */
@Getter
@AllArgsConstructor
public enum Environments {

    LOCAL(ProfileConst.LOCAL, ProfileGroup.LOCAL, "Local"),
    TEST(ProfileConst.TEST, ProfileGroup.TEST, "Test"),
    PROD(ProfileConst.PROD, ProfileGroup.PROD, "Prod"),
    ;

    private final String profile;
    private final String group;
    private final String name;

    public boolean not() {
        return !is();
    }

    public boolean is() {
        return SpringContext.acceptsProfiles(this.getProfile());
    }

    /**
     * 环境组
     */
    public interface ProfileGroup {

        String LOCAL = "local";
        String TEST = "test";
        String PROD = "prod";
        String NOT_PROD = "!prod";
    }

    public interface ProfileConst {

        String LOCAL = "local";
        String TEST = "test";
        String PROD = "prod";
    }
}

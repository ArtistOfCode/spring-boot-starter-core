package com.codeartist.component.core.support.auth;

import com.codeartist.component.core.entity.enums.Constants;
import com.codeartist.component.core.util.Assert;
import com.codeartist.component.core.util.WebUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 默认权限上下文实现
 *
 * @author AiJiangnan
 * @date 2023-11-12
 */
public class DefaultAuthContext implements AuthContext {

    @Override
    public Long getRequiredUserId() {
        String userId = WebUtils.getRequestHeader(Constants.USER_ID_HEADER);
        Assert.notBlank(userId, "UserId is null.");
        return Long.valueOf(userId);
    }

    public Long getUserId() {
        String userId = WebUtils.getRequestHeader(Constants.USER_ID_HEADER);
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return Long.valueOf(userId);
    }
}

package com.codeartist.component.core.support.auth;

/**
 * 权限上下文
 *
 * @author J.N.AI
 * @date 2023-11-12
 */
public interface AuthContext {

    Long getUserId();

    Long getNullableUserId();
}

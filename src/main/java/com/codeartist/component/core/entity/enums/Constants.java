package com.codeartist.component.core.entity.enums;

/**
 * 全局常量
 *
 * @author AiJiangnan
 * @date 2020/10/10
 */
public interface Constants {

    String DEFAULT = "default";
    String SESSION_ID_COOKIE = "JSESSIONID";
    String TOKEN_HEADER = "token";
    String USER_ID_HEADER = "UserId";

    enum EntityEventType {
        SAVE, UPDATE, DELETE
    }
}

package com.codeartist.component.core.entity.enums;

/**
 * 全局常量
 *
 * @author AiJiangnan
 * @date 2020/10/10
 */
public interface Constants {

    String APPLICATION_NAME_KEY = "${spring.application.name}";
    String ROOT_PACKAGE_KEY = "${spring.application.root-package}";
    String MODULE_PACKAGE_KEY = "#{'${spring.application.module-package:}'.split(',')}";

    String DEFAULT = "default";
    String SESSION_ID_COOKIE = "JSESSIONID";
    String TOKEN_HEADER = "token";
    String USER_ID_HEADER = "UserId";
    String LIMIT_1_SQL = "LIMIT 1";

    interface RedisKey {
        String CAPTCHA_KEY = "CAPTCHA:";
    }
}

package com.codeartist.component.core.support.props;

import com.codeartist.component.core.entity.enums.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 全局配置
 *
 * @author AiJiangnan
 * @date 2023-12-15
 */
@Data
public class GlobalProperties {

    @Value(Constants.APPLICATION_NAME_KEY)
    private String appName;

    @Value(Constants.ROOT_PACKAGE_KEY)
    private String rootPackage;
}

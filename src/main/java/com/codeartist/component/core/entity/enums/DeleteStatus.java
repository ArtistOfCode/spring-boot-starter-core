package com.codeartist.component.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除状态
 */
@Getter
@AllArgsConstructor
public enum DeleteStatus {

    UNDELETED(0), DELETED(1);

    private final int status;
}

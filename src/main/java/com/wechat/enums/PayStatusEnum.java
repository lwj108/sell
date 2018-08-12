package com.wechat.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 * @author liweijun
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;

    private String msg;

    PayStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

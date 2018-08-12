package com.wechat.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * <T> 范型
 * VO ViewObject
 */
@Data
public class ResultVO<T> {
    /** 错误吗 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 存放productVO*/
    private T data;

}

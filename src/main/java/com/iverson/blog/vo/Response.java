package com.iverson.blog.vo;

import lombok.Data;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Data
public class Response {

    /** 处理是否成功 */
    private boolean success;
    /** 处理后消息提示 */
    private String message;
    /** 返回的数据 */
    private Object body;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
}

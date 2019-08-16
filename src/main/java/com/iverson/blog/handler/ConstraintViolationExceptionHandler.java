package com.iverson.blog.handler;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 字段约束异常处理器
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public class ConstraintViolationExceptionHandler {

    /**
     * 获取批量异常信息
     * @param exception
     * @return
     */
    public static String getMesssage(ConstraintViolationException exception) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}

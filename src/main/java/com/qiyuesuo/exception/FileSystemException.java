package com.qiyuesuo.exception;

/**
 * Auth0r : fangbofei
 * Date : 2018/8/3/0003
 * Time : 10:21
 */
public class FileSystemException extends RuntimeException {

    /**
     * 自定义异常信息处理
     */
    private String message;

    public FileSystemException(String message) {
        super(message);
    }


}

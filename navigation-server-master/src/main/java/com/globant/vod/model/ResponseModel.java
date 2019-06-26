/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import org.springframework.http.HttpStatus;

import lombok.ToString;

/**
 * @author rohitkumar.patel
 */
@ToString
public class ResponseModel {
    private HttpStatus code;

    private String msg;

    public ResponseModel(String msg, HttpStatus code) {
        this.msg = msg;
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

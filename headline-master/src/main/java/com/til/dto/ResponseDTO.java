package com.til.dto;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Created by Satya on 31-07-2018.
 */
public class ResponseDTO<T> {
    String message;
    T data;
    HttpStatus status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}

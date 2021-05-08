package com.pavan.secrets.exception;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class APIException extends RuntimeException{

    private static final long serialVersionUID = 2250067449378604388L;

    private HttpStatus httpStatus;
    private String message;
    private Throwable cause;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public APIException(String message, Throwable cause, HttpStatus httpStatus) {
        this.message =message;
        this.cause = cause;
        this.httpStatus = httpStatus;

    }
    public APIException(String message, HttpStatus httpStatus) {
        this.message =message;
        this.httpStatus = httpStatus;

    }
    public APIException(String message) {
        this.message =message;
    }
    public APIException() {
        super();
    }

    @Override
    public String toString() {
        return "APIException{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", cause=" + cause +
                '}';
    }
}

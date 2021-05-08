package com.pavan.secrets.utility;

import com.pavan.secrets.exception.APIException;
import org.springframework.http.HttpStatus;


public class APIExceptionUtil {

    /**
     * This is the method which handler the Api Exception Object generated
     * @param args message,httpStatus,cause
     * @return APIException
     */

    public static APIException populateAPIException(String message, HttpStatus httpStatus, Throwable cause){
        return new APIException(message,cause,httpStatus);
    }

    public static APIException populateAPIException(String message,HttpStatus httpStatus){
        return new APIException(message,httpStatus);
    }
}

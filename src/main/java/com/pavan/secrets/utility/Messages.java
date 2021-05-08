package com.pavan.secrets.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    /**
     * This is the method to initialize the Message source Accessor
     */
    @PostConstruct
    private void init(){
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }


    /**
     * This is the method used to get the message based upon its code
     */
    public String get(String code){
        return accessor.getMessage(code);
    }
}

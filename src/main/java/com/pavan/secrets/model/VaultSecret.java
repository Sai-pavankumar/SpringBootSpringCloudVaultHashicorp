package com.pavan.secrets.model;

import org.springframework.stereotype.Component;

@Component
public class VaultSecret {

    private String key;
    private String value;

    public String getKey(){
        return  key;
    }

    public void setKey(String key){
        this.key=key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){
        this.value=value;
    }


    public String toString(){
        return  "SecretKey{"+"secretKeyName='"+key+'\''+"secretKeyValue='"+value+'\''+'}';
    }
}

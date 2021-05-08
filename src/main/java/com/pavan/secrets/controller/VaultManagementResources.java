package com.pavan.secrets.controller;

import com.pavan.secrets.handler.VaultManagementHandler;
import com.pavan.secrets.model.VaultSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@ComponentScan("com/pavan/secrets")
public class VaultManagementResources extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(VaultManagementResources.class);

    @Autowired
    VaultManagementHandler vaultManagementHandler;

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path","/");
        SpringApplication.run(VaultManagementResources.class,args);
    }

    /** This method which takes the get Request with key as param and returns json string with key and value
     *
     * @param key
     * @return String key and value
     */
    @GetMapping("/vault/secret")
    public VaultSecret getVaultSecret(@RequestParam("key") String key){
        logger.info("Request came to getVaultSecret with key as parameter.."+key);
        return vaultManagementHandler.getVaultSecret(key);
    }

    /** This method which takes the post Request with request body as key and value json string
     *
     * @param key,value
     * @return Success
     */
    @PostMapping("vault/secret")
    public Map<String,String> createVaultSecret(@RequestBody List<VaultSecret> secretKeys){
        logger.info("Request came to createVaultSecret with Secret key and value as parameters.."+secretKeys);
        return vaultManagementHandler.createVaultSecret(secretKeys);
    }
}

package com.pavan.secrets.config;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class VaultApplicationProperties {

    private static final Logger logger = LoggerFactory.getLogger(VaultApplicationProperties.class);

    /** This is the property for HashiCorp application name */

    @Value("${spring.application.name}")
    private String applicationName;

    /** This is the property for HashiCorp Vault token */

    @Value("${spring.cloud.vault.token}")
    private String vaultToken;

    /** This is the property for HashiCorp Vault URI */

    @Value("${spring.cloud.vault.uri}")
    private String vaultUri;


    /**
     * This is the bean which helps to read HashiCorp Uri
     * @param args
     * @return HashiCorp Vault Uri
     */
    @Bean(name="vaultUri")
    public String getVaultUri(){
        return  vaultUri;
    }


    /**
     * This is the bean which helps to read HashiCorp Vault token
     * @param args
     * @return HashiCorp Vault token
     */
    @Bean(name="vaultToken")
    public String getVaultToken(){
        return  vaultToken;
    }


    /**
     * This is the bean which helps to read HashiCorp Application name
     * @param args
     * @return HashiCorp Application Name
     */
    @Bean(name="applicationName")
    public String getApplicationName(){
        return  applicationName;
    }
}

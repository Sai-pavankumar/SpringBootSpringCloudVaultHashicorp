package com.pavan.secrets.handler;

import com.pavan.secrets.config.VaultApplicationProperties;
import com.pavan.secrets.model.VaultSecret;
import com.pavan.secrets.utility.APIExceptionUtil;
import com.pavan.secrets.utility.Messages;
import com.pavan.secrets.utility.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.vault.VaultException;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.repository.convert.SecretDocument;
import org.springframework.vault.support.VaultResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class VaultManagementHandler {

    private static final Logger logger = LoggerFactory.getLogger(VaultManagementHandler.class);

    @Autowired
    VaultTemplate vaultTemplate;
    @Autowired
    VaultApplicationProperties vaultProperties;
    @Autowired
    VaultSecret vaultSecret;
    @Autowired
    Messages messages;

    public VaultSecret getVaultSecret(String key) {
        logger.info("Request came to Handler method for getVaultSecret details based on parameter as key");
        try {
            VaultResponse response = getVaultResponse();
            SecretDocument secretDocument = new SecretDocument(Objects.requireNonNull(response.getData()));
            String secretValue = secretDocument.getBody().get(key).toString();
            if(!secretValue.isEmpty()){
                vaultSecret.setKey(key);
                vaultSecret.setValue(secretValue);
            }
            return vaultSecret;
        }catch (Exception exception){
            String message = messages.get("vault.access.key");
            logger.error(message,exception.getMessage());
            throw APIExceptionUtil.populateAPIException(message, HttpStatus.BAD_REQUEST,exception);
        }
    }

    public Map<String, String> createVaultSecret(List<VaultSecret> secrets) {
        logger.info("Request came to createVaultSecret in Handler class");
        if(!ValidationUtil.isEmpty(secrets)) {
            try{
            VaultResponse response = getVaultResponse();
            SecretDocument secretDocument = new SecretDocument(Objects.requireNonNull(response.getData()));
                Map<String, Object> mapObj = new HashMap<>(secretDocument.getBody());
                secrets.forEach(secretVault-> mapObj.put(secretVault.getKey(),secretVault.getValue()));
            vaultTemplate.write(vaultProperties.getApplicationName(), mapObj);
            return !Objects.equals(mapObj.size(), 0)?Collections.singletonMap("Status", "Success"):Collections.singletonMap("Status", "Failure");
        }
        catch (VaultException vaultException){
            String message = messages.get("vault.input.save");
            logger.error(message,vaultException.getMessage());
            throw APIExceptionUtil.populateAPIException(message,HttpStatus.BAD_REQUEST,vaultException);
        }
        }else{
            throw APIExceptionUtil.populateAPIException(messages.get("vault.secret.empty"),HttpStatus.BAD_REQUEST);
        }
    }

    private VaultResponse getVaultResponse()  {
        try{
            vaultTemplate = new VaultTemplate(
                    VaultEndpoint.from(new URI(vaultProperties.getVaultUri())),new TokenAuthentication(vaultProperties.getVaultToken()));

        }catch (URISyntaxException uriSyntaxException){
            String message = messages.get("vault.invalid.uri");
            logger.error(message,uriSyntaxException.getMessage());
            throw APIExceptionUtil.populateAPIException(message, HttpStatus.BAD_REQUEST,uriSyntaxException);
        }
        return vaultTemplate.read(vaultProperties.getApplicationName());
    }
}

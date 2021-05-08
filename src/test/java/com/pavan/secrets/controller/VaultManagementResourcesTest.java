package com.pavan.secrets.controller;

import com.pavan.secrets.model.VaultSecret;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VaultManagementResourcesTest extends AbstractControllerTest{

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    @Order(1)
    public void testCreateVaultSecret() throws Exception{
        String uri="/vault/secret";

        VaultSecret vaultSecret = new VaultSecret();
        List<VaultSecret> secrets = new ArrayList<>();
        vaultSecret.setKey("testUser");
        vaultSecret.setValue("testPassword");

        String inputJson = super.mapToJson(secrets);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                               .contentType(MediaType.APPLICATION_JSON)
                              .contentType(inputJson)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure -excepted HTTP Status",200,status);
        Assert.assertTrue("failure - expected HTTP response body to have a value",secrets.size()>0);
    }

    @Test
    @Order(2)
    public void testGetVaultSecret() throws Exception{
        String key="testUser";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/vault/secret?key="+key)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure -excepted HTTP Status",200,status);
        Assert.assertTrue("failure - expected HTTP response body to have a value",content.trim().length()>0);
    }
}

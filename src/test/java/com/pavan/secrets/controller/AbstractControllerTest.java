package com.pavan.secrets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *  This is extends the functionality of {@link AbstractTest}.AbstractControllerTest
 *  is the parent of all web controller unit test classes.The class ensures that a type
 *  of WebApplicationContext is built and prepares a MockMvc instance for use in test methods
 */

@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {

    protected MockMvc mvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    /**
     *  Prepare the test class for execution of web tests.Builds a MockMvc instance.
     *  Call this method from the concrete Junit test class in the
     *  <code>@Before</code> setup method
     */

    protected void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    /**
     * Prepares the test class for execution of web tests.builds a MockMvc instance using
     * standalone configuration facilitating the injection of Mockito resources into the
     * Controller class
     * @param controller A controller object to be tested
     */

    protected void setup(VaultManagementResources controller){
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    /** Maps an object into a JSON Strina,Uses a Jackson ObjectMapper
     *
     * @param object The Object to map
     * @return A String of JSON
     * @throws JsonProcessingException Thrown if an error occurs while mapping
     *
     */

    protected String mapToJson(Object object)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * Maps s String of JSON into an instance of a class type T. uses a
     * Jackson ObjectMapper
     *
     * @param json A String of JSON
     * @param clazz A class Type T.The mapper will attempt to convert the Json
     *              into an object of the class type
     * @return An Object of type T
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws JsonProcessingException
     *
     * */

    protected <T> T mapFromJson(String json,Class<T> clazz) throws JsonProcessingException, JsonParseException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json,clazz);
    }

}

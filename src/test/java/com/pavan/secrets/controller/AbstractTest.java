package com.pavan.secrets.controller;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This AbstractTest class is the parent of all junit test class.This class configure
 * the test ApplicationContext and test runner environment
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=VaultManagementResources.class)
public abstract class AbstractTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}

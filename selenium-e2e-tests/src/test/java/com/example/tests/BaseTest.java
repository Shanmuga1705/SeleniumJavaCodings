package com.example.tests;

import org.openqa.selenium.WebDriver;
import com.example.utils.WebDriverUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverUtils.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverUtils.quitDriver();
    }
}
package com.example.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {
    
    @Test
    public void testLogin() {
        // Navigate to the login page
        driver.get("https://rahulshettyacademy.com/client");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("userEmail")).sendKeys("testE2Eflow@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("testE2Eflow@123");
        driver.findElement(By.id("login")).click();
    }

}

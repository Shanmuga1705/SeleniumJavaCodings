package com.example.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {
    
    @Test
    public void testLogin() {
        String productName = "ADIDAS ORIGINAL";
        driver.get("https://rahulshettyacademy.com/client");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("userEmail")).sendKeys("testE2Eflow@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("testE2Eflow@123");
        driver.findElement(By.id("login")).click();
        waitForElementToBeVisible(By.cssSelector(".mb-3"), 10);
        List<WebElement> elements = driver.findElements(By.cssSelector(".mb-3"));


        for (WebElement element : elements) {
            String text = element.findElement(By.cssSelector("b")).getText();
            if (text.equals(productName)) {
                //.card-body button.btn.w-10.rounded
                element.findElement(By.cssSelector(".card-body button:last-of-type")).click();
                break;
            }
        }

        waitForElementToBeVisible(By.cssSelector("#toast-container"), 10);
        
        // Wait for loading spinner to disappear
        By spinnerLocator = By.cssSelector(".ngx-spinner-overlay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        
        driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
    }
}

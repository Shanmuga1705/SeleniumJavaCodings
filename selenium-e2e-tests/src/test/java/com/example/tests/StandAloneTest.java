package com.example.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {
    
    @Test
    public void testLogin() {
        String[] productNames = {"ADIDAS ORIGINAL","IPHONE 13 PRO"};
        driver.get("https://rahulshettyacademy.com/client");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("userEmail")).sendKeys("testE2Eflow@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("testE2Eflow@123");
        driver.findElement(By.id("login")).click();
        
        waitForElementToBeVisible(By.cssSelector(".mb-3"), 10);
        List<WebElement> elements = driver.findElements(By.cssSelector(".mb-3"));

        By spinnerLocator = By.cssSelector(".ngx-spinner-overlay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement element : elements) {
            String text = element.findElement(By.cssSelector("b")).getText();
            for (String productName : productNames) {
                if (text.equals(productName)) {
                    // Wait for spinner to disappear if present
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
                    
                    // Wait for button to be clickable
                    WebElement addButton = element.findElement(By.cssSelector(".card-body button:last-of-type"));
                    wait.until(ExpectedConditions.elementToBeClickable(addButton));
                    addButton.click();
                    
                    // Wait for toast and spinner
                    waitForElementToBeVisible(By.cssSelector("#toast-container"), 10);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
                    break;
                }
            }
        }

        // Wait for final spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        
        // Wait for cart button to be clickable
        WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*=cart]"));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();

        // Verify cart products
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        for (String productName : productNames) {
            boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
            assert match;
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        
        //js.executeScript("arguments[0].scrollIntoView();", checkoutBtn);
        By checkoutBtn = By.cssSelector(".totalRow button");
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(checkoutBtn));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        driver.findElement(checkoutBtn).click();

      
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
        for (WebElement country : countries) {
            if (country.getText().equals(" India")) {
                country.click();
                break;
            }
        }
        Actions ac = new Actions(driver);
        ac.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
        waitForElementToBeVisible(By.cssSelector(".ta-results"), 10);
        driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
        //equivalent xpath //button[contains(@class,'ta-item')][last()]or[2]
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        assert confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
        //Assert.AssertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        //Assert.AssertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
}

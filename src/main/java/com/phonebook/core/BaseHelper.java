package com.phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseHelper {

    Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    protected WebDriver driver;
    public WebDriverWait wait;

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isElementPresent(By locator) {
   // logger.info("Checking whether the [By.xpath: //button[.='Sign Out']] element is on the page");
        return driver.findElements(locator).size() > 0;
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
        //    logger.info("["+ locator +"] is pressed");
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }

    }

    public boolean isAlertPresent() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (alert == null) {
                return false;
            } else {
                logger.warn("Alert has text: [" + driver.switchTo().alert().getText() + "]");
                //driver.switchTo().alert().accept();
                alert.accept();
                return true;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            //throw new RuntimeException(e);
        }
        return false;
    }


    public String alertTextPresent() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public String takeScreenshot() {
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(tmp.toPath(), screenshot.toPath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }
}

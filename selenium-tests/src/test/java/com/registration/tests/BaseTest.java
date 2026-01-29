package com.registration.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base test class with common setup and utility methods
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected String screenshotDir = "test-screenshots";

    @BeforeClass
    public void setUp() {
        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        // Initialize WebDriver
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Set base URL - Update this path to your actual file location
        baseUrl = "file:///C:/Users/priya/OneDrive/Desktop/Form%20automation/index.html";
        
        // Create screenshot directory
        new File(screenshotDir).mkdirs();
        
        System.out.println("=== Test Setup Completed ===");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("=== Browser Closed ===");
        }
    }

    /**
     * Navigate to the registration page
     */
    protected void navigateToRegistrationPage() {
        driver.get(baseUrl);
        System.out.println("Page URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
    }

    /**
     * Take screenshot with given name
     */
    protected void takeScreenshot(String fileName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullFileName = fileName + "_" + timestamp + ".png";
            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotDir + "/" + fullFileName);
            FileUtils.copyFile(source, destination);
            
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Check if element has error class
     */
    protected boolean hasErrorClass(WebElement element) {
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("error");
    }

    /**
     * Check if error message is displayed
     */
    protected boolean isErrorMessageDisplayed(String fieldId) {
        try {
            WebElement errorMsg = driver.findElement(By.id(fieldId + "-error"));
            String classes = errorMsg.getAttribute("class");
            return classes != null && classes.contains("show");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Fill text field
     */
    protected void fillTextField(By locator, String value) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Select dropdown by visible text
     */
    protected void selectDropdown(By locator, String visibleText) {
        WebElement element = waitForElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Check checkbox
     */
    protected void checkCheckbox(By locator) {
        WebElement element = waitForElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * Wait for dropdown to have options
     */
    protected void waitForDropdownOptions(By locator) {
        wait.until(driver -> {
            WebElement dropdown = driver.findElement(locator);
            Select select = new Select(dropdown);
            return select.getOptions().size() > 1; // More than just the placeholder
        });
    }

    /**
     * Check if submit button is disabled
     */
    protected boolean isSubmitButtonDisabled() {
        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        String disabled = submitBtn.getAttribute("disabled");
        return disabled != null && disabled.equals("true");
    }

    /**
     * Get password strength text
     */
    protected String getPasswordStrength() {
        try {
            WebElement strengthText = driver.findElement(By.id("strengthText"));
            return strengthText.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Print test section header
     */
    protected void printSectionHeader(String header) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(header);
        System.out.println("=".repeat(60));
    }
}

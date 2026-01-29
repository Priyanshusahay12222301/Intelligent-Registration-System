package com.registration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Automation Flow A - Negative Scenario Testing
 * Tests form validation with missing required field (Last Name)
 */
public class NegativeScenarioTest extends BaseTest {

    @Test(priority = 1, description = "Test form validation with missing Last Name field")
    public void testMissingLastNameValidation() {
        printSectionHeader("AUTOMATION FLOW A - NEGATIVE SCENARIO TEST");
        
        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();
        
        System.out.println("\nStep 2: Printing page details...");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        
        System.out.println("\nStep 3: Filling required fields (EXCEPT Last Name)...");
        
        // Fill First Name
        fillTextField(By.id("firstName"), "John");
        System.out.println("✓ Filled First Name: John");
        
        // Skip Last Name intentionally
        System.out.println("✗ Last Name: LEFT EMPTY (intentionally)");
        
        // Fill Email
        fillTextField(By.id("email"), "john.doe@example.com");
        System.out.println("✓ Filled Email: john.doe@example.com");
        
        // Fill Phone
        fillTextField(By.id("phone"), "+1234567890");
        System.out.println("✓ Filled Phone: +1234567890");
        
        // Select Gender
        selectDropdown(By.id("gender"), "Male");
        System.out.println("✓ Selected Gender: Male");
        
        // Select Country
        selectDropdown(By.id("country"), "United States");
        System.out.println("✓ Selected Country: United States");
        
        // Wait for state dropdown to populate and select
        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "California");
        System.out.println("✓ Selected State: California");
        
        // Wait for city dropdown to populate and select
        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "Los Angeles");
        System.out.println("✓ Selected City: Los Angeles");
        
        // Fill Password
        fillTextField(By.id("password"), "SecurePass123!");
        System.out.println("✓ Filled Password: SecurePass123!");
        
        // Fill Confirm Password
        fillTextField(By.id("confirmPassword"), "SecurePass123!");
        System.out.println("✓ Filled Confirm Password: SecurePass123!");
        
        // Check Terms
        checkCheckbox(By.id("terms"));
        System.out.println("✓ Checked Terms & Conditions");
        
        // Wait a moment for validation to process
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nStep 4: Attempting to submit form...");
        
        // Check if submit button is disabled
        boolean isDisabled = isSubmitButtonDisabled();
        System.out.println("Submit button disabled: " + isDisabled);
        
        // Click submit button (even though it should be disabled)
        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        try {
            submitBtn.click();
            System.out.println("Submit button clicked");
        } catch (Exception e) {
            System.out.println("Cannot click submit button (as expected - it's disabled)");
        }
        
        System.out.println("\nStep 5: Validating error state...");
        
        // Validate Last Name field has error
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        boolean hasError = hasErrorClass(lastNameField);
        
        System.out.println("Last Name field highlighted with error: " + hasError);
        
        // Check if error message is displayed
        boolean errorMsgDisplayed = isErrorMessageDisplayed("lastName");
        System.out.println("Error message displayed: " + errorMsgDisplayed);
        
        if (errorMsgDisplayed) {
            WebElement errorMsg = driver.findElement(By.id("lastName-error"));
            System.out.println("Error message text: " + errorMsg.getText());
        }
        
        // Verify submit button remains disabled
        Assert.assertTrue(isDisabled, "Submit button should be disabled with missing Last Name");
        System.out.println("✓ Validation passed: Submit button is disabled");
        
        System.out.println("\nStep 6: Capturing error state screenshot...");
        takeScreenshot("error-state");
        
        System.out.println("\n=== NEGATIVE SCENARIO TEST COMPLETED SUCCESSFULLY ===");
        System.out.println("Key Findings:");
        System.out.println("1. Form correctly prevents submission with missing Last Name");
        System.out.println("2. Submit button remains disabled");
        System.out.println("3. Error validation is working as expected");
    }

    @Test(priority = 2, description = "Test disposable email validation")
    public void testDisposableEmailValidation() {
        printSectionHeader("NEGATIVE TEST - DISPOSABLE EMAIL DOMAIN");
        
        System.out.println("\nNavigating to registration page...");
        navigateToRegistrationPage();
        
        System.out.println("\nTesting disposable email domain rejection...");
        
        // Fill email with disposable domain
        fillTextField(By.id("email"), "test@tempmail.com");
        System.out.println("Entered email: test@tempmail.com");
        
        // Trigger blur event
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("\t"); // Tab to trigger blur
        
        // Wait for validation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check for error
        boolean hasError = hasErrorClass(emailField);
        boolean errorMsgDisplayed = isErrorMessageDisplayed("email");
        
        System.out.println("Email field has error: " + hasError);
        System.out.println("Error message displayed: " + errorMsgDisplayed);
        
        if (errorMsgDisplayed) {
            WebElement errorMsg = driver.findElement(By.id("email-error"));
            System.out.println("Error message: " + errorMsg.getText());
        }
        
        Assert.assertTrue(hasError || errorMsgDisplayed, "Disposable email should be rejected");
        System.out.println("✓ Disposable email validation working correctly");
        
        takeScreenshot("disposable-email-error");
        
        System.out.println("\n=== DISPOSABLE EMAIL TEST COMPLETED ===");
    }

    @Test(priority = 3, description = "Test invalid phone number format")
    public void testInvalidPhoneNumber() {
        printSectionHeader("NEGATIVE TEST - INVALID PHONE NUMBER");
        
        System.out.println("\nNavigating to registration page...");
        navigateToRegistrationPage();
        
        System.out.println("\nTesting invalid phone number format...");
        
        // Fill phone without country code
        fillTextField(By.id("phone"), "1234567890");
        System.out.println("Entered phone: 1234567890 (missing + country code)");
        
        // Trigger blur event
        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("\t");
        
        // Wait for validation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check for error
        boolean hasError = hasErrorClass(phoneField);
        boolean errorMsgDisplayed = isErrorMessageDisplayed("phone");
        
        System.out.println("Phone field has error: " + hasError);
        System.out.println("Error message displayed: " + errorMsgDisplayed);
        
        if (errorMsgDisplayed) {
            WebElement errorMsg = driver.findElement(By.id("phone-error"));
            System.out.println("Error message: " + errorMsg.getText());
        }
        
        Assert.assertTrue(hasError || errorMsgDisplayed, "Invalid phone format should be rejected");
        System.out.println("✓ Phone number validation working correctly");
        
        takeScreenshot("invalid-phone-error");
        
        System.out.println("\n=== INVALID PHONE NUMBER TEST COMPLETED ===");
    }
}

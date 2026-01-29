package com.registration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Automation Flow B - Positive Scenario Testing
 * Tests successful form submission with all valid data
 */
public class PositiveScenarioTest extends BaseTest {

    @Test(priority = 1, description = "Test successful registration with all valid data")
    public void testSuccessfulRegistration() {
        printSectionHeader("AUTOMATION FLOW B - POSITIVE SCENARIO TEST");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Filling all fields with VALID data...");

        // Fill First Name
        fillTextField(By.id("firstName"), "John");
        System.out.println("✓ First Name: John");

        // Fill Last Name
        fillTextField(By.id("lastName"), "Doe");
        System.out.println("✓ Last Name: Doe");

        // Fill Email
        fillTextField(By.id("email"), "john.doe@example.com");
        System.out.println("✓ Email: john.doe@example.com");

        // Fill Phone
        fillTextField(By.id("phone"), "+1234567890");
        System.out.println("✓ Phone: +1234567890");

        // Fill Age (optional)
        fillTextField(By.id("age"), "25");
        System.out.println("✓ Age: 25");

        // Select Gender
        selectDropdown(By.id("gender"), "Male");
        System.out.println("✓ Gender: Male");

        // Fill Address (optional)
        fillTextField(By.id("address"), "123 Main Street, Apt 4B");
        System.out.println("✓ Address: 123 Main Street, Apt 4B");

        // Select Country
        selectDropdown(By.id("country"), "United States");
        System.out.println("✓ Country: United States");

        // Wait for state dropdown to populate and select
        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "California");
        System.out.println("✓ State: California");

        // Wait for city dropdown to populate and select
        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "San Francisco");
        System.out.println("✓ City: San Francisco");

        // Fill Password
        String password = "SecurePass123!";
        fillTextField(By.id("password"), password);
        System.out.println("✓ Password: " + password);

        // Fill Confirm Password (matching)
        fillTextField(By.id("confirmPassword"), password);
        System.out.println("✓ Confirm Password: " + password + " (MATCHES)");

        // Check Terms & Conditions
        checkCheckbox(By.id("terms"));
        System.out.println("✓ Terms & Conditions: CHECKED");

        // Wait for validation to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStep 3: Verifying form state before submission...");

        // Verify submit button is enabled
        boolean isDisabled = isSubmitButtonDisabled();
        System.out.println("Submit button disabled: " + isDisabled);
        Assert.assertFalse(isDisabled, "Submit button should be enabled with all valid data");
        System.out.println("✓ Submit button is ENABLED (as expected)");

        System.out.println("\nStep 4: Submitting the form...");
        WebElement submitBtn = waitForClickable(By.id("submitBtn"));
        submitBtn.click();
        System.out.println("✓ Form submitted");

        // Wait for success message
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStep 5: Validating success message...");

        // Check for success message
        WebElement messageBox = driver.findElement(By.id("message-box"));
        String messageClass = messageBox.getAttribute("class");
        String messageText = messageBox.getText();

        System.out.println("Message box class: " + messageClass);
        System.out.println("Message text: " + messageText);

        Assert.assertTrue(messageClass.contains("success"), "Success message should be displayed");
        Assert.assertTrue(messageText.contains("Registration Successful"),
                "Success message should contain 'Registration Successful'");

        System.out.println("✓ SUCCESS MESSAGE DISPLAYED CORRECTLY");

        System.out.println("\nStep 6: Capturing success state screenshot...");
        takeScreenshot("success-state");

        // Wait for form reset (form resets after 2 seconds)
        System.out.println("\nStep 7: Waiting for form reset...");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStep 8: Verifying form has been reset...");

        // Verify fields are reset
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        String firstNameValue = firstNameField.getAttribute("value");

        System.out.println("First Name field value after reset: '" + firstNameValue + "'");
        Assert.assertEquals(firstNameValue, "", "Form should be reset after successful submission");
        System.out.println("✓ FORM HAS BEEN RESET");

        // Verify submit button is disabled again
        boolean isDisabledAfterReset = isSubmitButtonDisabled();
        System.out.println("Submit button disabled after reset: " + isDisabledAfterReset);
        Assert.assertTrue(isDisabledAfterReset, "Submit button should be disabled after form reset");

        System.out.println("\n=== POSITIVE SCENARIO TEST COMPLETED SUCCESSFULLY ===");
        System.out.println("Key Findings:");
        System.out.println("1. All form fields accepted valid data");
        System.out.println("2. Submit button enabled when form is valid");
        System.out.println("3. Success message displayed correctly");
        System.out.println("4. Form reset automatically after submission");
        System.out.println("5. Submit button disabled after reset");
    }

    @Test(priority = 2, description = "Test registration with optional fields empty")
    public void testRegistrationWithOptionalFieldsEmpty() {
        printSectionHeader("POSITIVE TEST - REGISTRATION WITHOUT OPTIONAL FIELDS");

        System.out.println("\nNavigating to registration page...");
        navigateToRegistrationPage();

        System.out.println("\nFilling only REQUIRED fields...");

        fillTextField(By.id("firstName"), "Jane");
        fillTextField(By.id("lastName"), "Smith");
        fillTextField(By.id("email"), "jane.smith@example.com");
        fillTextField(By.id("phone"), "+9876543210");
        selectDropdown(By.id("gender"), "Female");
        selectDropdown(By.id("country"), "India");

        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "Maharashtra");

        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "Mumbai");

        fillTextField(By.id("password"), "StrongPassword456!");
        fillTextField(By.id("confirmPassword"), "StrongPassword456!");
        checkCheckbox(By.id("terms"));

        System.out.println("✓ All required fields filled (Age and Address left empty)");

        // Wait for validation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify submit button is enabled
        boolean isDisabled = isSubmitButtonDisabled();
        Assert.assertFalse(isDisabled, "Submit button should be enabled even without optional fields");
        System.out.println("✓ Submit button is enabled (optional fields not required)");

        // Submit form
        WebElement submitBtn = waitForClickable(By.id("submitBtn"));
        submitBtn.click();

        // Wait and verify success
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement messageBox = driver.findElement(By.id("message-box"));
        String messageClass = messageBox.getAttribute("class");

        Assert.assertTrue(messageClass.contains("success"), "Registration should succeed without optional fields");
        System.out.println("✓ Registration successful without optional fields");

        takeScreenshot("success-without-optional-fields");

        System.out.println("\n=== OPTIONAL FIELDS TEST COMPLETED ===");
    }
}

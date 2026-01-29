package com.registration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Automation Flow C - Logic Validation Testing
 * Tests dynamic form behavior and logic
 */
public class LogicValidationTest extends BaseTest {

    @Test(priority = 1, description = "Test Country to State dropdown cascade")
    public void testCountryStateDropdownLogic() {
        printSectionHeader("LOGIC VALIDATION - COUNTRY → STATE DROPDOWN");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Testing dynamic State dropdown based on Country...");

        // Initially state should be disabled
        WebElement stateDropdown = driver.findElement(By.id("state"));
        boolean initiallyDisabled = !stateDropdown.isEnabled();
        System.out.println("State dropdown initially disabled: " + initiallyDisabled);
        Assert.assertTrue(initiallyDisabled, "State dropdown should be disabled initially");

        // Select Country - United States
        System.out.println("\nSelecting Country: United States");
        selectDropdown(By.id("country"), "United States");

        // Wait for state dropdown to be enabled and populated
        waitForDropdownOptions(By.id("state"));

        // Check if state dropdown is now enabled
        boolean stateEnabled = stateDropdown.isEnabled();
        System.out.println("State dropdown enabled after country selection: " + stateEnabled);
        Assert.assertTrue(stateEnabled, "State dropdown should be enabled after country selection");

        // Get available states
        Select stateSelect = new Select(stateDropdown);
        int stateCount = stateSelect.getOptions().size() - 1; // Exclude placeholder
        System.out.println("Number of states available for USA: " + stateCount);
        Assert.assertTrue(stateCount > 0, "States should be populated for USA");

        // Print available states
        System.out.println("Available states:");
        stateSelect.getOptions().forEach(option -> {
            if (!option.getText().equals("Select State")) {
                System.out.println("  - " + option.getText());
            }
        });

        System.out.println("\n✓ Country → State cascade working correctly");

        // Change country to India
        System.out.println("\nChanging Country to: India");
        selectDropdown(By.id("country"), "India");

        waitForDropdownOptions(By.id("state"));

        // Check states are updated for India
        stateSelect = new Select(stateDropdown);
        int indiaStateCount = stateSelect.getOptions().size() - 1;
        System.out.println("Number of states available for India: " + indiaStateCount);
        Assert.assertTrue(indiaStateCount > 0, "States should be populated for India");

        // Print available states for India
        System.out.println("Available states for India:");
        stateSelect.getOptions().forEach(option -> {
            if (!option.getText().equals("Select State")) {
                System.out.println("  - " + option.getText());
            }
        });

        System.out.println("\n✓ State dropdown updates correctly when country changes");

        takeScreenshot("country-state-logic");

        System.out.println("\n=== COUNTRY → STATE LOGIC TEST COMPLETED ===");
    }

    @Test(priority = 2, description = "Test State to City dropdown cascade")
    public void testStateCityDropdownLogic() {
        printSectionHeader("LOGIC VALIDATION - STATE → CITY DROPDOWN");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Setting up Country and State...");

        // Select Country
        selectDropdown(By.id("country"), "United States");
        System.out.println("Selected Country: United States");

        // Wait and select State
        waitForDropdownOptions(By.id("state"));

        // Initially city should be disabled
        WebElement cityDropdown = driver.findElement(By.id("city"));
        boolean initiallyDisabled = !cityDropdown.isEnabled();
        System.out.println("City dropdown initially disabled: " + initiallyDisabled);
        Assert.assertTrue(initiallyDisabled, "City dropdown should be disabled initially");

        System.out.println("\nSelecting State: California");
        selectDropdown(By.id("state"), "California");

        // Wait for city dropdown to be enabled and populated
        waitForDropdownOptions(By.id("city"));

        // Check if city dropdown is now enabled
        boolean cityEnabled = cityDropdown.isEnabled();
        System.out.println("City dropdown enabled after state selection: " + cityEnabled);
        Assert.assertTrue(cityEnabled, "City dropdown should be enabled after state selection");

        // Get available cities
        Select citySelect = new Select(cityDropdown);
        int cityCount = citySelect.getOptions().size() - 1; // Exclude placeholder
        System.out.println("Number of cities available for California: " + cityCount);
        Assert.assertTrue(cityCount > 0, "Cities should be populated for California");

        // Print available cities
        System.out.println("Available cities for California:");
        citySelect.getOptions().forEach(option -> {
            if (!option.getText().equals("Select City")) {
                System.out.println("  - " + option.getText());
            }
        });

        System.out.println("\n✓ State → City cascade working correctly");

        // Change state to Texas
        System.out.println("\nChanging State to: Texas");
        selectDropdown(By.id("state"), "Texas");

        waitForDropdownOptions(By.id("city"));

        // Check cities are updated for Texas
        citySelect = new Select(cityDropdown);
        int texasCityCount = citySelect.getOptions().size() - 1;
        System.out.println("Number of cities available for Texas: " + texasCityCount);
        Assert.assertTrue(texasCityCount > 0, "Cities should be populated for Texas");

        // Print available cities for Texas
        System.out.println("Available cities for Texas:");
        citySelect.getOptions().forEach(option -> {
            if (!option.getText().equals("Select City")) {
                System.out.println("  - " + option.getText());
            }
        });

        System.out.println("\n✓ City dropdown updates correctly when state changes");

        takeScreenshot("state-city-logic");

        System.out.println("\n=== STATE → CITY LOGIC TEST COMPLETED ===");
    }

    @Test(priority = 3, description = "Test password strength meter")
    public void testPasswordStrengthMeter() {
        printSectionHeader("LOGIC VALIDATION - PASSWORD STRENGTH METER");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Testing password strength indicator...");

        // Test weak password
        System.out.println("\nTesting WEAK password: 'pass123'");
        fillTextField(By.id("password"), "pass123");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String strengthWeak = getPasswordStrength();
        System.out.println("Password strength: " + strengthWeak);

        // Test medium password
        System.out.println("\nTesting MEDIUM password: 'Password123'");
        fillTextField(By.id("password"), "Password123");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String strengthMedium = getPasswordStrength();
        System.out.println("Password strength: " + strengthMedium);

        // Test strong password
        System.out.println("\nTesting STRONG password: 'P@ssw0rd!2023'");
        fillTextField(By.id("password"), "P@ssw0rd!2023");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String strengthStrong = getPasswordStrength();
        System.out.println("Password strength: " + strengthStrong);
        Assert.assertEquals(strengthStrong, "Strong", "Strong password should show 'Strong' indicator");

        System.out.println("\n✓ Password strength meter working correctly");

        takeScreenshot("password-strength");

        System.out.println("\n=== PASSWORD STRENGTH TEST COMPLETED ===");
    }

    @Test(priority = 4, description = "Test confirm password mismatch")
    public void testConfirmPasswordMismatch() {
        printSectionHeader("LOGIC VALIDATION - CONFIRM PASSWORD MISMATCH");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Testing password mismatch validation...");

        // Fill password
        fillTextField(By.id("password"), "SecurePass123!");
        System.out.println("Password: SecurePass123!");

        // Fill different confirm password
        fillTextField(By.id("confirmPassword"), "DifferentPass456!");
        System.out.println("Confirm Password: DifferentPass456! (MISMATCH)");

        // Trigger blur event
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("\t");

        // Wait for validation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check for error
        boolean hasError = hasErrorClass(confirmPasswordField);
        boolean errorMsgDisplayed = isErrorMessageDisplayed("confirmPassword");

        System.out.println("\nConfirm Password field has error: " + hasError);
        System.out.println("Error message displayed: " + errorMsgDisplayed);

        if (errorMsgDisplayed) {
            WebElement errorMsg = driver.findElement(By.id("confirmPassword-error"));
            System.out.println("Error message: " + errorMsg.getText());
        }

        Assert.assertTrue(hasError || errorMsgDisplayed, "Password mismatch should show error");
        System.out.println("\n✓ Password mismatch validation working correctly");

        takeScreenshot("password-mismatch");

        System.out.println("\n=== CONFIRM PASSWORD MISMATCH TEST COMPLETED ===");
    }

    @Test(priority = 5, description = "Test submit button enable/disable logic")
    public void testSubmitButtonLogic() {
        printSectionHeader("LOGIC VALIDATION - SUBMIT BUTTON ENABLE/DISABLE");

        System.out.println("\nStep 1: Launching registration page...");
        navigateToRegistrationPage();

        System.out.println("\nStep 2: Verifying submit button is initially disabled...");
        boolean initiallyDisabled = isSubmitButtonDisabled();
        System.out.println("Submit button initially disabled: " + initiallyDisabled);
        Assert.assertTrue(initiallyDisabled, "Submit button should be disabled initially");

        System.out.println("\nStep 3: Filling form fields progressively...");

        // Fill partial data
        fillTextField(By.id("firstName"), "Test");
        System.out.println("Filled First Name");

        boolean stillDisabled = isSubmitButtonDisabled();
        System.out.println("Submit button disabled after first field: " + stillDisabled);
        Assert.assertTrue(stillDisabled, "Submit button should remain disabled with incomplete form");

        // Fill all required fields
        System.out.println("\nFilling remaining required fields...");
        fillTextField(By.id("lastName"), "User");
        fillTextField(By.id("email"), "test@example.com");
        fillTextField(By.id("phone"), "+1234567890");
        selectDropdown(By.id("gender"), "Other");
        selectDropdown(By.id("country"), "Canada");

        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "Ontario");

        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "Toronto");

        fillTextField(By.id("password"), "ValidPass123!");
        fillTextField(By.id("confirmPassword"), "ValidPass123!");
        checkCheckbox(By.id("terms"));

        // Wait for validation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStep 4: Verifying submit button is now enabled...");
        boolean nowEnabled = !isSubmitButtonDisabled();
        System.out.println("Submit button enabled after completing form: " + nowEnabled);
        Assert.assertTrue(nowEnabled, "Submit button should be enabled with valid complete form");

        System.out.println("\n✓ Submit button logic working correctly");

        takeScreenshot("submit-button-enabled");

        System.out.println("\n=== SUBMIT BUTTON LOGIC TEST COMPLETED ===");
    }

    @Test(priority = 6, description = "Test complete flow with all cascading logic")
    public void testCompleteCascadingFlow() {
        printSectionHeader("COMPREHENSIVE LOGIC VALIDATION - COMPLETE FLOW");

        System.out.println("\nTesting complete cascading logic flow...");
        navigateToRegistrationPage();

        System.out.println("\n1. Testing Country → State → City cascade for multiple countries...");

        // Test for USA
        selectDropdown(By.id("country"), "United States");
        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "New York");
        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "New York City");
        System.out.println("✓ USA: New York → New York City");

        // Test for Australia
        selectDropdown(By.id("country"), "Australia");
        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "Victoria");
        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "Melbourne");
        System.out.println("✓ Australia: Victoria → Melbourne");

        // Test for UK
        selectDropdown(By.id("country"), "United Kingdom");
        waitForDropdownOptions(By.id("state"));
        selectDropdown(By.id("state"), "England");
        waitForDropdownOptions(By.id("city"));
        selectDropdown(By.id("city"), "London");
        System.out.println("✓ UK: England → London");

        System.out.println("\n✓ All cascading dropdowns working correctly across multiple countries");

        takeScreenshot("complete-cascade-flow");

        System.out.println("\n=== COMPREHENSIVE LOGIC TEST COMPLETED ===");
    }
}

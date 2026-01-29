# Intelligent Registration System

A responsive, user-friendly web application with robust client-side validations and comprehensive Selenium automation tests.

## 📋 Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Running Automation Tests](#running-automation-tests)
- [Test Scenarios](#test-scenarios)
- [Screenshots](#screenshots)

## ✨ Features

### Form Features
- ✅ **Comprehensive Registration Form** with all essential fields
- ✅ **Client-Side Validations**:
  - Email format validation
  - Disposable email domain blocking
  - Phone number with country code validation
  - Password strength indicator (Weak/Medium/Strong)
  - Confirm password matching
  - Mandatory field checks
- ✅ **Dynamic Dropdowns**: Country → State → City cascade
- ✅ **Real-time Feedback**: Inline error messages and field highlighting
- ✅ **Responsive Design**: Works on Desktop, Tablet, and Mobile
- ✅ **Smart Form Control**: Submit button auto-enables/disables based on validation

### Validation Rules
| Field | Validation |
|-------|-----------|
| First Name | Required, min 2 characters |
| Last Name | Required, min 2 characters |
| Email | Required, valid format, no disposable domains |
| Phone | Required, must start with country code (+) |
| Age | Optional, 1-120 if provided |
| Gender | Required (Male/Female/Other) |
| Address | Optional |
| Country | Required |
| State | Required, dynamically populated |
| City | Required, dynamically populated |
| Password | Required, min 8 characters |
| Confirm Password | Required, must match password |
| Terms & Conditions | Required |

## 🛠 Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript (ES6+)

### Automation Testing
- Selenium WebDriver 4.18.1
- Java 11
- TestNG 7.9.0
- Maven

## 📁 Project Structure

```
Form automation/
├── index.html                          # Main HTML file
├── style.css                           # Stylesheet
├── script.js                           # Form logic and validations
├── README.md                           # Project documentation
└── selenium-tests/                     # Automation test suite
    ├── pom.xml                         # Maven configuration
    ├── testng.xml                      # TestNG suite configuration
    └── src/test/java/com/registration/tests/
        ├── BaseTest.java               # Base test class with utilities
        ├── NegativeScenarioTest.java   # Negative test scenarios
        ├── PositiveScenarioTest.java   # Positive test scenarios
        └── LogicValidationTest.java    # Logic validation tests
```

## 🚀 Setup Instructions

### Prerequisites
1. **Web Browser**: Chrome, Firefox, Safari, or Edge
2. **For Testing**:
   - Java JDK 11 or higher
   - Maven 3.6+
   - Chrome Browser
   - ChromeDriver (automatically managed by Selenium)

### Installation Steps

1. **Clone or download the project**
   ```bash
   cd "C:\Users\priya\OneDrive\Desktop\Form automation"
   ```

2. **No additional installation needed for the web application** - Just open `index.html` in a browser!

3. **For Automation Tests Setup**:
   ```bash
   cd selenium-tests
   mvn clean install
   ```

## 🌐 Running the Application

### Method 1: Direct File Opening
1. Navigate to the project folder
2. Double-click `index.html`
3. The form will open in your default browser

### Method 2: Using Live Server (VS Code)
1. Install "Live Server" extension in VS Code
2. Right-click on `index.html`
3. Select "Open with Live Server"

### Method 3: Using Python HTTP Server
```bash
# Navigate to project folder
cd "C:\Users\priya\OneDrive\Desktop\Form automation"

# Start server
python -m http.server 8000

# Open browser and go to: http://localhost:8000
```

## 🧪 Running Automation Tests

### Prerequisites Check
```bash
# Verify Java installation
java -version

# Verify Maven installation
mvn -version
```

### Running Tests

1. **Navigate to test directory**
   ```bash
   cd "C:\Users\priya\OneDrive\Desktop\Form automation\selenium-tests"
   ```

2. **Update the file path in BaseTest.java**
   - Open `src/test/java/com/registration/tests/BaseTest.java`
   - Update the `baseUrl` variable with your actual path:
   ```java
   baseUrl = "file:///C:/Users/priya/OneDrive/Desktop/Form%20automation/index.html";
   ```

3. **Run all tests**
   ```bash
   mvn clean test
   ```

4. **Run specific test class**
   ```bash
   # Run only negative scenario tests
   mvn test -Dtest=NegativeScenarioTest

   # Run only positive scenario tests
   mvn test -Dtest=PositiveScenarioTest

   # Run only logic validation tests
   mvn test -Dtest=LogicValidationTest
   ```

5. **View test results**
   - Console output: Real-time test execution logs
   - Screenshots: `selenium-tests/test-screenshots/`
   - TestNG Reports: `selenium-tests/target/surefire-reports/`

## 📊 Test Scenarios

### Automation Flow A - Negative Scenario
**Objective**: Validate form prevents submission with missing required field

**Test Cases**:
1. Fill all fields EXCEPT Last Name
2. Attempt to submit form
3. Verify:
   - ❌ Submit button remains disabled
   - ❌ Last Name field highlighted with error
   - ❌ Error message displayed
4. Capture screenshot: `error-state.png`

**Additional Negative Tests**:
- Disposable email domain rejection
- Invalid phone number format
- Password mismatch detection

### Automation Flow B - Positive Scenario
**Objective**: Validate successful registration with valid data

**Test Cases**:
1. Fill all required fields with valid data
2. Ensure Password & Confirm Password match
3. Check Terms & Conditions
4. Submit form
5. Verify:
   - ✅ Success message displayed: "Registration Successful! Your profile has been submitted successfully."
   - ✅ Form fields reset
   - ✅ Submit button disabled after reset
6. Capture screenshot: `success-state.png`

**Additional Positive Tests**:
- Registration without optional fields (Age, Address)

### Automation Flow C - Logic Validation
**Objective**: Validate dynamic form behavior

**Test Cases**:
1. **Country → State Cascade**
   - Change Country
   - Verify State dropdown updates
   - Verify correct states populated

2. **State → City Cascade**
   - Change State
   - Verify City dropdown updates
   - Verify correct cities populated

3. **Password Strength Meter**
   - Test weak password: Shows "Weak"
   - Test medium password: Shows "Medium"
   - Test strong password: Shows "Strong"

4. **Confirm Password Validation**
   - Enter mismatched passwords
   - Verify error displayed

5. **Submit Button Logic**
   - Initially disabled
   - Enables when form is valid
   - Disables when form becomes invalid

## 📸 Screenshots

Screenshots are automatically captured during test execution and saved in:
```
selenium-tests/test-screenshots/
├── error-state_YYYYMMDD_HHMMSS.png
├── success-state_YYYYMMDD_HHMMSS.png
├── disposable-email-error_YYYYMMDD_HHMMSS.png
├── invalid-phone-error_YYYYMMDD_HHMMSS.png
├── password-mismatch_YYYYMMDD_HHMMSS.png
├── country-state-logic_YYYYMMDD_HHMMSS.png
├── state-city-logic_YYYYMMDD_HHMMSS.png
├── password-strength_YYYYMMDD_HHMMSS.png
└── submit-button-enabled_YYYYMMDD_HHMMSS.png
```

## 🎯 Validation Highlights

### ✅ Implemented Validations
- [x] Email format validation
- [x] Disposable email domain blocking
- [x] Phone number with country code
- [x] Password strength indicator
- [x] Password match verification
- [x] Dynamic Country → State → City
- [x] Smart submit button control
- [x] Real-time inline error messages
- [x] Form reset after successful submission

### 🎨 UI/UX Features
- [x] Responsive design (Mobile, Tablet, Desktop)
- [x] Error state highlighting (red borders)
- [x] Success state highlighting (green borders)
- [x] Loading states
- [x] Smooth animations and transitions
- [x] Accessible form labels
- [x] Clear error messaging

## 📝 Test Execution Logs

Sample console output from test execution:

```
============================================================
AUTOMATION FLOW A - NEGATIVE SCENARIO TEST
============================================================

Step 1: Launching registration page...
Page URL: file:///C:/Users/priya/OneDrive/Desktop/Form%20automation/index.html
Page Title: Intelligent Registration System

Step 2: Printing page details...
Current URL: file:///C:/Users/priya/OneDrive/Desktop/Form%20automation/index.html
Page Title: Intelligent Registration System

Step 3: Filling required fields (EXCEPT Last Name)...
✓ Filled First Name: John
✗ Last Name: LEFT EMPTY (intentionally)
✓ Filled Email: john.doe@example.com
...

Step 5: Validating error state...
Last Name field highlighted with error: true
Error message displayed: true
Error message text: Last name must be at least 2 characters long
✓ Validation passed: Submit button is disabled

=== NEGATIVE SCENARIO TEST COMPLETED SUCCESSFULLY ===
```

## 🐛 Troubleshooting

### Common Issues

1. **Tests can't find the HTML file**
   - Update the `baseUrl` in `BaseTest.java` with correct file path
   - Ensure path uses forward slashes and %20 for spaces

2. **ChromeDriver issues**
   - Update Selenium version in `pom.xml`
   - Selenium 4.x manages ChromeDriver automatically

3. **Tests failing due to timing issues**
   - Implicit waits and explicit waits are already implemented
   - Adjust `Duration.ofSeconds()` in BaseTest if needed

4. **Form not loading properly**
   - Check if all files (HTML, CSS, JS) are in the same directory
   - Open browser console to check for errors

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Modern web form development
- ✅ Client-side validation best practices
- ✅ Responsive web design
- ✅ JavaScript DOM manipulation
- ✅ Selenium WebDriver automation
- ✅ TestNG test framework
- ✅ Page Object Model pattern
- ✅ Test reporting and documentation

## 📄 License

This project is created for educational purposes.

## 👤 Author

**Priya**
- Project: Intelligent Registration System
- Date: January 2026

## 🙏 Acknowledgments

- Selenium WebDriver Documentation
- TestNG Framework
- Mozilla Developer Network (MDN) Web Docs
- W3C Web Standards

---

**Note**: This project fulfills all requirements specified in the Product Requirements Document (PRD) including:
- ✅ Complete registration form with all specified fields
- ✅ Robust client-side validations
- ✅ Dynamic dropdown logic (Country → State → City)
- ✅ Password strength indicator
- ✅ Responsive design
- ✅ Selenium automation tests (Flows A, B, C)
- ✅ Screenshots and test artifacts
- ✅ Comprehensive documentation

For questions or issues, please refer to the troubleshooting section or check the console logs during test execution.

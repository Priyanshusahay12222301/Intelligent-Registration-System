# Quick Start Guide - Intelligent Registration System

## 🚀 For Testing the Web Application

### Option 1: Direct Browser Opening
1. Open File Explorer
2. Navigate to: `C:\Users\priya\OneDrive\Desktop\Form automation`
3. Double-click `index.html`
4. The registration form will open in your browser!

### Option 2: Using VS Code Live Server
1. Open the folder in VS Code
2. Install "Live Server" extension (if not already installed)
3. Right-click on `index.html`
4. Click "Open with Live Server"

---

## 🧪 For Running Selenium Automation Tests

### Step 1: Verify Prerequisites
Open PowerShell and run:
```powershell
# Check Java
java -version
# Should show Java 11 or higher

# Check Maven
mvn -version
# Should show Maven 3.6 or higher
```

**If not installed:**
- **Java**: Download from https://adoptium.net/
- **Maven**: Download from https://maven.apache.org/download.cgi

### Step 2: Navigate to Test Directory
```powershell
cd "C:\Users\priya\OneDrive\Desktop\Form automation\selenium-tests"
```

### Step 3: Install Dependencies
```powershell
mvn clean install -DskipTests
```

### Step 4: Run All Tests
```powershell
mvn clean test
```

### Step 5: View Results
After tests complete:
- **Console**: See test execution logs in terminal
- **Screenshots**: Check `test-screenshots` folder
- **Reports**: Open `target\surefire-reports\index.html` in browser

---

## 📊 Expected Test Results

### ✅ All Tests Should Pass
```
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
```

### 📸 Screenshots Generated
- `error-state_*.png` - Form with validation errors
- `success-state_*.png` - Successful registration
- `password-strength_*.png` - Password strength indicator
- `country-state-logic_*.png` - Dynamic dropdown logic
- And more...

---

## 🎯 Manual Testing Checklist

### Test the Registration Form Manually:

1. **Open the form** (index.html)

2. **Test Negative Scenarios:**
   - [ ] Try submitting with empty Last Name
   - [ ] Enter email: test@tempmail.com (should reject)
   - [ ] Enter phone without +: 1234567890 (should show error)
   - [ ] Enter mismatched passwords (should show error)

3. **Test Positive Scenario:**
   - [ ] Fill all required fields correctly
   - [ ] Password & Confirm Password should match
   - [ ] Check Terms & Conditions
   - [ ] Submit button should be enabled
   - [ ] Click Submit
   - [ ] Success message should appear
   - [ ] Form should reset after 2 seconds

4. **Test Dynamic Logic:**
   - [ ] Select "United States" → States should populate
   - [ ] Select "California" → Cities should populate
   - [ ] Change to "India" → States should update
   - [ ] Password strength should show Weak/Medium/Strong

---

## 🐛 Common Issues & Solutions

### Issue: Maven not recognized
**Solution**: Install Maven and add to PATH
```powershell
# After installing Maven, add to PATH:
# Windows: System Properties > Environment Variables > Path
```

### Issue: Java not found
**Solution**: Install Java JDK 11+
```powershell
# Download from: https://adoptium.net/
# After install, verify:
java -version
```

### Issue: Tests can't find HTML file
**Solution**: Update path in BaseTest.java
- Open: `selenium-tests\src\test\java\com\registration\tests\BaseTest.java`
- Update line with `baseUrl` to your actual path

### Issue: Chrome browser not found
**Solution**: Tests use Chrome. If using different browser:
- Update `BaseTest.java` to use `FirefoxDriver()` or `EdgeDriver()`

---

## 📞 Need Help?

1. Check the main README.md for detailed documentation
2. Review console output for error messages
3. Ensure all files are in correct locations
4. Verify Java and Maven are properly installed

---

## ✨ Quick Test Commands

```powershell
# Navigate to project
cd "C:\Users\priya\OneDrive\Desktop\Form automation\selenium-tests"

# Run all tests
mvn test

# Run specific test
mvn test -Dtest=NegativeScenarioTest
mvn test -Dtest=PositiveScenarioTest
mvn test -Dtest=LogicValidationTest

# Clean and run
mvn clean test

# Skip tests (just compile)
mvn clean install -DskipTests
```

---

**Happy Testing! 🎉**

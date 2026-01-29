# Test Data Configuration

This file contains sample test data used in automation tests.

## Valid Test Data

### User Information
- **First Name**: John, Jane, Test
- **Last Name**: Doe, Smith, User
- **Email**: john.doe@example.com, jane.smith@example.com, test@example.com
- **Phone**: +1234567890, +9876543210, +447700900000
- **Age**: 25, 30, 35 (optional)
- **Gender**: Male, Female, Other

### Location Data

#### United States
- **States**: California, Texas, New York, Florida
- **California Cities**: Los Angeles, San Francisco, San Diego, Sacramento
- **Texas Cities**: Houston, Dallas, Austin, San Antonio
- **New York Cities**: New York City, Buffalo, Rochester, Albany
- **Florida Cities**: Miami, Orlando, Tampa, Jacksonville

#### India
- **States**: Maharashtra, Karnataka, Tamil Nadu, Delhi
- **Maharashtra Cities**: Mumbai, Pune, Nagpur, Nashik
- **Karnataka Cities**: Bangalore, Mysore, Mangalore, Hubli
- **Tamil Nadu Cities**: Chennai, Coimbatore, Madurai, Tiruchirappalli
- **Delhi Cities**: New Delhi, Central Delhi, South Delhi, North Delhi

#### United Kingdom
- **States**: England, Scotland, Wales, Northern Ireland
- **England Cities**: London, Manchester, Birmingham, Liverpool
- **Scotland Cities**: Edinburgh, Glasgow, Aberdeen, Dundee
- **Wales Cities**: Cardiff, Swansea, Newport, Wrexham
- **Northern Ireland Cities**: Belfast, Londonderry, Lisburn, Newry

#### Canada
- **States**: Ontario, Quebec, British Columbia, Alberta
- **Ontario Cities**: Toronto, Ottawa, Mississauga, Hamilton
- **Quebec Cities**: Montreal, Quebec City, Laval, Gatineau
- **British Columbia Cities**: Vancouver, Victoria, Surrey, Burnaby
- **Alberta Cities**: Calgary, Edmonton, Red Deer, Lethbridge

#### Australia
- **States**: New South Wales, Victoria, Queensland, Western Australia
- **New South Wales Cities**: Sydney, Newcastle, Wollongong, Central Coast
- **Victoria Cities**: Melbourne, Geelong, Ballarat, Bendigo
- **Queensland Cities**: Brisbane, Gold Coast, Townsville, Cairns
- **Western Australia Cities**: Perth, Fremantle, Mandurah, Bunbury

### Passwords
- **Weak**: pass123, test123, hello123
- **Medium**: Password123, Testing456, Welcome789
- **Strong**: P@ssw0rd!2023, SecurePass123!, StrongPassword456!

### Address (Optional)
- 123 Main Street, Apt 4B
- 456 Oak Avenue, Suite 200
- 789 Pine Road, Unit 5

## Invalid Test Data (For Negative Testing)

### Disposable Email Domains
- test@tempmail.com
- user@throwaway.email
- temp@10minutemail.com
- fake@guerrillamail.com
- test@mailinator.com

### Invalid Phone Numbers
- 1234567890 (missing country code)
- +123 (too short)
- abc123 (contains letters)
- +1-234-567-890 (contains dashes)

### Invalid Passwords
- short (too short - less than 8 characters)
- password (no numbers or special chars)
- 12345678 (only numbers)

### Mismatched Passwords
- Password: SecurePass123!
- Confirm: DifferentPass456!

## Test Scenarios

### Scenario 1: Complete Valid Registration
```
First Name: John
Last Name: Doe
Email: john.doe@example.com
Phone: +1234567890
Age: 25
Gender: Male
Address: 123 Main Street, Apt 4B
Country: United States
State: California
City: San Francisco
Password: SecurePass123!
Confirm Password: SecurePass123!
Terms: Checked
```

### Scenario 2: Minimal Valid Registration (Optional Fields Empty)
```
First Name: Jane
Last Name: Smith
Email: jane.smith@example.com
Phone: +9876543210
Age: [empty]
Gender: Female
Address: [empty]
Country: India
State: Maharashtra
City: Mumbai
Password: StrongPassword456!
Confirm Password: StrongPassword456!
Terms: Checked
```

### Scenario 3: Invalid Registration (Missing Last Name)
```
First Name: Test
Last Name: [empty] ❌
Email: test@example.com
Phone: +1234567890
Gender: Other
Country: Canada
State: Ontario
City: Toronto
Password: ValidPass123!
Confirm Password: ValidPass123!
Terms: Checked
```

### Scenario 4: Invalid Registration (Disposable Email)
```
First Name: Test
Last Name: User
Email: test@tempmail.com ❌
Phone: +1234567890
Gender: Male
Country: United States
State: California
City: Los Angeles
Password: SecurePass123!
Confirm Password: SecurePass123!
Terms: Checked
```

### Scenario 5: Invalid Registration (Password Mismatch)
```
First Name: Test
Last Name: User
Email: test@example.com
Phone: +1234567890
Gender: Male
Country: United States
State: Texas
City: Houston
Password: SecurePass123!
Confirm Password: DifferentPass456! ❌
Terms: Checked
```

## Automation Test Coverage

✅ **Implemented Tests**:
1. Missing required field (Last Name)
2. Disposable email domain rejection
3. Invalid phone number format
4. Password mismatch detection
5. Complete valid registration
6. Registration without optional fields
7. Country → State cascade
8. State → City cascade
9. Password strength indicator
10. Submit button enable/disable logic
11. Complete cascading flow across multiple countries

## Notes

- All test data is fictional and for testing purposes only
- Email addresses use example.com domain (reserved for testing)
- Phone numbers are in international format (+country code)
- Passwords include mix of uppercase, lowercase, numbers, and special characters
- Dynamic dropdowns are tested across 5 countries with multiple states/cities each

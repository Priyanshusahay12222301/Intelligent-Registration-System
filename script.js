// Dynamic dropdown data
const locationData = {
    USA: {
        California: ['Los Angeles', 'San Francisco', 'San Diego', 'Sacramento'],
        Texas: ['Houston', 'Dallas', 'Austin', 'San Antonio'],
        'New York': ['New York City', 'Buffalo', 'Rochester', 'Albany'],
        Florida: ['Miami', 'Orlando', 'Tampa', 'Jacksonville']
    },
    India: {
        Maharashtra: ['Mumbai', 'Pune', 'Nagpur', 'Nashik'],
        Karnataka: ['Bangalore', 'Mysore', 'Mangalore', 'Hubli'],
        'Tamil Nadu': ['Chennai', 'Coimbatore', 'Madurai', 'Tiruchirappalli'],
        Delhi: ['New Delhi', 'Central Delhi', 'South Delhi', 'North Delhi']
    },
    UK: {
        England: ['London', 'Manchester', 'Birmingham', 'Liverpool'],
        Scotland: ['Edinburgh', 'Glasgow', 'Aberdeen', 'Dundee'],
        Wales: ['Cardiff', 'Swansea', 'Newport', 'Wrexham'],
        'Northern Ireland': ['Belfast', 'Londonderry', 'Lisburn', 'Newry']
    },
    Canada: {
        Ontario: ['Toronto', 'Ottawa', 'Mississauga', 'Hamilton'],
        Quebec: ['Montreal', 'Quebec City', 'Laval', 'Gatineau'],
        'British Columbia': ['Vancouver', 'Victoria', 'Surrey', 'Burnaby'],
        Alberta: ['Calgary', 'Edmonton', 'Red Deer', 'Lethbridge']
    },
    Australia: {
        'New South Wales': ['Sydney', 'Newcastle', 'Wollongong', 'Central Coast'],
        Victoria: ['Melbourne', 'Geelong', 'Ballarat', 'Bendigo'],
        Queensland: ['Brisbane', 'Gold Coast', 'Townsville', 'Cairns'],
        'Western Australia': ['Perth', 'Fremantle', 'Mandurah', 'Bunbury']
    }
};

// Disposable email domains
const disposableEmailDomains = [
    'tempmail.com', 'throwaway.email', '10minutemail.com', 'guerrillamail.com',
    'mailinator.com', 'maildrop.cc', 'temp-mail.org', 'getnada.com',
    'trashmail.com', 'fakeinbox.com', 'yopmail.com', 'mintemail.com'
];

// Form elements
const form = document.getElementById('registrationForm');
const submitBtn = document.getElementById('submitBtn');
const messageBox = document.getElementById('message-box');

// Get all form fields
const fields = {
    firstName: document.getElementById('firstName'),
    lastName: document.getElementById('lastName'),
    email: document.getElementById('email'),
    phone: document.getElementById('phone'),
    age: document.getElementById('age'),
    gender: document.getElementById('gender'),
    address: document.getElementById('address'),
    country: document.getElementById('country'),
    state: document.getElementById('state'),
    city: document.getElementById('city'),
    password: document.getElementById('password'),
    confirmPassword: document.getElementById('confirmPassword'),
    terms: document.getElementById('terms')
};

// Validation rules
const validators = {
    firstName: {
        validate: (value) => value.trim().length >= 2,
        message: 'First name must be at least 2 characters long'
    },
    lastName: {
        validate: (value) => value.trim().length >= 2,
        message: 'Last name must be at least 2 characters long'
    },
    email: {
        validate: (value) => {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(value)) {
                return false;
            }
            // Check for disposable email domains
            const domain = value.split('@')[1];
            if (disposableEmailDomains.includes(domain)) {
                validators.email.message = 'Disposable email addresses are not allowed';
                return false;
            }
            validators.email.message = 'Please enter a valid email address';
            return true;
        },
        message: 'Please enter a valid email address'
    },
    phone: {
        validate: (value) => {
            // Must start with + and have 10-15 digits
            const phoneRegex = /^\+\d{10,15}$/;
            return phoneRegex.test(value.replace(/\s/g, ''));
        },
        message: 'Phone number must start with country code (e.g., +1234567890)'
    },
    age: {
        validate: (value) => {
            if (value === '') return true; // Optional field
            return value >= 1 && value <= 120;
        },
        message: 'Age must be between 1 and 120'
    },
    gender: {
        validate: (value) => value !== '',
        message: 'Please select your gender'
    },
    country: {
        validate: (value) => value !== '',
        message: 'Please select your country'
    },
    state: {
        validate: (value) => value !== '',
        message: 'Please select your state'
    },
    city: {
        validate: (value) => value !== '',
        message: 'Please select your city'
    },
    password: {
        validate: (value) => value.length >= 8,
        message: 'Password must be at least 8 characters long'
    },
    confirmPassword: {
        validate: (value) => value === fields.password.value,
        message: 'Passwords do not match'
    },
    terms: {
        validate: (value) => value,
        message: 'You must agree to the terms and conditions'
    }
};

// Show error message
function showError(fieldName, message) {
    const errorElement = document.getElementById(`${fieldName}-error`);
    const fieldElement = fields[fieldName];
    
    errorElement.textContent = message;
    errorElement.classList.add('show');
    fieldElement.classList.add('error');
    fieldElement.classList.remove('success');
}

// Clear error message
function clearError(fieldName) {
    const errorElement = document.getElementById(`${fieldName}-error`);
    const fieldElement = fields[fieldName];
    
    errorElement.textContent = '';
    errorElement.classList.remove('show');
    fieldElement.classList.remove('error');
    
    // Add success class for valid required fields
    if (fieldElement.value.trim() !== '' || fieldName === 'terms') {
        fieldElement.classList.add('success');
    }
}

// Validate single field
function validateField(fieldName) {
    const field = fields[fieldName];
    const validator = validators[fieldName];
    
    if (!validator) return true;
    
    const value = field.type === 'checkbox' ? field.checked : field.value;
    
    if (validator.validate(value)) {
        clearError(fieldName);
        return true;
    } else {
        showError(fieldName, validator.message);
        return false;
    }
}

// Password strength checker
function checkPasswordStrength(password) {
    const strengthBar = document.getElementById('strengthBar');
    const strengthText = document.getElementById('strengthText');
    
    if (password.length === 0) {
        strengthBar.className = 'strength-bar';
        strengthText.textContent = '';
        strengthText.className = 'strength-text';
        return;
    }
    
    let strength = 0;
    
    // Length
    if (password.length >= 8) strength++;
    if (password.length >= 12) strength++;
    
    // Contains lowercase
    if (/[a-z]/.test(password)) strength++;
    
    // Contains uppercase
    if (/[A-Z]/.test(password)) strength++;
    
    // Contains numbers
    if (/\d/.test(password)) strength++;
    
    // Contains special characters
    if (/[^a-zA-Z0-9]/.test(password)) strength++;
    
    // Determine strength level
    if (strength <= 2) {
        strengthBar.className = 'strength-bar weak';
        strengthText.textContent = 'Weak';
        strengthText.className = 'strength-text weak';
    } else if (strength <= 4) {
        strengthBar.className = 'strength-bar medium';
        strengthText.textContent = 'Medium';
        strengthText.className = 'strength-text medium';
    } else {
        strengthBar.className = 'strength-bar strong';
        strengthText.textContent = 'Strong';
        strengthText.className = 'strength-text strong';
    }
}

// Dynamic dropdown - Update State based on Country
fields.country.addEventListener('change', function() {
    const country = this.value;
    const stateDropdown = fields.state;
    const cityDropdown = fields.city;
    
    // Reset state and city
    stateDropdown.innerHTML = '<option value="">Select State</option>';
    cityDropdown.innerHTML = '<option value="">Select City</option>';
    cityDropdown.disabled = true;
    stateDropdown.value = '';
    cityDropdown.value = '';
    
    if (country && locationData[country]) {
        stateDropdown.disabled = false;
        const states = Object.keys(locationData[country]);
        
        states.forEach(state => {
            const option = document.createElement('option');
            option.value = state;
            option.textContent = state;
            stateDropdown.appendChild(option);
        });
    } else {
        stateDropdown.disabled = true;
    }
    
    validateField('country');
    checkFormValidity();
});

// Dynamic dropdown - Update City based on State
fields.state.addEventListener('change', function() {
    const country = fields.country.value;
    const state = this.value;
    const cityDropdown = fields.city;
    
    // Reset city
    cityDropdown.innerHTML = '<option value="">Select City</option>';
    cityDropdown.value = '';
    
    if (country && state && locationData[country][state]) {
        cityDropdown.disabled = false;
        const cities = locationData[country][state];
        
        cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city;
            option.textContent = city;
            cityDropdown.appendChild(option);
        });
    } else {
        cityDropdown.disabled = true;
    }
    
    validateField('state');
    checkFormValidity();
});

// City change event
fields.city.addEventListener('change', function() {
    validateField('city');
    checkFormValidity();
});

// Password strength checker
fields.password.addEventListener('input', function() {
    checkPasswordStrength(this.value);
    validateField('password');
    
    // Re-validate confirm password if it has value
    if (fields.confirmPassword.value) {
        validateField('confirmPassword');
    }
    
    checkFormValidity();
});

// Confirm password validation
fields.confirmPassword.addEventListener('input', function() {
    validateField('confirmPassword');
    checkFormValidity();
});

// Add input event listeners for all fields
Object.keys(fields).forEach(fieldName => {
    const field = fields[fieldName];
    
    if (field.type === 'checkbox') {
        field.addEventListener('change', function() {
            validateField(fieldName);
            checkFormValidity();
        });
    } else if (fieldName !== 'password' && fieldName !== 'confirmPassword' && 
               fieldName !== 'country' && fieldName !== 'state' && fieldName !== 'city') {
        field.addEventListener('input', function() {
            validateField(fieldName);
            checkFormValidity();
        });
        
        field.addEventListener('blur', function() {
            validateField(fieldName);
            checkFormValidity();
        });
    }
});

// Gender change event
fields.gender.addEventListener('change', function() {
    validateField('gender');
    checkFormValidity();
});

// Check if entire form is valid
function checkFormValidity() {
    let isValid = true;
    
    // Check all required fields
    const requiredFields = ['firstName', 'lastName', 'email', 'phone', 'gender', 
                           'country', 'state', 'city', 'password', 'confirmPassword', 'terms'];
    
    for (const fieldName of requiredFields) {
        const field = fields[fieldName];
        const value = field.type === 'checkbox' ? field.checked : field.value;
        
        if (!value || (typeof value === 'string' && value.trim() === '')) {
            isValid = false;
            break;
        }
        
        // Also check if validator exists and passes
        if (validators[fieldName]) {
            if (!validators[fieldName].validate(value)) {
                isValid = false;
                break;
            }
        }
    }
    
    submitBtn.disabled = !isValid;
    return isValid;
}

// Show message
function showMessage(message, type) {
    messageBox.textContent = message;
    messageBox.className = `message-box ${type}`;
    messageBox.scrollIntoView({ behavior: 'smooth', block: 'start' });
    
    // Auto-hide after 5 seconds
    setTimeout(() => {
        messageBox.className = 'message-box';
    }, 5000);
}

// Form submission
form.addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Validate all fields
    let isValid = true;
    const requiredFields = ['firstName', 'lastName', 'email', 'phone', 'gender', 
                           'country', 'state', 'city', 'password', 'confirmPassword', 'terms'];
    
    requiredFields.forEach(fieldName => {
        if (!validateField(fieldName)) {
            isValid = false;
        }
    });
    
    // Validate optional field if filled
    if (fields.age.value) {
        validateField('age');
    }
    
    if (!isValid) {
        showMessage('Please fix all errors before submitting', 'error');
        return;
    }
    
    // If all validations pass
    showMessage('Registration Successful! Your profile has been submitted successfully.', 'success');
    
    // Reset form after 2 seconds
    setTimeout(() => {
        form.reset();
        
        // Reset all error states
        Object.keys(fields).forEach(fieldName => {
            fields[fieldName].classList.remove('error', 'success');
        });
        
        // Reset password strength
        document.getElementById('strengthBar').className = 'strength-bar';
        document.getElementById('strengthText').textContent = '';
        
        // Reset dropdowns
        fields.state.disabled = true;
        fields.city.disabled = true;
        fields.state.innerHTML = '<option value="">Select State</option>';
        fields.city.innerHTML = '<option value="">Select City</option>';
        
        submitBtn.disabled = true;
        
        // Hide message
        messageBox.className = 'message-box';
    }, 2000);
});

// Initial check
checkFormValidity();

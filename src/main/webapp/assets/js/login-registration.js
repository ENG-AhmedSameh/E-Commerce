function showLogin() {
    document.getElementById('lg1').style.display = 'block';
    document.getElementById('lg2').style.display = 'none';
    document.getElementById('loginTab').classList.add('active');
    document.getElementById('registerTab').classList.remove('active');
}

function showRegister() {
    document.getElementById('lg1').style.display = 'none';
    document.getElementById('lg2').style.display = 'block';
    document.getElementById('loginTab').classList.remove('active');
    document.getElementById('registerTab').classList.add('active');


}

function postData(event) {
    event.preventDefault();
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var label = document.getElementById("emailWarning");
            var response = xmlhttp.responseText;
            if (response === "valid") {
                label.textContent = ""; // clear the warning message
            } else {
                label.textContent = "Username already exists. Please choose another one.";
            }
        }
    };


    xmlhttp.open("POST", "checkEmailAvailabilityController", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(formData);
}

function postData(event) {
    event.preventDefault();
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var label = document.getElementById("usernameWarning");
            var response = xmlhttp.responseText;
            if (response === "valid") {
                label.textContent = ""; // clear the warning message
            } else {
                label.textContent = "Username already exists. Please choose another one.";
            }
        }
    };


    xmlhttp.open("POST", "CheckUserNameAvailabilityController ", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(formData);
}

// Add event listener for form submission
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registrationForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent form submission

        // Perform validation
        const email = document.getElementById('email').value;
        const phoneNumber = document.getElementById('phone_number').value;
        const password = document.getElementById('Password').value;

        // Email validation
        if (!validateEmail(email)) {
            alert('Please enter a valid email address.');
            return;
        }

        // Phone number validation
        if (!validatePhoneNumber(phoneNumber)) {
            alert('Please enter a valid phone number starting with 010, 011, 012, or 015 followed by 8 digits.');
            return;
        }

        // Password length validation
        if (password.length < 8) {
            alert('Password must be at least 8 characters long.');
            return;
        }

        // If all validations pass, submit the form
        form.submit();
    });

    // Email validation function
    function validateEmail(email) {
        const re = /\S+@\S+\.\S+/;
        return re.test(email);
    }

    // Phone number validation function
    function validatePhoneNumber(phoneNumber) {
        const re = /^(010|011|012|015)\d{8}$/;
        return re.test(phoneNumber);
    }
});


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


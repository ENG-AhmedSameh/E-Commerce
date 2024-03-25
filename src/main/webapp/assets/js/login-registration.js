// function showLogin() {
//     document.getElementById('lg1').style.display = 'block';
//     document.getElementById('lg2').style.display = 'none';
//     document.getElementById('loginTab').classList.add('active');
//     document.getElementById('registerTab').classList.remove('active');
// }

// function showRegister() {
//     document.getElementById('lg1').style.display = 'none';
//     document.getElementById('lg2').style.display = 'block';
//     document.getElementById('loginTab').classList.remove('active');
//     document.getElementById('registerTab').classList.add('active');

// }


document.addEventListener("DOMContentLoaded", function() {
    function switchTab(event) {
        console.log("Switching tab");
        var loginTab = document.getElementById('lg1');
        var registerTab = document.getElementById('lg2');
        var loginTabLink = document.getElementById('loginTab');
        var registerTabLink = document.getElementById('registerTab');

       
        if (event.target === loginTabLink) {
            showLogin();
        }

        // Handle register tab
        if (event.target === registerTabLink) {
            showRegister();
        }

        event.preventDefault(); 
    }

   
    showLogin();

    document.getElementById('loginTab').addEventListener('click', switchTab);
    document.getElementById('registerTab').addEventListener('click', switchTab);
});


function showRegister() {
    document.getElementById('lg1').style.display = 'none';
    document.getElementById('lg2').style.display = 'block';
    document.getElementById('loginTab').classList.remove('active');
    document.getElementById('registerTab').classList.add('active');
}

function showLogin() {
    document.getElementById('lg1').style.display = 'block';
    document.getElementById('lg2').style.display = 'none';
    document.getElementById('loginTab').classList.add('active');
    document.getElementById('registerTab').classList.remove('active');
}





////// email validation
function postEmailData(){
    event.preventDefault();
    var xmlhttp;
    var label = document.getElementById("emailError");

    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function(){

        if(xmlhttp.readyState===4 && xmlhttp.status===200){

            var response = xmlhttp.responseText;
            if(response=="valid"){
                label.textContent = xmlhttp.responseText;
                label.style.color = "green";
            }else{
                label.textContent = xmlhttp.responseText;
                label.style.color = "red";
            }

        }

    };

    var url="front?page=checkEmail";
    var myvalue = document.getElementById("user_email").value;

    console.log("label"+label.textContent);
    console.log("email"+myvalue);
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("email="+myvalue);
}


////// user name validation
function posUserNametData(){
    event.preventDefault();
    var xmlhttp;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function(){

        if(xmlhttp.readyState===4 && xmlhttp.status===200){
            var label = document.getElementById("usernameError");
            var response = xmlhttp.responseText;
            if(response==="valid"){
                label.textContent = xmlhttp.responseText;
                label.style.color = "green";
            }else{
                label.textContent = xmlhttp.responseText;
                label.style.color = "red";
            }
        }
    };

    myvalue = document.getElementById("user_name").value;
    if(myvalue.length < 5){
        return;
    }

    url="front?page=checkUserName";
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("username="+myvalue);

}


document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registrationForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();


        const email = document.getElementById('user_email').value;
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

function getUserSavedCartItems() {
    //event.preventDefault();

    console.log("Getting user cart items from data base");

    // var username = document.getElementById("username1").value;
    // var password = document.getElementById("password1").value;
    var data = new URLSearchParams();
    var cartItems = JSON.parse(sessionStorage.getItem("cartItems")) || [];
    var productIds = cartItems.map((item) => item.id);
    var quantities = cartItems.map((item) => item.quantity);
    // data.append("username", username);
    // data.append("password", password);
    data.append("productIds", JSON.stringify(productIds));
    data.append("quantities", JSON.stringify(quantities));

    fetch("front?page=getUserCartItem", {
        method: "POST",
        body: data,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
    })
        .then((response) => response.json())
        .then((data) => {
            console.log("Product added to cart:", data);

            if(data=="Invalid username or password."){
                document.getElementById("login-error-message").innerText = data;
                return;
            }
            var newCartItems =[];

            console.log("Cart items:", data);

            data.forEach((cartProduct) => {
            console.log("Product added to cart:", cartProduct);
                var cartItem = {
                    id: cartProduct.id,
                    mainImageUrl:cartProduct.mainImageUrl,
                    name: cartProduct.name,
                    price: cartProduct.price,
                    category: cartProduct.category,
                    quantity: cartProduct.quantity
                };
                newCartItems.push(cartItem);
            });
            sessionStorage.setItem("cartItems", JSON.stringify(newCartItems));
            document.getElementById("login-error-message").innerText = '';
        })
        .then(() => {
            window.location.href = "";
            // updateCount();
            // loadProductsToCart();
        })
        .catch((error) => {
            console.log(
                "An error occurred while getting the user cart items from data base:",
                error
            );
        });
}




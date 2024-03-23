<%@ page import="com.example.ecommerce.model.DTO.LoggedInUserDto" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" type="image/png" href="assets/images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <link rel="stylesheet" type="text/css" href="assets/css/updateProfile.css">
    <style>
        /* CSS for the button */
        #registerButton {
            border-radius: 20px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    

        #registerButton:disabled {
            background-color: #cccccc;
            color: #999999;
            cursor: not-allowed;
        }
    </style>
    <!--===============================================================================================-->
</head>
<body class="animsition">

<jsp:include page="header.jsp" />

<!-- Cart -->
<jsp:include page="cart.jsp" />

<!-- Title page -->
<section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('assets/images/bg-01.jpg');">
    <h2 class="ltext-105 cl0 txt-center">
        Update Profile
    </h2>
</section>

<!-- Content page -->
<section class="bg0 p-t-14 p-b-116">
    <div class="login-register-area ptb-100">
        <div class="container">
            <div class="card">
                <div class="row">
                    <div class="col-lg-50 col-md-12 mx-auto "> <!-- Increased width to col-lg-9 -->
                        <div class="login-register-wrapper">
                            <form action="front?page=updateProfile" method="post">
                                <div class="form-group ">
                                    <label class="m-2" for="user_name">Username:</label>
                                    <input type="text" id="user_name" name="user_name" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getUserName() %>" readonly class="form-control">
                                </div>
                                <div class="form-group">
                                    <label class="m-2" for="email">Email:</label>
                                    <input type="email" id="email" name="email" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getEmail() %>" readonly class="form-control">
                                </div>
                                <div class="form-group">
                                   
                                </div>

                                
                                <div class="form-row">
                                    <div class="form-group col">
                                        <label for="first_name">First Name:</label>
                                        <input type="text" id="first_name" name="first_name" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getFirstName() %>" class="form-control">
                                    </div>
                                    <div class="form-group col">
                                        <label for="last_name">Last Name:</label>
                                        <input type="text" id="last_name" name="last_name" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getLastName() %>" class="form-control">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col">
                                        <label for="job">Job Title:</label>
                                        <input type="text" id="job" name="job" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getJob() %>" class="form-control">
                                    </div>
                                    <div class="form-group col">
                                        <label for="phone_number">Phone Number:</label>
                                        <input type="text" id="phone_number" name="phone_number" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getPhoneNumber() %>" class="form-control">
                                    </div>
                                </div>



                                <!-- <div class="form-row">
                                    <div class="form-group col">
                                        
                                    </div>
                                </div> -->
                                <div class="form-row">
                                    <div class="form-group col">
                                        <label for="city">City:</label>
                                        <input type="text" id="city" name="city" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getCity() %>" class="form-control">
                                    </div>
                                    <div class="form-group col">
                                        <label for="street">Street:</label>
                                        <input type="text" id="street" name="street" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getStreet() %>" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="credit_limit">Credit Limit:</label>
                                    <input type="text" id="credit_limit" name="credit_limit" value="<%= ((LoggedInUserDto) session.getAttribute("currentUser")).getCreditLimit() %>" class="form-control">
                                </div>
                                <div class="button-box text-center">
                                    <button id="registerButton" type="submit" disabled><span>Register</span></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- End of Content page -->

<jsp:include page="footer.jsp" />

<!-- Your existing JavaScript imports and scripts -->
<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/bootstrap/js/popper.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="assets/js/main.js"></script>

<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
    <span class="symbol-btn-back-to-top">
        <i class="zmdi zmdi-chevron-up"></i>
    </span>
</div>

<script>
       document.addEventListener('DOMContentLoaded', function() {
        // Get all input elements within the form
        var inputs = document.querySelectorAll('form input');

        // Iterate through each input and add an event listener for change
        inputs.forEach(function(input) {
            input.addEventListener('input', function() {
                // Enable the button when any input changes
                document.getElementById('registerButton').disabled = false;
            });
        });
    });
</script>

</body>
</html>

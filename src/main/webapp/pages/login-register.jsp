<%@ page session="false" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" type="image/png" href="assets/images/icons/favicon.png" />
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/linearicons-v1.0.0/icon-font.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/MagnificPopup/magnific-popup.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/perfect-scrollbar/perfect-scrollbar.css" />
        <!--============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/css/util.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/login-registration.css" />
        <!--===============================================================================================-->
        <title>User Registration</title>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <!-- Cart -->
        <jsp:include page="cart.jsp" />

        <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('assets/images/bg-01.jpg');">
            --%>
            <h2 class="ltext-105 cl0 txt-center">
                Login - Register
            </h2>
        </section>

        <!-- Content page -->
        <section class="bg0 p-t-14 p-b-116">
            <div class="login-register-area ptb-100">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7 col-md-12 mx-auto"> <!-- Center the column -->
                            <div class="login-register-wrapper">
                                <div class="login-register-tab-list nav">
                                    <a class="active text-center" data-toggle="tab" href="#lg1" onclick="showLogin()"
                                        id="loginTab">
                                        <h4>Login</h4>
                                    </a>

                                    <a class="text-center" data-toggle="tab" href="#lg2" onclick="showRegister()"
                                        id="registerTab">
                                        <h4>Register</h4>
                                    </a>
                                </div>
                                <div class="tab-content">
                                    <div id="lg1" class="tab-pane active">
                                        <div class="form-container">
                                            <form id="loginForm" action="front?page=login" method="post">
                                                <input id="username1" type="text" name="username" placeholder="Username">
                                                <input id="password1" type="password" name="password" placeholder="Password">
                                                <div id="login-error-message" style="color: red;">
                                                    <!-- This area is for displaying error messages -->
                                                    <!-- It will be empty and not displayed if there are no errors -->
                                                    <% if(request.getAttribute("login-error") != null) { %>
                                                    <%= request.getAttribute("login-error") %>
                                                    <% } %>
                                                </div>
                                                <div class="button-box">
<%--                                                    <div class="login-toggle-btn">--%>
<%--                                                        <input type="checkbox" class="remember"> <label>Remember--%>
<%--                                                            me</label>--%>
<%--                                                        <label class="login-toggle-btn center-content">--%>
<%--                                                            <a href="#">Forgot Password?</a>--%>
<%--                                                        </label>--%>
<%--                                                    </div>--%>
                                                    <button type="submit"><span>Login</span></button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div id="lg2" class="tab-pane">
                                        <div class="form-container">
                                            <form id="registrationForm" action="front?page=registration" method="post">
                                                <div id="register-error-message" style="color: red;">
                                                    <!-- This area is for displaying error messages -->
                                                    <!-- It will be empty and not displayed if there are no errors -->
                                                    <% if(request.getAttribute("register-error") != null) { %>
                                                    <%= request.getAttribute("register-error") %>
                                                    <% } %>
                                                </div>
                                                <div class="line d-flex " >
                                                    <input class="form-controll w-50 mr-2" type="text" id="first_name" name="firstname"
                                                        placeholder="First Name" required>
                                                    <input class="form-controll w-50 ml-2" type="text" id="last_name" name="lastname"
                                                        placeholder="Last Name" required>
                                                </div>

                                                <div class="line d-flex">
                                                    <input class="form-controll w-75 " type="text" class="line-custom" id="user_name"
                                                        name="username" onblur="posUserNametData();"
                                                        placeholder="Username" required>

                                                    <span class="m-2" id="usernameError" class="error"></span>
                                                </div>

                                                <div class="line d-flex">
                                                    <input class="form-controll w-75 " type="email" class="line-custom" id="user_email" name="email"
                                                        placeholder="Email" onblur="postEmailData();" required>

                                                    <span  class="m-2" id="emailError" class="error"></span>
                                                </div>

                                                <div class="line d-flex">
                                                    <input    class="form-controll w-50 mr-2" type="text" id="City" name="city" placeholder="City"
                                                        required>
                                                    <input  class="form-controll w-50 ml-2" type="text" id="Street" name="street" placeholder="Street"
                                                        required>
                                                </div>

                           

                                                <div class="block-input line d-flex">
                                                    <input  class="form-controll w-50 mr-2" type="tel" id="phone_number" name="phoneNumber"
                                                        placeholder="Phone Number" required>
                                                        <input  class="form-controll w-50 ml-2" type="text" id="job" name="job" placeholder="Job">
                                                </div>
                                                
                                                <input  class="d-flex w-60" type="text" id="creditLimit" name="creditLimit"
                                                    placeholder="Credit Limit" required maxlength="16">

                                                <div class="line d-flex">
                                                    <input class="form-controll w-50 mr-2 " type="password" id="Password" name="password"
                                                        placeholder="Password" required>
                                                    <input class="form-controll w-50 ml-2" type="password" id="confirmPassword" name="confirmPassword"
                                                        placeholder="Confirm Password" required>
                                                </div>
                                                <!-- Checkboxes for selecting things -->
                                                
                                                <div class="separator line ">

                                                    <!-- justify-content-center -->
                                                   
                                                    <div class="line d-flex  m-2">
                                                        <h3 class="mr-2">Interesting:&nbsp; &nbsp; &nbsp;</h3>
                                                        <div class="d-flex mr-4">
                                                            <input type="checkbox" id="thing1" name="selectedThing"
                                                                value="Phones">
                                                            <label for="thing1" class="ml-2 mt-2">Phones</label>
                                                        </div>
                                                        <div class="d-flex mr-4">
                                                            <input type="checkbox" id="thing2" name="selectedThing"
                                                                value="Laptops">
                                                            <label for="thing2" class="ml-2 mt-2">Laptops</label>
                                                        </div>
                                                        <div class="d-flex mr-4">
                                                            <input type="checkbox" id="thing3" name="selectedThing"
                                                                value="Tablets">
                                                            <label for="thing3" class="ml-2 mt-2">Tablets</label>
                                                        </div>
                                                    </div>
                                                
                                                </div>
                                                
                                                <!-- Radio buttons for gender -->
                                                <div class="separator center-content">
                                                   
                                                    <div class="line d-flex m-2 ">
                                                        <h3 class=>Gender: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</h3>
                                                        <div class="d-flex mr-5">
                                                            <input type="radio" id="female" name="gender" value="F"
                                                                required>
                                                            <label for="female" class="ml-2 mt-2 ">Female</label><br>
                                                        </div>
                                                        <div class="d-flex mr-5">
                                                            <input type="radio" id="male" name="gender" value="M"
                                                                required>
                                                            <label for="male" class="ml-2 mt-2 ">Male</label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Submit button -->
                                                <div class="button-box text-center">
                                                    <button type="submit"><span>Register</span></button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp" />
    </body>

    <!--===============================================================================================-->
    <script src="../assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="../assets/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="../assets/vendor/bootstrap/js/popper.js"></script>
    <script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="../assets/vendor/select2/select2.min.js"></script>


    <script>
        $(".js-select2").each(function () {
            $(this).select2({
                minimumResultsForSearch: 20,
                dropdownParent: $(this).next(".dropDownSelect2"),
            });
        });
    </script>
    <!--===============================================================================================-->
    <script src="assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script>
        var error;
        $(".js-pscroll").each(function () {
            $(this).css("position", "relative");
            $(this).css("overflow", "hidden");
            var ps = new PerfectScrollbar(this, {
                wheelSpeed: 1,
                scrollingThreshold: 1000,
                wheelPropagation: false,
            });

            $(window).on("resize", function () {
                ps.update();
            });
        });
    </script>


    <!--===============================================================================================-->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
    <script src="assets/js/map-custom.js"></script>
    <!--===============================================================================================-->
    <script src="assets/js/main.js"></script>
    <script src="assets/js/login-registration.js"></script>

    </html>
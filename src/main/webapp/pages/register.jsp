<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="icon"
      type="image/png"
      href="../assets/images/icons/favicon.png"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/bootstrap/css/bootstrap.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/fonts/iconic/css/material-design-iconic-font.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/fonts/linearicons-v1.0.0/icon-font.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/animate/animate.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/css-hamburgers/hamburgers.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/animsition/css/animsition.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/select2/select2.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/daterangepicker/daterangepicker.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/slick/slick.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/MagnificPopup/magnific-popup.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="../assets/vendor/perfect-scrollbar/perfect-scrollbar.css"
    />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../assets/css/util.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/main.css" />
    <!--===============================================================================================-->
    <title>User Registration</title>
  </head>
  <body>
    <jsp:include page="header.jsp" />
    <section class="bg0 p-t-104 p-b-116">
      <div class="container">
        <div class="flex-w flex-tr">
          <div
            class="size-210 bor10 p-lr-70 p-t-55 p-b-70 p-lr-15-lg w-full-md"
          >
            <form method="post" action="?controller=RegisterController">
              <h4 class="mtext-105 cl2 txt-center p-b-30">Try for free!</h4>

              <div class="bor8 m-b-20 how-pos4-parent">
                <input
                  class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30"
                  type="text"
                  name="email"
                  placeholder="Your Email Address"
                />
                <img
                  class="how-pos4 pointer-none"
                  src="../assets/images/icons/icon-email.png"
                  alt="ICON"
                />
              </div>
              <div class="bor8 m-b-20 how-pos4-parent">
                <input
                  class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30"
                  type="text"
                  name="username"
                  placeholder="Your Username"
                />
                <img
                  class="how-pos4 pointer-none"
                  src="../assets/images/icons/icon-email.png"
                  alt="ICON"
                />
              </div>
              <div class="bor8 m-b-20 how-pos4-parent">
                <input
                  class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30"
                  type="password"
                  name="password"
                  placeholder="Your Password"
                />
                <img
                  class="how-pos4 pointer-none"
                  src="../assets/images/icons/icon-email.png"
                  alt="ICON"
                />
              </div>
              <div class="text-center p-t-50 p-b-20">
                <span class="txt1">Already have an account?</span>
                <a class="txt1 bo1 hov1" href="?controller=LoginController">Log in</a>
              </div>

              <button
                class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
              >
                REGISTER
              </button>
            </form>
          </div>

          <div
            class="size-210 bor10 flex-w flex-col-m p-lr-93 p-tb-30 p-lr-15-lg w-full-md"
          >
            <div class="flex-w w-full p-b-42">
              <span class="fs-18 cl5 txt-center size-211">
                <span class="lnr lnr-map-marker"></span>
              </span>

              <div class="size-212 p-t-2">
                <span class="mtext-110 cl2"> Address </span>

                <p class="stext-115 cl6 size-213 p-t-18">
                  Coza Store Center 8th floor, 379 Hudson St, New York, NY 10018
                  US
                </p>
              </div>
            </div>

            <div class="flex-w w-full p-b-42">
              <span class="fs-18 cl5 txt-center size-211">
                <span class="lnr lnr-phone-handset"></span>
              </span>

              <div class="size-212 p-t-2">
                <span class="mtext-110 cl2"> Lets Talk </span>

                <p class="stext-115 cl1 size-213 p-t-18">+1 800 1236879</p>
              </div>
            </div>

            <div class="flex-w w-full">
              <span class="fs-18 cl5 txt-center size-211">
                <span class="lnr lnr-envelope"></span>
              </span>

              <div class="size-212 p-t-2">
                <span class="mtext-110 cl2"> Sale Support </span>

                <p class="stext-115 cl1 size-213 p-t-18">contact@example.com</p>
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
  <script src="../assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
  <!--===============================================================================================-->
  <script src="../assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
  <script>
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

  <script>
    console.log(email);
   $(document).ready(function() {
       $('#email').on('blur', function() {
           var email = $(this).val();
           $.ajax({
               url: '/ecommerce/?controller=CheckEmailServlet',
               type: 'POST',
               data: { email: email },
               success: function(response) {
                   if(response.emailExists) {
                       alert('Email already exists!');
                   }
               },
               error: function() {
                   alert('An error occurred while checking the email.');
               },
               complete: function(jqXHR) {
                   if(jqXHR.statusText !== "success") {
                       console.log("Error: Could not reach the URL.");
                   }
               }
           });
       });
   });

  </script>

  <!--===============================================================================================-->
  <!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
  <script src="../assets/js/map-custom.js"></script> -->
  <!--===============================================================================================-->
  <script src="../assets/js/main.js"></script>
</html>

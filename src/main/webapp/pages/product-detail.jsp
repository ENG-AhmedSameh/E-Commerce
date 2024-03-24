<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Product Detail</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
<link rel="icon" type="image/png" href="assets/images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/css/util.css">
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
<!--===============================================================================================-->
</head>
<body class="animsition">
	
	<!-- Header -->
	<jsp:include page="header.jsp" />
	<!-- Cart -->
	<jsp:include page="cart.jsp" />

	<!-- breadcrumb -->
	<div class="container" id="breadcrumb-placeholder">
		<!-- Breadcrumb will be filled by JS -->
	</div>

	<section class="sec-product-detail bg0 p-t-65 p-b-60" id="product-detail-section">
		<!-- Product details will be filled by JS -->
	</section>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />

	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

	<!-- Product Modal -->
	<div class="wrap-modal1 js-modal1 p-t-60 p-b-20" id="product-modal-placeholder">
		<!-- Product modal will be filled by JS -->
	</div>


<!--===============================================================================================-->	
	<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--============================================================================================-->
	<script src="assets/vendor/animsition/js/animsition.min.js"></script>
<!--============================================================================================-->
	<script src="assets/vendor/bootstrap/js/popper.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--============================================================================================-->
	<script src="assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="assets/vendor/daterangepicker/moment.min.js"></script>
	<script src="assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--============================================================================================-->
	<script src="assets/vendor/slick/slick.min.js"></script>
	<script src="assets/js/slick-custom.js"></script>
<!--============================================================================================-->
	<script src="assets/vendor/parallax100/parallax100.js"></script>

<!--===============================================================================================-->
	<script src="assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
	<script src="assets/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
	<script src="assets/vendor/sweetalert/sweetalert.min.js"></script>

<!--===============================================================================================-->
	<script src="assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<!--===============================================================================================-->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/productDetails.js"></script>
	<script>
		const productId = "${pageContext.request.getParameter("id")}";
		const contextPath = '${pageContext.request.contextPath}';
		window.onload = function() {
			fetchProductDetails(productId,contextPath);
		};
	</script>


</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to bottom right, #ede0c8, #f6f0ea); /* Beige gradient background */
            color: #ffffff; /* Set text color to white */
            margin: 0;
            padding: 0;
            height: 100vh; /* Set full viewport height */
            display: flex;
            justify-content: center;
            align-items: center;
            background-size: 150% 150%; /* Increase background size for a more pronounced gradient */
        }
        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.5); /* Semi-transparent white background */
            padding: 20px; /* Add some padding for spacing */
            border-radius: 10px; /* Add border radius */
        }
        .headline {
            font-size: 48px; /* Larger font size */
            font-weight: bold;
            margin-bottom: 40px;
            color: #f6f6f6; /* Set headline color to dark gray */
            background-color: rgb(255, 193, 7); /* Semi-transparent black background */
            padding: 20px; /* Add padding to the headline */
            border-radius: 10px; /* Add border radius */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* Add text shadow for depth */
        }
        .card {
            margin: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease;
            height: 400px;
            width: 300px;
            display: flex;
            flex-direction: column;
            background-color: #ffffff; /* White background color for cards */
        }
        .card:hover {
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card img {
            flex-grow: 1;
            width: 100%;
            height: auto;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }
        .card-body {
            padding: 20px;
            flex-grow: 0;
        }
        .card-link {
            color: #ffffff; /* Set link color to white */
            text-decoration: none;
            font-weight: bold;
            display: inline-block;
            padding: 10px 20px;
            background-color: #ffc107;
            border: 1px solid #ffc107;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .card-link:hover {
            background-color: #f60000;
            border-color: #ffca28;
            color: #fafafa;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="headline">Admin Panel</div>
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <img src="assets/images/electric%20devices.jpg" class="card-img-top" alt="Image 1">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/admin?page=productsPanel" class="card-link">View Products</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img src="assets/images/customers.jpg" class="card-img-top" alt="Image 2">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/admin?page=customersPanel" class="card-link">View Customers</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img src="assets/images/cart.jpg" class="card-img-top" alt="Image 3">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/admin?page=ordersPanel" class="card-link">View Orders</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS (Optional, if you need JavaScript) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

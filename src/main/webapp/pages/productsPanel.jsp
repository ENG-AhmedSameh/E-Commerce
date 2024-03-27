<%@ page import="com.example.ecommerce.model.DTO.ProductDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products Panel</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(to bottom right, #ede0c8, #f6f0ea); /* Beige gradient background */
    color: #ffffff; /* Set text color to white */
    margin: 0;
    padding: 0;
    height: 100vh; /* Set full viewport height */
    background-size: 150% 150%; /* Increase background size for a more pronounced gradient */
}

.container {
    text-align: center;
    background-color: rgb(243, 235, 235); /* Semi-transparent white background */
    padding: 20px; /* Add some padding for spacing */
    border-radius: 10px; /* Add border radius */
    margin-top: 50px; /* Add margin to top */
    max-width: 1300px; /* Set maximum width */
    margin-left: auto; /* Center the container horizontally */
    margin-right: auto; /* Center the container horizontally */
}

.headline {
    font-size: 36px; /* Reduce font size */
    font-weight: bold;
    margin-bottom: 20px; /* Reduce margin */
    color: #f6f6f6; /* Set headline color to dark gray */
    background-color: rgb(255, 193, 7); /* Semi-transparent black background */
    padding: 15px; /* Reduce padding */
    border-radius: 8px; /* Add border radius */
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* Add text shadow for depth */
}

.table {
    background-color: #fff; /* White background for table */
    border-radius: 10px; /* Add border radius */
    margin-top: 10px; /* Reduce margin to top */
}

.table th,
.table td {
    border: none; /* Remove border */
}

.table th {
    background-color: rgb(255, 193, 7); /* Header background color */
    color: #fff; /* Header text color */
}

.table-striped tbody tr:nth-of-type(odd) {
    background-color: rgba(255, 255, 255, 0.5); /* Semi-transparent white background for odd rows */
}

.table-striped tbody tr:nth-of-type(even) {
    background-color: rgba(255, 255, 255, 0.7); /* Semi-transparent white background for even rows */
}

.btn {
    color: #fff; /* Button text color */
    border-radius: 5px; /* Add border radius */
}

.btn-primary {
    background-color: #ffc107; /* Primary button background color */
}

.btn-danger {
    background-color: #ff0000; /* Danger button background color */
}

.btn-primary:hover, .btn-danger:hover {
    background-color: #ffca28; /* Button background color on hover */
}

/* Adjusting input width for image URLs */
.form-control-url {
    width: calc(100% - 80px); /* Adjust width as needed */
}

/* Styling links */
a {
    color: blue; /* Link color */
    text-decoration: underline; /* Underline link */
}

a:hover {
    color: darkblue; /* Link color on hover */
    text-decoration: none; /* Remove underline on hover */
}

/* Tooltip styles */
.tooltip {
    position: relative;
    display: inline-block;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 200px;
    background-color: #000;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -100px;
    opacity: 0;
    transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #000 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}

label {
  color: black;
  text-align: center;
}

a {
  text-decoration: none;
  display: inline-block;
  padding: 8px 16px;
}

a:hover {
  background-color: #ddd;
  color: black;
}

.previous {
  background-color: #f1f1f1;
  color: black;
}

.next {
  background-color: #04AA6D;
  color: white;
}

.round {
  border-radius: 50%;
}

    </style>
</head>
<body>


<div class="container mt-5">
    <!-- Products Section -->
    <div class="row">
        <div class="col-md-12">
            <h2 class="headline text-center" >Products</h2>
            <%
               
                HttpSession sessionObj = request.getSession(); 
                int pageValue = (int) sessionObj.getAttribute("page");
                
            %>
            <h1 hidden class="headline text-center"><%= pageValue %></h1>
            

            <%--  Add Button--%>
            <button type="button" id="backBtn" class="btn btn-secondary mr-1" onclick="window.location.href='${pageContext.request.contextPath}/admin'">
                Back
            </button>



            <button type="button" id="addBtn" class="btn btn-primary text-center" data-toggle="modal" data-target="#addProductModal">
                Add Product
            </button>

           

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <!-- <th>Discount %</th> -->
                    <th>Main Image</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody id="productTableBody">
                <!-- Product details will be dynamically added here -->
                <% List<ProductDto> products = (List<ProductDto>) request.getSession().getAttribute("productsPanel");
                    if (products != null && !products.isEmpty()) {
                        for (ProductDto product : products) { %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getAvailableQuantity() %></td>
                    <td><%= product.getPrice() %></td>
                    <!-- <td><%= product.getDiscountPercentage() %></td> -->
                    <td><img src="<%= product.getMainImageUrl() %>" alt="_blank" class="img-fluid">  </td>
                   <!-- <%= truncateUrl(product.getMainImageUrl()) %></a> -->
                    <td><%= product.getCategory().getName() %></td>
                     <!-- <td>
                       
                        <button class="btn btn-primary btn-edit" data-product-id="<%= product.getId() %>">Edit</button>
                      
                        <button class="btn btn-danger btn-delete" data-product-id="<%= product.getId() %>">Delete</button>
                    </td>  -->

                    <td class="text-center">
                        <div class="d-inline-block">
                            <!-- Edit button -->
                            <button class="btn btn-primary btn-edit mr-2" data-product-id="<%= product.getId() %>">Edit</button>
                            <!-- Delete button -->
                            <button class="btn btn-danger btn-delete" data-product-id="<%= product.getId() %>">Delete</button>
                        </div>
                    </td>


                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="11">No products available.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <a href="#" class="previous" onclick="updatePage(-1)">&laquo; Previous</a>
<a href="#" class="next" onclick="updatePage(1)">Next &raquo;</a>
        </div>
    </div>
</div>

<!-- edit  modal -->
<div class="modal fade" id="editProductModal" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: #000;" id="editProductModalLabel">Edit Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                
                <form id="editProductForm">
                    <!-- Input fields for editing product details -->
                    <input type="hidden" id="editProductId">
                    <div class="form-group">
                        <label for="editName">Name:</label>
                        <input type="text" class="form-control" id="editName">
                    </div>
                    <div class="form-group">
                        <label for="editDescription">Description:</label>
                        <textarea class="form-control" id="editDescription" resize="none" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="editQuantity">Quantity:</label>
                        <input type="number" class="form-control" id="editQuantity">
                    </div>
                    <div class="form-group">
                        <label for="editPrice">Price:</label>
                        <input type="number" class="form-control" id="editPrice">
                    </div>
                 
                    <div class="form-group">
                        <label for="editMainImage">Main Image URL:</label>
                        <input type="text" class="form-control" id="editMainImage">
                    </div>
                    <div class="form-group">
                        <label for="secondUrlImage">Seconde Image URL:</label>
                        <input type="text" class="form-control" id="secondUrlImage">
                    </div>

                    <div class="form-group">
                        <label for="thirdUrlImage">Third Image URL:</label>
                        <input type="text" class="form-control" id="thirdUrlImage">
                    </div>

                    <div class="form-group">
                        <label for="editCategory">Category:</label>
                         <input type="text" class="form-control" readonly id="editCategory"> 
                        <!-- <select class="form-control"  name="editCategory" id="editCategory">
                            <option value="1">Smart Phone</option>
                            <option value="2">Laptop</option>
                            <option value="3">Accessory</option>
                        </select> -->
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="saveEditBtn">Save Changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Add Modal -->
<div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: #000;" id="addProductModalLabel">Add Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form for adding product -->
                <form id="addProductForm">
                    <!-- Your form fields -->
                    <div class="form-group">
                        <label for="addName">Name</label>
                        <input type="text" class="form-control" id="addName" name="name">
                    </div>
                    <div class="form-group">
                        <label for="addDescription">Description</label>
                        <textarea class="form-control" id="addDescription" name="description" resize="none" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="addQuantity">Quantity</label>
                        <input type="number" class="form-control" id="addQuantity" name="quantity">
                    </div>
                    <div class="form-group">
                        <label for="addPrice">Price</label>
                        <input type="number" class="form-control" id="addPrice" name="price">
                    </div>
                  
                    <div class="form-group">
                        <label for="addMainImage">Main Image URL</label>
                        <input type="text" class="form-control" id="addMainImage" name="mainImage">
                    </div>

                    <div class="form-group">
                        <label for="addSecondUrlImage">Second Image URL</label>
                        <input type="text" class="form-control" id="addSecondUrlImage" name="secondImage">
                    </div>

                    <div class="form-group">
                        <label for="addThirdUrlImage">Third Image URL</label>
                        <input type="text" class="form-control" id="addThirdUrlImage" name="thirdImage">
                    </div>


                    <div class="form-group">
                        <label for="addCategory">Category</label>
                        <!-- <input type="text" class="form-control" id="addCategory" name="category"> -->
                        <select class="form-control"  name="category" id="addCategory">
                            <option  selected ="1">Smart Phone</option>
                            <option value="2">Laptop</option>
                            <option value="3">Accessory</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" id="submitAddBtn">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>




<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%--<script src="assets/js/pro.js"></script>--%>



<script>

function updatePage(change) {
        
    if(change===1){
        <%
               
            // HttpSession sessionObj = request.getSession(); 
            int Value = (int) sessionObj.getAttribute("page");
            session.setAttribute("page", Value - 1);
        %>
       
    }else{
        <%
                // Obtain the session
            // HttpSession sessionObj = request.getSession();
                
            int num = (int) sessionObj.getAttribute("page");
            session.setAttribute("page", num - 1);
        %>
       
    }
        
    }
    $(document).ready(function() {

        $('.btn-edit').click(function () {
            var productId = $(this).data('product-id');
            var product = getProductDetails(productId);

            $('#editProductId').val(product.id);
            $('#editName').val(product.name);
            $('#editDescription').val(product.description);
            $('#editQuantity').val(product.availableQuantity);
            $('#editPrice').val(product.price);
            $('#editDiscount').val(product.discountPercentage);
            $('#editMainImage').val(product.mainImageUrl);
            $('#secondUrlImage').val(product.productImages[0].id.imageUrl)
            $('#thirdUrlImage').val(product.productImages[1].id.imageUrl)
            $('#editCategory').val(product.category.name);

            $('#editProductModal').modal('show');
        });

        $('#saveEditBtn').click(function () {
            // Retrieve updated product details from the modal fields
            var productId = $('#editProductId').val();
            var updatedProduct = {
                id: productId,
                name: $('#editName').val(),
                description: $('#editDescription').val(),
                availableQuantity: $('#editQuantity').val(),
                price: $('#editPrice').val(),
                // discountPercentage: $('#editDiscount').val(),
                mainImageUrl: $('#editMainImage').val(),
                categoryId: $('#editCategory').val(),
                secondImageUrl: $('#secondUrlImage').val(),
                thirdImageUrl: $('#thirdUrlImage').val(),
            };
            console.log(updatedProduct);

            // Send the updated product details to the server for processing
            $.ajax({
                url: 'admin?page=productsPanel', // Servlet URL
                type: 'POST', // Request type
                contentType: 'application/json', // Data format
                data: JSON.stringify(updatedProduct), // Data to be sent
                success: function (response) {
                    // Handle the success response
                    console.log('Product updated successfully:', response);
                    // Optionally, you can close the modal or perform any other action
                    $('#editProductModal').modal('hide');
                },
                error: function (xhr, status, error) {
                    // Handle the error response
                    console.error('Error updating product:', error);
                    if (xhr.status === 500) {
                        // Display an error message to the user
                        alert('Internal Server Error. Please try again later.');
                    } else {
                        // Display a generic error message
                        alert('An error occurred while updating the product.');
                    }
                }
            });
        });

        // Delete button click event
        $('.btn-delete').click(function () {
            // Retrieve the product ID associated with the clicked delete button
            var productId = $(this).data('product-id');

            // Send the delete request to the server for processing
            $.ajax({
                url: 'admin?page=deleteProductsPanel', // Servlet URL with action parameter
                type: 'POST', // Request type
                contentType: 'application/json', // Data format
                data: JSON.stringify({ id: productId }), // Data to be sent
                success: function (response) {
                    // Handle the success response
                    console.log('Product deleted successfully:', response);
                    // Optionally, you can reload the page or update the product list
                    location.reload();
                },
                error: function (xhr, status, error) {
                    // Handle the error response
                    console.error('Error deleting product:', error);
                    if (xhr.status === 500) {
                        // Display an error message to the user
                        alert('Internal Server Error. Please try again later.');
                    } else {
                        // Display a generic error message
                        alert('An error occurred while deleting the product.');
                    }
                }
            });
        });

        //-----------------------------------------------------------------------------------
        // Event listener for the "Add" button to open the modal form
        $('#addBtn').on('click', function() {
            console.log('add button in console');
            $('#addProductModal').modal('show');
        });

        

        <%--$('#backBtn').on('click', function() {--%>
        <%--    console.log('Back button in console');--%>

        <%--    $.ajax({--%>
        <%--        url: '${pageContext.request.contextPath}/admin', // Servlet URL with action parameter--%>
        <%--        type: 'POST', // Request type--%>
        <%--        contentType: 'application/json', // Data format--%>
        <%--        data: null, // Data to be sent--%>
        <%--        success: function (response) {--%>
        <%--            // Handle the success response--%>
        <%--            console.log('Product deleted successfully:', response);--%>
        <%--            // Optionally, you can reload the page or update the product list--%>
        <%--            location.reload();--%>
        <%--        },--%>
        <%--        error: function (xhr, status, error) {--%>
        <%--            // Handle the error response--%>
        <%--            console.error('Error deleting product:', error);--%>
        <%--            if (xhr.status === 500) {--%>
        <%--                // Display an error message to the user--%>
        <%--                alert('Internal Server Error. Please try again later.');--%>
        <%--            } else {--%>
        <%--                // Display a generic error message--%>
        <%--                alert('An error occurred while deleting the product.');--%>
        <%--            }--%>
        <%--        }--%>
        <%--    });--%>
            
        });

        // Event listener for the "Submit" button inside the modal form
        $('#submitAddBtn').click(function () {
            // Retrieve data from the modal form fields
            event.preventDefault();
            var newProduct = {
                name: $('#addName').val(),
                description: $('#addDescription').val(),
                availableQuantity: $('#addQuantity').val(),
                price: $('#addPrice').val(),
                // discountPercentage: $('#addDiscount').val(),
                mainImageUrl: $('#addMainImage').val(),
                secondImageUrl: $('#addSecondUrlImage').val(),
                thirdImageUrl: $('#addThirdUrlImage').val(),
                categoryId: $('#addCategory').val()
            };
            console.log(newProduct);

            // Send the new product data to the server for processing
            $.ajax({
                url: 'admin?page=addProducts', // Server endpoint
                type: 'POST', // Request type
                contentType: 'application/json', // Data format
                data: JSON.stringify(newProduct), // Data to be sent
                success: function (response) {
                    // Handle success response
                    console.log('Product added successfully:', response);
                    // Optionally, you can close the modal or perform any other action
                    $('#addProductModal').modal('hide');
                    // Reload the page or update the product list as needed
                    location.reload();
                },
                error: function (xhr, status, error) {
                    // Handle error response
                    console.error('Error adding product:', error);
                    if (xhr.status === 500) {
                        // Display an error message to the user
                        alert('Internal Server Error. Please try again later. Hajar ');
                    } else {
                        // Display a generic error message
                        alert('An error occurred while adding the product.');
                    }
                }
            });
        });

        //-----------------------------------------------------------------------------------



    });





    // Function to retrieve product details from the session
    function getProductDetails(productId) {
        // Assuming products are stored in the session as an array
        var products = <%= new Gson().toJson(request.getSession().getAttribute("productsPanel"))%>;

        // Find the product with the given productId
        for (var i = 0; i < products.length; i++) {
            if (products[i].id === productId) {
                return products[i];
            }
        }

        return null;
    }



</script>

</body>
</html>


<%!
    // Function to truncate long URLs
    private String truncateUrl(String url) {
        int maxLength = 20; // Maximum length of displayed URL
        if (url.length() > maxLength) {
            return url.substring(0, maxLength - 3) + "...";
        }
        return url;
    }
%>
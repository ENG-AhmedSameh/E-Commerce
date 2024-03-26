
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
    <link rel="stylesheet" href="assets/css/pro.css">
</head>
<body>

<div class="container mt-5">
    <!-- Products Section -->
    <div class="row">
        <div class="col-md-12">
            <h2 class="headline">Products</h2>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Discount %</th>
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
                    <td><%= product.getDiscountPercentage() %></td>
                    <td><a href="<%= product.getMainImageUrl() %>" target="_blank"><%= truncateUrl(product.getMainImageUrl()) %></a></td>
                    <td><%= product.getCategory().getName() %></td>
                    <td>
                        <!-- Edit button -->
                        <button class="btn btn-primary btn-edit" data-product-id="<%= product.getId() %>">Edit</button>
                        <!-- Delete button -->
                        <button class="btn btn-danger btn-delete" data-product-id="<%= product.getId() %>">Delete</button>
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
        </div>
    </div>
</div>

<!-- edit  modal -->
<div class="modal fade" id="editProductModal" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">Edit Product</h5>
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
                        <textarea class="form-control" id="editDescription"></textarea>
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
                        <label for="editDiscount">Discount %:</label>
                        <input type="number" class="form-control" id="editDiscount">
                    </div>
                    <div class="form-group">
                        <label for="editMainImage">Main Image URL:</label>
                        <input type="text" class="form-control" id="editMainImage">
                    </div>
                    <div class="form-group">
                        <label for="editCategory">Category:</label>
                        <input type="text" class="form-control" id="editCategory">
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



<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%--<script src="assets/js/pro.js"></script>--%>


<script>
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
                discountPercentage: $('#editDiscount').val(),
                mainImageUrl: $('#editMainImage').val(),
                category: {
                    name: $('#editCategory').val()
                }
            };

            // Send the updated product details to the server for processing
            $.ajax({
                url: 'front?page=productsPanel', // Servlet URL
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
                url: 'front?page=deleteProductsPanel', // Servlet URL with action parameter
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
    });

    // Function to retrieve product details from the session
    function getProductDetails(productId) {
        // Assuming products are stored in the session as an array
        var products = <%= new Gson().toJson(request.getSession().getAttribute("productsPanel")) %>;

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
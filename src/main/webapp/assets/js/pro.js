$(document).ready(function() {
    // Event listener for edit button
    $('.btn-edit').click(function() {
        var productId = $(this).data('product-id');
        var product = getProductById(productId); // Function to fetch product details by ID
        populateEditForm(product);
        $('#editProductModal').modal('show');
    });

    // Function to populate edit form with product details
    function populateEditForm(product) {
        $('#editProductId').val(product.id);
        $('#editName').val(product.name);
        $('#editDescription').val(product.description);
        $('#editQuantity').val(product.availableQuantity);
        $('#editPrice').val(product.price);
        $('#editDiscount').val(product.discountPercentage);
        $('#editMainImage').val(product.mainImageUrl);
        $('#editCategory').val(product.category.name);
    }

    // Event listener for save edit button
    $('#saveEditBtn').click(function() {
        var editedProduct = {
            id: $('#editProductId').val(),
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
        saveEditedProduct(editedProduct);
    });

    // Function to send AJAX request to update product
    function saveEditedProduct(product) {
        $.ajax({
            type: 'POST',
            url: 'font?page=productsPanel', // Replace 'your_servlet_url' with the actual URL of your servlet
            data: product,
            success: function(response) {
                // Handle success response
                $('#editProductModal').modal('hide');
                // Redirect or update UI as needed
            },
            error: function(xhr, status, error) {
                // Handle error
            }
        });
    }
});
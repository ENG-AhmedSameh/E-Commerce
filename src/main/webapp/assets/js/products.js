// Sample product data
let products = [];

// Function to render products
function renderProducts() {
    const productTableBody = document.getElementById('productTableBody');
    productTableBody.innerHTML = '';

    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.quantity}</td>
                <td>${product.price}</td>
                <td>${product.discount}</td>
                <td><a href="${product.mainImageUrl}" target="_blank">${truncateUrl(product.mainImageUrl)}</a></td>
                <td><a href="${product.secondImageUrl}" target="_blank">${truncateUrl(product.secondImageUrl)}</a></td>
                <td><a href="${product.thirdImageUrl}" target="_blank">${truncateUrl(product.thirdImageUrl)}</a></td>
                <td>${product.category}</td>
                <td>
                    <button type="button" class="btn btn-primary" onclick="editProduct(${product.id})">Edit</button>
                    <button type="button" class="btn btn-danger" onclick="deleteProduct(${product.id})">Delete</button>
                </td>
            `;
        productTableBody.appendChild(row);
    });
}

// Function to add product
function addProduct() {
    const productName = document.getElementById('productName').value;
    const productDescription = document.getElementById('productDescription').value;
    const productQuantity = document.getElementById('productQuantity').value;
    const productPrice = document.getElementById('productPrice').value;
    const productDiscount = document.getElementById('productDiscount').value;
    const productCategory = document.getElementById('productCategory').value;
    const mainImageUrl = document.getElementById('mainImageUrl').value;
    const secondImageUrl = document.getElementById('secondImageUrl').value;
    const thirdImageUrl = document.getElementById('thirdImageUrl').value;

    const newProduct = {
        id: products.length + 1,
        name: productName,
        description: productDescription,
        quantity: productQuantity,
        price: productPrice,
        discount: productDiscount,
        category: productCategory,
        mainImageUrl: mainImageUrl,
        secondImageUrl: secondImageUrl,
        thirdImageUrl: thirdImageUrl
    };

    products.push(newProduct);
    renderProducts();
    $('#addProductModal').modal('hide');
}

// Function to truncate long URLs
function truncateUrl(url) {
    const maxLength = 20; // Maximum length of displayed URL
    if (url.length > maxLength) {
        return url.substring(0, maxLength - 3) + '...';
    }
    return url;
}

// Function to edit product
function editProduct(productId) {
    // Find the product with the given ID
    const product = products.find(prod => prod.id === productId);
    // Fill the form fields with the product data
    document.getElementById('editProductName').value = product.name;
    document.getElementById('editProductDescription').value = product.description;
    document.getElementById('editProductQuantity').value = product.quantity;
    document.getElementById('editProductPrice').value = product.price;
    document.getElementById('editProductDiscount').value = product.discount;
    document.getElementById('editProductCategory').value = product.category;
    document.getElementById('editMainImageUrl').value = product.mainImageUrl;
    document.getElementById('editSecondImageUrl').value = product.mainImageUrl;
    document.getElementById('editThirdImageUrl').value = product.mainImageUrl;
    // Show the modal
    $('#editProductModal').modal('show');
    // Handle the save changes button click
    document.getElementById('saveChangesBtn').onclick = function() {
        // Update the product data
        product.name = document.getElementById('editProductName').value;
        product.description = document.getElementById('editProductDescription').value;
        product.quantity = document.getElementById('editProductQuantity').value;
        product.price = document.getElementById('editProductPrice').value;
        product.discount = document.getElementById('editProductDiscount').value;
        product.category = document.getElementById('editProductCategory').value;
        product.mainImageUrl = document.getElementById('editMainImageUrl').value;
        product.mainImageUrl = document.getElementById('editSecondImageUrl').value;
        product.mainImageUrl = document.getElementById('editThirdImageUrl').value;
        // Re-render the products table
        renderProducts();
        // Hide the modal
        $('#editProductModal').modal('hide');
    };
}

// Function to delete product
function deleteProduct(productId) {
    // Implement delete functionality here
    console.log("Delete product with ID:", productId);
    products = products.filter(product => product.id !== productId);
    renderProducts();
}

// Initial rendering
renderProducts();

// Add Product form submit event listener
document.getElementById('addProductForm').addEventListener('submit', function(event) {
    event.preventDefault();
    addProduct();
});
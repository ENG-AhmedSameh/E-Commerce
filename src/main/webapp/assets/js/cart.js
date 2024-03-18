function loadProducts() {
    var cartItems = JSON.parse(localStorage.getItem("cartItems"));

    if (cartItems !== null) {
        for (var i = 0; i < cartItems.length; i++) {
            var item = cartItems[i];
            console.log(item);

            var html = `<li class="header-cart-item flex-w flex-t m-b-12">
                <div class="header-cart-item-img">
                    <img src="${item.mainImageUrl}" alt="IMG" />
                </div>
                <div class="header-cart-item-txt p-t-8">
                    <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">${item.name}</a>
                    <span class="header-cart-item-info">${item.quantity} x $${item.price}</span>
                </div>
            </li>`;
            document.getElementById("items").innerHTML += html;
        }
    }
}


// window.onload = function () {
//     console.log("hello")
//     loadProducts();
// }


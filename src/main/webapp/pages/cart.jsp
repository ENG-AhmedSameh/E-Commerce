  <!DOCTYPE html>
  <html lang="en">

  <head>
    <script>
      function loadProducts() {
        var cartItems = JSON.parse(localStorage.getItem("cartItems"));
        console.log("hello")
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
                    <span class="header-cart-item-info">${item.quantity} x ${item.price} EGP</span>
                </div>
            </li>`;
            document.getElementById("items").innerHTML += html;
          }
        }
      }

    </script>
  </head>

  <body >
    <div class="wrap-header-cart js-panel-cart">
      <div class="s-full js-hide-cart"></div>

      <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
          <span class="mtext-103 cl2"> Your Cart </span>

          <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
            <i class="zmdi zmdi-close"></i>
          </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
          <ul id="items" class="header-cart-wrapitem w-full">



          </ul>

          <div class="w-full">
            <div class="header-cart-total w-full p-tb-40">Total: 00.00 EGP</div>

            <div class="header-cart-buttons flex-w w-full">
              <a href="${pageContext.request.contextPath}/front?page=ShoppingCart"
                class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                View Cart
              </a>

              <a href="shopping-cart.jsp"
                class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                Check Out
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="../assets/js/cart.js"></script>
    <script src="../assets/js/main.js"></script>

  </body>

  </html>
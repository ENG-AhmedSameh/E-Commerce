var testProduct;
function fetchProductDetails(id,contextPath) {
    // Fetch product data from your servlet
    fetch('front?page=productDetailsData&id='+id)
        .then(response => response.json())
        .then(product => {
            // Call functions to populate your HTML with the product data
            testProduct = product;
            populateBreadcrumb(product,contextPath);
            populateProductDetail(product);
            populateProductModal(product);
        })
        .catch(error => console.error('Error fetching product data:', error));
}

function populateBreadcrumb(product,contextPath) {
    const breadcrumbContainer = document.getElementById('breadcrumb-placeholder');
    breadcrumbContainer.innerHTML = `
        <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
          <a href="${contextPath}/front?page=home" class="stext-109 cl8 hov-cl1 trans-04">
            Home
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
          </a>

          <a href="${contextPath}/front?page=shop" class="stext-109 cl8 hov-cl1 trans-04">
            ${product.category.name}
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
          </a>

          <span class="stext-109 cl4">
            ${product.name}
          </span>
        </div>
      `;
}

function populateProductDetail(product) {
    const detailSection = document.getElementById('product-detail-section');
    detailSection.innerHTML = `
        
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-7 p-b-30">
                    <div class="p-l-25 p-r-30 p-lr-0-lg">
                        <div class="wrap-slick3 flex-sb flex-w">
                            <div class="wrap-slick3-dots"></div>
                            <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                            <div class="slick3 gallery-lb">
                                <div class="item-slick3" data-thumb="${product.mainImage}">
                                    <div class="wrap-pic-w pos-relative">
                                        <img src="${product.mainImageUrl}" alt="IMG-PRODUCT">

                                            <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.mainImageUrl}">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                    </div>
                                </div>

                                <div class="item-slick3" data-thumb="${product.productImages[0].id.imageUrl}">
                                    <div class="wrap-pic-w pos-relative">
                                        <img src="${product.productImages[0].id.imageUrl}" alt="IMG-PRODUCT">

                                            <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.productImages[0].id.imageUrl}">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                    </div>
                                </div>

                                <div class="item-slick3" data-thumb="${product.productImages[1].id.imageUrl}">
                                    <div class="wrap-pic-w pos-relative">
                                        <img src="${product.productImages[1].id.imageUrl}" alt="IMG-PRODUCT">

                                            <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.productImages[1].id.imageUrl}">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-5 p-b-30">
                    <div class="p-r-50 p-t-5 p-lr-0-lg">
                        <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                            ${product.name}
                        </h4>

                        <span class="mtext-106 cl2">
							${product.price} EGP
						</span>

                        <p class="stext-102 cl3 p-t-23">
                            ${product.description}
                        </p>

                        <!--  -->
                        <div class="p-t-33">

                            <div class="flex-w flex-r-m p-b-10">
                                <div class="size-204 flex-w flex-m respon6-next">
                                    <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                        <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                            <i class="fs-16 zmdi zmdi-minus"></i>
                                        </div>
                    
                                        <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">
                    
                                        <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                            <i class="fs-16 zmdi zmdi-plus"></i>
                                        </div>
                                    </div>
                                    <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                        Add to cart
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!--  -->
                        <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                            <div class="flex-m bor9 p-r-10 m-r-11">
                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                                    <i class="zmdi zmdi-favorite"></i>
                                </a>
                            </div>

                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                                <i class="fa fa-facebook"></i>
                            </a>

                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                                <i class="fa fa-twitter"></i>
                            </a>

                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                                <i class="fa fa-google-plus"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        `;

    document.querySelector('.btn-num-product-up').addEventListener('click', function() {
        var input = document.querySelector('.num-product');
        input.value = parseInt(input.value) + 1;
    });

    document.querySelector('.btn-num-product-down').addEventListener('click', function() {
        var input = document.querySelector('.num-product');
        if (parseInt(input.value) > 1) {
            input.value = parseInt(input.value) - 1;
        }
    });


    $(document).ready(function() {
        $('.parallax100').parallax100();


        $('.gallery-lb').each(function () { // the containers for all your galleries
            $(this).magnificPopup({
                delegate: 'a', // the selector for gallery item
                type: 'image',
                gallery: {
                    enabled: true
                },
                mainClass: 'mfp-fade'
            });
        });

        $(".js-select2").each(function () {
            $(this).select2({
                minimumResultsForSearch: 20,
                dropdownParent: $(this).next('.dropDownSelect2')
            });
        })
        /*---------------------------------------------*/

        $('.js-addcart-detail').each(function () {
            $(this).on('click', function () {
                var cartItem = {
                    id: product.id,
                    mainImageUrl: product.mainImageUrl,
                    name: product.name,
                    price: product.price,
                    category: product.category,
                    quantity: parseInt($(".num-product").val())
                };
                var cartItems = JSON.parse(sessionStorage.getItem("cartItems")) || [];
                var existingItemIndex = cartItems.findIndex(item => item.id === cartItem.id);
                if (existingItemIndex !== -1) {
                    cartItems[existingItemIndex].quantity += cartItem.quantity;
                } else {
                    cartItems.push(cartItem);
                }
                sessionStorage.setItem("cartItems", JSON.stringify(cartItems));
                swal(cartItem.name, "is added to cart !", "success");
                $('.js-show-cart').attr('data-notify', cartItems.length);
                SendToCart();
            });
        });


        $('.js-pscroll').each(function () {
            $(this).css('position', 'relative');
            $(this).css('overflow', 'hidden');
            var ps = new PerfectScrollbar(this, {
                wheelSpeed: 1,
                scrollingThreshold: 1000,
                wheelPropagation: false,
            });

            $(window).on('resize', function () {
                ps.update();
            })
        });
    });

}
function SendToCart() {
    var xmlhttp;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        console.error('XMLHttpRequest is not supported by this browser.');
        return;
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4) {
            if (xmlhttp.status === 200) {
                console.log("Items added to cart successfully.");
            } else {
                console.error('Request failed with status ' + xmlhttp.status);
            }
        }
    };

    var url = 'front?page=AddToCart';
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader('Content-Type', 'application/json');

    var cartItems = JSON.parse(sessionStorage.getItem("cartItems")) || [];
    var transformedCartItems = cartItems.map(function(item) {
        return { id: item.id, quantity: item.quantity };
    });

    var data = JSON.stringify(transformedCartItems);

    console.log("data "+ data);

    xmlhttp.send(data);
}
function populateProductModal(product) {
    const modalPlaceholder = document.getElementById('product-modal-placeholder');
    // For simplicity, let's say your modal just repeats the product's name and price
    modalPlaceholder.innerHTML = `
        <div class="overlay-modal1 js-hide-modal1"></div>

        <div class="container">
            <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
                <button class="how-pos3 hov3 trans-04 js-hide-modal1">
                    <img src="assets/images/icons/icon-close.png" alt="CLOSE">
                </button>

                <div class="row">
                    <div class="col-md-6 col-lg-7 p-b-30">
                        <div class="p-l-25 p-r-30 p-lr-0-lg">
                            <div class="wrap-slick3 flex-sb flex-w">
                                <div class="wrap-slick3-dots"></div>
                                <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                                <div class="slick3 gallery-lb">
                                    <div class="item-slick3" data-thumb="${product.mainImageUrl}">
                                        <div class="wrap-pic-w pos-relative">
                                            <img src="${product.mainImageUrl}" alt="IMG-PRODUCT">

                                                <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.mainImageUrl}">
                                                    <i class="fa fa-expand"></i>
                                                </a>
                                        </div>
                                    </div>
                                    <div class="item-slick3" data-thumb="${product.productImages[0].id.imageUrl}">
                                        <div class="wrap-pic-w pos-relative">
                                            <img src="${product.productImages[0].id.imageUrl}" alt="IMG-PRODUCT">

                                                <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.productImages[0].id.imageUrl}">
                                                    <i class="fa fa-expand"></i>
                                                </a>
                                        </div>
                                    </div>
                                    <div class="item-slick3" data-thumb="${product.productImages[1].id.imageUrl}">
                                        <div class="wrap-pic-w pos-relative">
                                            <img src="${product.productImages[1].id.imageUrl}" alt="IMG-PRODUCT">

                                                <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${product.productImages[1].id.imageUrl}">
                                                    <i class="fa fa-expand"></i>
                                                </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-5 p-b-30">
                        <div class="p-r-50 p-t-5 p-lr-0-lg">
                            <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                                ${product.name}
                            </h4>

                            <span class="mtext-106 cl2">
								${product.price} EGP
							</span>

                            <p class="stext-102 cl3 p-t-23">
                                ${product.description}
                            </p>

                            <!--  -->
                            <div class="p-t-33">
                                <div class="flex-w flex-r-m p-b-10">
                                    <div class="size-204 flex-w flex-m respon6-next">
                                        <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                            <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-minus"></i>
                                            </div>
                        
                                            <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">
                        
                                            <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-plus"></i>
                                            </div>
                                        </div>
                                        <button  class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                            Add to cart
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <!--  -->
                            <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                                <div class="flex-m bor9 p-r-10 m-r-11">
                                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                                        <i class="zmdi zmdi-favorite"></i>
                                    </a>
                                </div>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                                    <i class="fa fa-facebook"></i>
                                </a>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                                    <i class="fa fa-twitter"></i>
                                </a>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
}


// function addtoCart2(){
//     console.log("pressed on cart");
//     var cartItem = {
//         id: testProduct.id,
//         mainImageUrl: testProduct.mainImageUrl,
//         name: testProduct.name,
//         price: testProduct.price,
//         category: testProduct.category,
//         quantity: parseInt($(".num-product").val())
//     };
//     var cartItems = JSON.parse(sessionStorage.getItem("cartItems")) || [];
//     var existingItemIndex = cartItems.findIndex(item => item.id === cartItem.id);
//     if (existingItemIndex !== -1) {
//         cartItems[existingItemIndex].quantity += cartItem.quantity;
//     } else {
//         cartItems.push(cartItem);
//     }
//     sessionStorage.setItem("cartItems", JSON.stringify(cartItems));
//     swal(cartItem.name, "is added to cart !", "success");
//     $('.js-show-cart').attr('data-notify', cartItems.length);
// }





function showProductPopUp(id) {
    getProductDetails(id, function(product) {
        console.log("The product is: "+product);
        $.magnificPopup.open({
            items: {
                src: `<div class="container">
                     <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
                        <div class="row">
                            <div class="col-md-6 col-lg-7 p-b-30">
                                <div class="p-l-25 p-r-30 p-lr-0-lg">
                                     <div class="wrap-slick3 flex-sb flex-w">
                                        <div class="wrap-slick3-dots"></div>
                                        <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
                                   
                                        <div class="slick3 gallery-lb">
                                            <div class="item-slick3" data-thumb="product.mainImageUrl">
                                                <div class="wrap-pic-w pos-relative">
                                                    <img src="${product.mainImageUrl}" alt="IMG-PRODUCT" class="product-image ">
                                                    <div class="zoom-square-overlay"></div>
                                                </div>
                                            </div>
                                                                                       ${product.productImages.map(img => `
                                            <div class="item-slick3" data-thumb="${img.id.imageUrl}">
                                                <div class="wrap-pic-w pos-relative">
                                                    <img src="${img.id.imageUrl}" alt="IMG-PRODUCT">
                                                </div>
                                            </div>
                                            `).join('')}
                                        </div>
                                    </div>
                                </div>
                            </div>
        
                            <div class="col-md-6 col-lg-5 p-b-30">
                            <div class="p-r-50 p-t-5 p-lr-0-lg">
                                <h4 class="mtext-105 cl2 js-name-detail p-b-14">${product.name}</h4>
                                <span class="mtext-106 cl2">${product.price} EGP</span>
                                <p class="stext-102 cl3 p-t-23">${product.description}</p>
                                <!-- Quantity and Add to Cart button -->
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
                         </div>
                     </div>
                 </div>`
            },
            callbacks: {
                close: () => {
                    // Callback for popup close
                },
                open: () => {
                    initializePopUpComponents(product);
                }
            }
        });
    });
}

function getProductDetails(id, callback) {
    // preventDefault();
    var xmlhttp;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4) {
            console.log(xmlhttp.status);
            if (xmlhttp.status === 200) {
                var product = JSON.parse(xmlhttp.responseText);
                callback(product);
            }
        }
    };

    var url = "front?page=productDetails&id=" + id;
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}

function initializePopUpComponents(product) {
    // Initialize quantity change buttons
    $(".btn-num-product-up").on("click", function() {
        var $numProduct = $(this).closest('.wrap-num-product').find('.num-product');
        $numProduct.val(parseInt($numProduct.val()) + 1);
    });

    $(".btn-num-product-down").on("click", function() {
        var $numProduct = $(this).closest('.wrap-num-product').find('.num-product');
        var num = parseInt($numProduct.val());
        if (num > 1) $numProduct.val(num - 1);
    });

    // Initialize Slick slider with a short delay to ensure the DOM is ready
    setTimeout(() => initializeSlickSliders(), 10);

    // Add to cart button action
    $(".js-addcart-detail").on("click", function() {
        addToCart(product);
        return false; // Prevent default action
    });

    console.log($('.slick3'));

    // Initialize Slick slider
    // $('.slick3').slick({
    //     slidesToShow: 1,
    //     slidesToScroll: 1,
    //     arrows: true,
    //     fade: true,
    //     prevArrow: '<button class="arrow-slick2 prev-slick2"><i class="fa fa-angle-left" aria-hidden="true"></i></button>',
    //     nextArrow: '<button class="arrow-slick2 next-slick2"><i class="fa fa-angle-right" aria-hidden="true"></i></button>',
    //     asNavFor: '.wrap-slick3-dots'
    // });
    //
    // // Initialize Slick slider dots
    // $('.wrap-slick3-dots').slick({
    //     slidesToShow: 3,
    //     slidesToScroll: 1,
    //     asNavFor: '.slick3',
    //     centerMode: true,
    //     focusOnSelect: true,
    //     centerPadding: '40px',
    //     responsive: [
    //         {
    //             breakpoint: 992,
    //             settings: {
    //                 slidesToShow: 3,
    //                 slidesToScroll: 1,
    //                 centerPadding: '30px',
    //             }
    //         },
    //         {
    //             breakpoint: 768,
    //             settings: {
    //                 slidesToShow: 3,
    //                 slidesToScroll: 1,
    //                 centerPadding: '20px',
    //             }
    //         },
    //         {
    //             breakpoint: 576,
    //             settings: {
    //                 slidesToShow: 2,
    //                 slidesToScroll: 1,
    //                 centerPadding: '20px',
    //             }
    //         }
    //     ]
    // });
}

function initializeSlickSliders() {
    // Your Slick slider initialization code here
}

function addToCart(product) {
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

    swal(product.name, "is added to cart!", "success");
    $('.js-show-cart').attr('data-notify', cartItems.length);
}

$(".js-addcart-detail").on("click", function () {

    var cartItem = {
        id: product.id,
        mainImageUrl:product.mainImageUrl,
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

    swal(product.name, "is added to cart !", "success");

    $('.js-show-cart').attr('data-notify', cartItems.length);

    console.log(product);

    return false;
});

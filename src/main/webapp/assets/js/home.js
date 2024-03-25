var allProducts = [];

function getProduct() {
    // preventDefault();
    var xmlhttp;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        console.log(xmlhttp.status);
        if (xmlhttp.readyState === 4) {
            console.log(xmlhttp.status);
            if (xmlhttp.status === 200) {
                var response = JSON.parse(xmlhttp.responseText);
                console.log(response);
                allProducts.push(...response);
                let allProd = '';
                $('#best_seller_slick').slick({
                    infinite: false,
                    speed: 200,
                    slidesToShow: 3,
                    slidesToScroll: 1,
                    prevArrow: '<button class="arrow-slick2 prev-slick2"><i class="fa fa-angle-left" aria-hidden="true"></i></button>',
                    nextArrow: '<button class="arrow-slick2 next-slick2"><i class="fa fa-angle-right" aria-hidden="true"></i></button>',
                });
                for (var i = 0; i < allProducts.length; i++) {
                    let div = display(response[i]);
                    $('#best_seller_slick').slick('slickAdd', div);
                    allProd += div;
                }
            }
        }
    };

    var url = "front?page=homeProducts";
    console.log("page=homeProducts");
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}

function display(product) {
    var div = `
        <div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15 slick-slide" style="width: 290px;" tabindex="-1" data-slick-index="7" aria-hidden="true">
            <!-- Block2 -->
            <div class="block2">
                <div class="block2-pic hov-img0" style="width: 100%; height: 200px; overflow: hidden;">
                    <img src="${product.mainImageUrl}" alt="IMG-PRODUCT" style="width: 100%; height: auto; display: block; margin: 0 auto;">
                    <!-- Adjust height and width values as needed -->
                    <a  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1" onclick="showProductModal(${product.id})">
                        Quick View
                    </a>
                </div>

                <div class="block2-txt flex-w flex-t p-t-14">
                    <div class="block2-txt-child1 flex-col-l ">
                    <a onclick="showProductModal(${product.id})" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6" style="cursor:pointer;" >
                            ${product.name}
                        </a>

                        <span class="stext-105 cl3">
                            ${product.price}
                        </span>
                    </div>

                    <div class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                            <img class="icon-heart1 dis-block trans-04" src="assets/images/icons/icon-heart-01.png" alt="ICON">
                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="assets/images/icons/icon-heart-02.png" alt="ICON">
                        </a>
                    </div>
                </div>
            </div>
        </div>
    `;
    return div;
}


function showProductModal(id) {
    const product = allProducts.find(el => el?.id === id);
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
                                        <div class="item-slick3" data-thumb="images/product-detail-01.jpg">
                                            <div class="wrap-pic-w pos-relative">
                                                <img src="${product?.mainImageUrl}" alt="IMG-PRODUCT" class="product-image ">
                                                <div class="zoom-square-overlay"></div>
                                            </div>
                                        </div>
                                        
                                        <!-- Additional images -->
                                        <div class="item-slick3" data-thumb="images/product-detail-02.jpg">
                                            <div class="wrap-pic-w pos-relative">
                                                <img src="${product?.productImages[0].id.imageUrl}" alt="IMG-PRODUCT">
                                            </div>
                                        </div>
    
                                        <div class="item-slick3" data-thumb="images/product-detail-03.jpg">
                                            <div class="wrap-pic-w pos-relative">
                                                <img src="${product?.productImages[1].id.imageUrl}" alt="IMG-PRODUCT">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
    
                        
                        <div class="col-md-6 col-lg-5 p-b-30">
                        <div class="p-r-50 p-t-5 p-lr-0-lg">
                            
                               <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                                     ${product?.name}
                                 </h4>
                    
                            <span class="mtext-106 cl2">
                                     $${product?.price}
                                 </span>
                    
                                 <p class="stext-102 cl3 p-t-23">
                                    ${product?.description}
                                 </p>
                    
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
                </div></div>`
        },
        callbacks: {
            close: () => {


            },
            open: () => {



                // Add event listener for increment button
                $(".btn-num-product-up").on("click", function () {
                    var numProduct = parseInt($(this).closest('.wrap-num-product').find('.num-product').val());
                    $(this).closest('.wrap-num-product').find('.num-product').val(numProduct + 1);
                });

                // Add event listener for decrement button
                $(".btn-num-product-down").on("click", function () {
                    var numProduct = parseInt($(this).closest('.wrap-num-product').find('.num-product').val());
                    if (numProduct > 1) {
                        $(this).closest('.wrap-num-product').find('.num-product').val(numProduct - 1);
                    }
                });

                // Initialize Slick slider
                $('.slick3').slick({
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    arrows: true,
                    fade: true,
                    prevArrow: '<button class="arrow-slick2 prev-slick2"><i class="fa fa-angle-left" aria-hidden="true"></i></button>',
                    nextArrow: '<button class="arrow-slick2 next-slick2"><i class="fa fa-angle-right" aria-hidden="true"></i></button>',
                    asNavFor: '.wrap-slick3-dots'
                });

                // Initialize Slick slider dots
                $('.wrap-slick3-dots').slick({
                    slidesToShow: 3,
                    slidesToScroll: 1,
                    asNavFor: '.slick3',
                    centerMode: true,
                    focusOnSelect: true,
                    centerPadding: '40px',
                    responsive: [
                        {
                            breakpoint: 992,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 1,
                                centerPadding: '30px',
                            }
                        },
                        {
                            breakpoint: 768,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 1,
                                centerPadding: '20px',
                            }
                        },
                        {
                            breakpoint: 576,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 1,
                                centerPadding: '20px',
                            }
                        }
                    ]
                });




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
                    SendToCart();

                    return false;
                });



                // $(".js-addcart-detail").on("click", function () {

                //     var cartItem = {
                //         id: product.id,
                //         mainImageUrl: product.mainImageUrl,
                //         name: product.name,
                //         price: product.price,
                //         category: product.category,
                //         quantity: parseInt($(".num-product").val())
                //     };

                //     var cartItems = JSON.parse(localStorage.getItem("cartItems")) || [];

                //     var existingItemIndex = cartItems.findIndex(item => item.id === cartItem.id);

                //     if (existingItemIndex !== -1) {

                //         cartItems[existingItemIndex].quantity += cartItem.quantity;
                //     } else {

                //         cartItems.push(cartItem);
                //     }

                //     localStorage.setItem("cartItems", JSON.stringify(cartItems));
                //     console.log(cartItem);

                //     swal(product.name, "is added to cart !", "success");

                //     $('.js-show-cart').attr('data-notify', cartItems.length);
                //     console.log(product);
                //     return false;
                // });


            }
        }
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


window.onload = function () {
    getProduct();
}
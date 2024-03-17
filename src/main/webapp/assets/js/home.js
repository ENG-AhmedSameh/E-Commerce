
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
                for (var i = 0; i < 8; i++) {
                    let div = display(response[i].mainImageUrl, response[i].name, response[i].price);
                    document.getElementById("productList").innerHTML += div;


                }
            }

        }

    };

    var url = "front?page=homeProducts";

    console.log("page=homeProducts");
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}


 function display(productImageURl, productName, productPrice) {

     var div = '<div class="block2"> ' +
         '<div class="block2-pic hov-img0">' +
         '<img src="' + productImageURl + '" alt="IMG-PRODUCT">' + // Added missing + for concatenation

         ' <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">' +
         '     Quick View' +
         '</a>' +
         '</div>' +

         '<div class="block2-txt flex-w flex-t p-t-14">' +
         '<div class="block2-txt-child1 flex-col-l ">' +
         '   <a href="pages/product-detail.jsp" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">' +
         productName +
         ' </a>' +

         '<span class="stext-105 cl3">' +
         productPrice+"$" +
         '</span>' + // Removed the concatenation issue
         '</div>' +

         '<div class="block2-txt-child2 flex-r p-t-3">' +
         '<a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">' +
         '  <img class="icon-heart1 dis-block trans-04" src="assets/images/icons/icon-heart-01.png" alt="ICON">' +
         ' <img class="icon-heart2 dis-block trans-04 ab-t-l" src="assets/images/icons/icon-heart-02.png" alt="ICON">' +
         '  </a>' +
         '</div>' +
         ' </div> ' +
         '</div> ';

    return div;
 }







window.onload = function () {
    getProduct();
}
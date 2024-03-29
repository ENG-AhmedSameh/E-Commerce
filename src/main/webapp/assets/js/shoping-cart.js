window.onload = function () {
	loadProduct();

	const inputFields = document.querySelectorAll('.num-product');
	const minusButtons = document.querySelectorAll('.btn-num-product-down');
	const plusButtons = document.querySelectorAll('.btn-num-product-up');

	for (let i = 0; i < minusButtons.length; i++) {
		minusButtons[i].addEventListener('click', () => {
			if (inputFields[i].value > 1) {
				inputFields[i].value = parseInt(inputFields[i].value) - 1;
				enableUpdateCartButton(); 
				displayNote();
			}
		});
	}

	for (let i = 0; i < plusButtons.length; i++) {
		plusButtons[i].addEventListener('click', () => {
			inputFields[i].value = parseInt(inputFields[i].value) + 1;
			enableUpdateCartButton();
			displayNote();
		});
	}

	deletee();
};

function enableUpdateCartButton() {
    const updateCartButton = document.querySelector('.flex-c-m.stext-101.cl2.size-119.bg8.bor13.hov-btn3.p-lr-15.trans-04.pointer.m-tb-10');
    updateCartButton.removeAttribute('disabled');
}



// function displayNote() {
//     const noteSpan = document.getElementById('note');
//     noteSpan.textContent = 'Please press the "Update Cart" button to apply the changes.';
//     noteSpan.style.color = "red";
// }


var total = 0;
var allProd = [];

function loadHeader() {
	const header = `
	<tr class="table_head">
		<th class="column-1">Product</th>
		<th class="column-2"></th>
		<th class="column-3">Price</th>
		<th class="column-4">Quantity</th>
		<th class="column-5">Total</th>
	</tr>
	`;

	return header;
}

function loadProduct() {
	$('.table-shopping-cart').empty();
	const header = loadHeader();
	$('.table-shopping-cart').append(header);

	const cartItems = JSON.parse(sessionStorage.getItem("cartItems"));

	if (cartItems != null) {
		for (let i = 0; i < cartItems.length; i++) {
			const item = cartItems[i];
			allProd.push(item);
			const body = `
			<tr class="table_row">
				<td class="column-1">
					<div class="how-itemcart1">
						<img src="${item.mainImageUrl}" alt="IMG">
					</div>
				</td>
				<td class="column-2">${item.name}</td>
				<td class="column-3">${item.price.toLocaleString('en-US', {
				style: 'currency',
				currency: 'EGP'
			})}</td>
				<td class="column-4">
					<div class="wrap-num-product flex-w m-l-auto m-r-0">
				<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
							<i class="fs-16 zmdi zmdi-minus"></i>
						</div>

						<input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product1" value="${item.quantity}" disabled>

						<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
							<i class="fs-16 zmdi zmdi-plus"></i>
						</div>
					</div>
				</td>
				<td class="column-5">${(item.quantity * item.price).toLocaleString('en-US', {
				style: 'currency',
				currency: 'EGP'
			})}</td>
			</tr>
			`
			total += (item.quantity * item.price);
			$('.table-shopping-cart').append(body);
		}

		var formattedAmount = total.toLocaleString('en-US', {
			style: 'currency',
			currency: 'EGP'
		});

		const spanElement = $('.mtext-110');
		spanElement.text(formattedAmount);
	}
}


const updateCartButton = document.querySelector('.flex-c-m.stext-101.cl2.size-119.bg8.bor13.hov-btn3.p-lr-15.trans-04.pointer.m-tb-10');

// updateCartButton.addEventListener('click', function() {
//     updateCartAndRefresh();
// });


function updateCartAndRefresh(event) {
	event.preventDefault();
	console.log("update cart");
	let cartItems = JSON.parse(sessionStorage.getItem('cartItems')) || [];

	const tableRows = document.querySelectorAll('.table_row');

	tableRows.forEach(row => {
		const productName = row.querySelector('.column-2').textContent;
		const quantity = parseInt(row.querySelector('.num-product').value);

		const itemIndex = cartItems.findIndex(item => item.name === productName);
		if (itemIndex !== -1) {
			cartItems[itemIndex].quantity = quantity;
		}
	});

	sessionStorage.setItem('cartItems', JSON.stringify(cartItems));

	location.reload();

	SendToCart();
}


function deletee() {
    $('.how-itemcart1').on('click', function() {
		console.log("dellet");
        var indexToRemove = $('.how-itemcart1').index(this);
        
        var cartItems = JSON.parse(sessionStorage.getItem("cartItems"));
        
        if (cartItems !== null && indexToRemove >= 0 && indexToRemove < cartItems.length) {
            var id = cartItems[indexToRemove].id;
            cartItems.splice(indexToRemove, 1);
            
            sessionStorage.setItem("cartItems", JSON.stringify(cartItems));
            
            
            $('.js-show-cart').attr('data-notify', cartItems.length);
             removeItem(id);
			location.reload();
        }
    });
}


function removeItem(item_id){
       var postData = {
               id: item_id,

           };

           $.post('front?page=removeFromCart', postData)
               .done(function(response) {
                   console.log('Item sent to server successfully');
                   // Handle success response if needed
               })
               .fail(function(xhr, status, error) {
                   console.error('Error sending item to server:', error);
                   // Handle error if needed
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

function checkOutOrder(event) {
	event.preventDefault();
	console.log("checkout");
	var xmlhttp;

	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		console.error('XMLHttpRequest is not supported by this browser.');
		return;
	}
	const cartItems = JSON.parse(sessionStorage.getItem("cartItems")) || [];
	if(allProd.length == 0){

	    swal("Error", "Your cart is empty", "error");
	    return;
	}

	xmlhttp.open('POST', 'front?page=checkout', true);
	xmlhttp.setRequestHeader('Content-Type', 'text/plain');
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState === 4) { // The operation is complete
			if (xmlhttp.status === 200) {
				if (!xmlhttp.responseText.startsWith("Error")) {
					sessionStorage.removeItem('cartItems');
					swal("Order placed successfully!", "Your order will be shipped in two days", "success")
						.then(() => {
							window.location.reload();
						});

				} else {
					let errorMessage = xmlhttp.responseText.substring("Error: ".length);
					swal("Error", errorMessage, "error");
				}
			} else {
				console.error('An error occurred during the request: ' + xmlhttp.status);
			}
		}
	};

	xmlhttp.send(null);
}






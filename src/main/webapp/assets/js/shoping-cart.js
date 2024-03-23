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
				<td class="column-3">$ ${item.price.toLocaleString('en-US', {
				style: 'currency',
				currency: 'USD'
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
				<td class="column-5">$ ${(item.quantity * item.price).toLocaleString('en-US', {
				style: 'currency',
				currency: 'USD'
			})}</td>
			</tr>
			`
			total += (item.quantity * item.price);
			$('.table-shopping-cart').append(body);
		}

		var formattedAmount = total.toLocaleString('en-US', {
			style: 'currency',
			currency: 'USD'
		});

		const spanElement = $('.mtext-110');
		spanElement.text(formattedAmount);
	}
}


const updateCartButton = document.querySelector('.flex-c-m.stext-101.cl2.size-119.bg8.bor13.hov-btn3.p-lr-15.trans-04.pointer.m-tb-10');

updateCartButton.addEventListener('click', function() {
    updateCartAndRefresh();
});


function updateCartAndRefresh() {
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

}



function deletee() {
    $('.how-itemcart1').on('click', function() {
		console.log("dellet");
        var indexToRemove = $('.how-itemcart1').index(this);
        
        var cartItems = JSON.parse(sessionStorage.getItem("cartItems"));
        
        if (cartItems !== null && indexToRemove >= 0 && indexToRemove < cartItems.length) {
            cartItems.splice(indexToRemove, 1);
            
            sessionStorage.setItem("cartItems", JSON.stringify(cartItems));
            
            
            $('.js-show-cart').attr('data-notify', cartItems.length);
			location.reload();
        }
    });
}

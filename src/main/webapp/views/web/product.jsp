<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<section class="jumbotron text-center">
	<div class="container">
		<h1 class="jumbotron-heading">E-COMMERCE PRODUCT</h1>
		<p class="lead text-muted mb-0">Le Lorem Ipsum est simplement du
			faux texte employé dans la composition et la mise en page avant
			impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie
			depuis les années 1500...</p>
	</div>
</section>
<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/">Home</a></li>
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/category?categoryID=0">Category</a></li>
					<li class="breadcrumb-item active" aria-current="page">Product</li>
				</ol>
			</nav>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<!-- Image -->
		<div class="col-12 col-lg-6">
			<div class="card bg-light mb-3">
				<div class="card-body">
					<a href="" data-toggle="modal" data-target="#productModal"> <img
						class="img-fluid" src="${productByID.productImage}">
						<p class="text-center">Zoom</p>
					</a>
				</div>
			</div>
		</div>

		<!-- Add to cart -->
		<div class="col-12 col-lg-6 add_to_cart_block">
			<div class="card bg-light mb-3">
				<div class="card-body">
					<div class="d-flex justify-content-center">
						<h2 class="text-center">${productByID.productID} v ${productByID.productName}</h2>
					</div>
					<c:choose>
						<c:when
							test="${productByID.productStatus == 1 && productByID.productAmount >0}">
							<p class="price">${productByID.productPrice}vnd</p>
							<p class="price_discounted mb-4">${productByID.productPrice * 1.2}
								vnd</p>
						</c:when>
						<c:when
							test="${productByID.productStatus == 0 && productByID.productAmount >0}">
							<p class="price mb-4">Không còn kinh doanh</p>
						</c:when>
						<c:otherwise>
							<p class="price m-4">Hết hàng</p>
						</c:otherwise>
					</c:choose>
					<form method="get" action="cart.html">
						<div class="form-group">
							<label>Số lượng: ${productByID.productAmount}</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button type="button"
										class="quantity-left-minus btn btn-danger btn-number"
										data-type="minus" data-field=""
										onclick="func_SubtractQuantity()">
										<i class="fa fa-minus"></i>
									</button>
								</div>
								<input type="text" class="form-control" id="quantity"
									name="quantity" min="1" max="50" value="${productAmountInCart}">
								<div class="input-group-append">
									<button type="button"
										class="quantity-right-plus btn btn-success btn-number"
										data-type="plus" data-field="" onclick="func_AddQuantity()">
										<i class="fa fa-plus"></i>
									</button>
								</div>
							</div>
						</div>
						<button id="updateProductQuantityBtn"
							onclick="func_changeProductQuantityInCart()" type="button"
							class="${productByID.productStatus != 0 ? " btn btn-success
							btn-lg btn-block text-uppercase" : "btn btn-success btn-lg
							btn-blocktext-uppercasedisabled" } ">
							<i class="fa fa-shopping-cart"></i> Add To Cart
						</button>
					</form>
					<div class="product_rassurance">
						<ul class="list-inline">
							<li class="list-inline-item"><i class="fa fa-truck fa-2x"></i><br />Fast
								delivery</li>
							<li class="list-inline-item"><i
								class="fa fa-credit-card fa-2x"></i><br />Secure payment</li>
							<li class="list-inline-item"><i class="fa fa-phone fa-2x"></i><br />+33
								1 22 54 65 60</li>
						</ul>
					</div>
					<div class="reviews_product p-3 mb-2 ">
						3 reviews <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> (4/5) <a class="pull-right"
							href="#reviews">View all reviews</a>
					</div>
					<div class="datasheet p-3 mb-2 bg-info text-white">
						<a href="" class="text-white"><i class="fa fa-file-text"></i>
							Download DataSheet</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<!-- Description -->
		<div class="col-12">
			<div class="card border-light mb-3">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-align-justify"></i> Description
				</div>
				<div class="card-body">
					<p class="card-text">${productByID.productDescription}</p>
				</div>
			</div>
		</div>

		<!-- Reviews -->
		<div class="col-12" id="reviews">
			<div class="card border-light mb-3">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-comment"></i> Reviews
				</div>
				<div class="card-body">
					<div class="review">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						<meta itemprop="datePublished" content="01-01-2016">
						January 01, 2018 <span class="fa fa-star"></span> <span
							class="fa fa-star"></span> <span class="fa fa-star"></span> <span
							class="fa fa-star"></span> <span class="fa fa-star"></span> by
						Paul Smith
						<p class="blockquote">
						<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Integer posuere erat a ante.</p>
						</p>
						<hr>
					</div>
					<div class="review">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						<meta itemprop="datePublished" content="01-01-2016">
						January 01, 2018 <span class="fa fa-star" aria-hidden="true"></span>
						<span class="fa fa-star" aria-hidden="true"></span> <span
							class="fa fa-star" aria-hidden="true"></span> <span
							class="fa fa-star" aria-hidden="true"></span> <span
							class="fa fa-star" aria-hidden="true"></span> by Paul Smith
						<p class="blockquote">
						<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Integer posuere erat a ante.</p>
						</p>
						<hr>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal image -->
<div class="modal fade" id="productModal" tabindex="-1" role="dialog"
	aria-labelledby="productModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="productModalLabel">${productByID.productName}
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<img class="img-fluid" src="${productByID.productImage} " />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
	let maxQuantity = parseInt(document.getElementById("quantity").max, 10);
	let minQuantity = parseInt(document.getElementById("quantity").min, 10);

	const element = document.getElementById("quantity");
	const func_SubtractQuantity = function() {
		let quantityString = quantity.value;
		quantityInt = parseInt(quantityString, 10) - 1;

		if (quantityInt < minQuantity) {
			alert("Số lượng không hợp lệ");
		} else {
			element.value = quantityInt.toString();
		}
	}

	const func_AddQuantity = function() {
		let quantityString = quantity.value;
		quantityInt = parseInt(quantityString, 10) + 1;

		if (quantityInt > maxQuantity) {
			alert("Vượt mức cho phép");
		} else {
			element.value = quantityInt.toString();
		}
	}

	const func_changeProductQuantityInCart = function() {
		var changedProductQuantityInCart = document.getElementById("quantity").value;
		var pID = "${productByID.productID}";
		console.log("Hello");
		var root = "${pageContext.request.contextPath}"
		window.location.href = root + "/cart?productID=" + pID
				+ "&action=updateToCart&productAmount="
				+ changedProductQuantityInCart;
	}
</script>
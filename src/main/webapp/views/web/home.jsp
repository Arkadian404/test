<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<section class="jumbotron text-center">
	<div class="container">
		<h1 class="jumbotron-heading">E-COMMERCE BOOTSTRAP THEME</h1>
		<p class="lead text-muted mb-0">Simple theme for e-commerce buid
			with Bootstrap 4.0.0. Pages available : home, category, product, cart
			& contact</p>
	</div>
</section>
<div class="container">
	<div class="row">
		<div class="col w-100">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block w-100"
							src="http://allonboard.no/en/wp-content/uploads/2014/02/Postcard411-855x365.jpg"
							alt="First slide">
					</div>
					<c:forEach items="${list}" var="list">
						<div class="carousel-item">
							<img class="d-block w-100" src="${list.productImage}"
								alt="Second slide"
								style="max-height: 365px; max-width: 855px; object-fit: contain;">
						</div>
					</c:forEach>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="col-sm col-md-3 w-100">
			<div class="card">
				<div class="card-header bg-success text-white text-uppercase">
					<i class="fa fa-heart"></i> Top product
				</div>
				<img class="img-fluid border-0" src="${top.productImage }"
					alt="${top.productDescription }" style="height: 40vh;">
				<div class="card-body">
					<h4 class="card-title text-center">
						<a href="product?productID=${top.productID}" title="View Product">${top.productName}</a>
					</h4>
					<h5 class="card-subtitle text-center mb-2 text-muted">${top.productPrice}
						vnd</h5>
					<div class="row">
						<div class="col">
							<a href="product?productID=${top.productID}"
								class="btn btn-success btn-block">View</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="container mt-3">
	<div class="row">
		<div class="col-sm">
			<div class="card">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-star"></i> Last products
				</div>
				<div class="card-body">
					<div class="row">
						<c:forEach items="${list}" var="product">
							<div class="col-lg d-flex align-items-stretch">
								<div class="card">
									<img class="card-img-top" src="${product.productImage}"
										alt="${product.productName}" style="max-height: 50vh;">
									<div class="card-body d-flex flex-column">
										<h4 class="card-title">
											<a class="text-break" href="product?productID=${product.productID}"
												title="View Product"
												>${product.productName }
												</a>
										</h4>
										<h5 class="card-subtitle mb-2 text-muted">${product.productPrice}</h5>
										<p class="card-text mb-4">${product.productDescription}</p>
										<a href="cart.html"
											class="btn mt-auto align-self-start btn-success btn-block">Add to
											cart</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container mt-3 mb-4">
	<div class="row">
		<div class="col-sm">
			<div class="card">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-trophy"></i> Best products
				</div>
				<div class="card-body">
					<div class="row">
						<c:forEach items="${top4product}" var="p">
							<div class="col-sm">
								<div class="card">
									<img class="card-img-top" src="${p.productImage }"
										alt="${p.productName }" style="height: 40vh;">
									<div class="card-body">
										<h5 class="card-title truncate-multi truncate-title">
											<a class="d-block text-truncate"
												href="product?productID=${p.productID}" title="View Product">${p.productName }</a>
										</h5>
										<h6 class="card-subtitle mb-2 text-muted">${p.productPrice}vnd</h6>
										<p class="card-text text-truncate truncate-multi">${p.productDescription}</p>
										<div class="row">
											<div class="col-12">
												<a href="cart.html"
													class="btn mt-auto align-self-start btn-success btn-block">Add to
													cart</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
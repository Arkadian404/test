<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<section class="jumbotron text-center">
	<div class="container">
		<h1 class="jumbotron-heading">E-COMMERCE CATEGORY</h1>
		<p class="lead text-muted mb-0">Le Lorem Ipsum est simplement du
			faux texte employé dans la composition et la mise en page avant
			impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie
			depuis les années 1500, quand un peintre anonyme assembla ensemble
			des morceaux de texte pour réaliser un livre spécimen de polices de
			texte...</p>
	</div>
</section>
<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="home">Home</a></li>
					<li class="breadcrumb-item"><a href="category?categoryID=0">Category</a></li>
					<li class="breadcrumb-item active" aria-current="page">Sub-category</li>
				</ol>
			</nav>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-12 col-sm-3">
			<div class="card bg-light mb-3">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-list"></i> Categories
				</div>
				<ul class="list-group category_block">
					<c:forEach items="${listCate}" var="list">
						<li class="list-group-item ${tagActive==list.categoryID ? "active" : ""}"><a
							href="category?categoryID=${list.categoryID}">${list.categoryName}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="card bg-light mb-3">
				<div class="card-header bg-success text-white text-uppercase">Last
					product</div>
				<div class="card-body">
					<img class="img-fluid" src="${top.productImage}" />
					<h5 class="card-title">${top.productName}</h5>
					<p class="card-text">${top.productDescription}</p>
					<p class="bloc_left_price">${top.productPrice }$</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="row gy-3">
				<c:forEach items="${list}" var="l">
					<div class="col-12 col-md-6 col-lg-4">
						<div class="card h-100">
							<img class="card-img-top p-4" src="${l.productImage}"
								alt="${l.productDescription }">
							<div class="card-body">
								<h4 class="card-title">
									<a class="card-title d-block truncate-multi truncate-title" href="product?prodID=${l.productID}" title="View Product">${l.productName }</a>
								</h4>
								<h5 class="card-subtitle mb-4 text-muted ">${l.productPrice}$</h5>
								<p class="card-text truncate-multi">${l.productDescription }</p>
								<div class="row">
									<div class="col-12">
										<a href="#" class="btn btn-sm h-100 btn-success btn-block">Add to cart</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-12">
					<nav aria-label="...">
						<ul class="pagination">
							<c:if test="${index >1}">
								<li class="page-item"><a class="page-link"
									href="search?index=${index-1}&txtSearch=${txtSearch}">Previous</a></li>
							</c:if>
							<c:forEach begin="1" end="${endPage}" var="i">
								<li class="page-item ${index == i ? "active" :""}"><a class="page-link" href="search?index=${i}&txtSearch=${txtSearch}">${i}</a></li>
							</c:forEach>
							<c:if test="${index < endPage }">
								<li class="page-item"><a class="page-link"
									href="search?index=${index+1}&txtSearch=${txtSearch}">Next</a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>

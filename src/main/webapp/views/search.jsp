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
						<c:if test="${list.status != 0 }">
							<li class="list-group-item ${tagActive==list.categoryID ? "active" : ""}"><a
								href="category?categoryID=${list.categoryID}">${list.categoryName}</a></li>
						</c:if>
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
					<c:choose>
						<c:when test="${top.productStatus == 1 && top.productAmount >0}">
							<p class="bloc_left_price">${top.productPrice }vnd</p>
						</c:when>
						<c:when test="${top.productStatus == 0 && top.productAmount >0}">
							<p class="bloc_left_price">Không còn kinh doanh</p>
						</c:when>
						<c:otherwise>
							<p class="bloc_left_price">Hết hàng</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="row gy-3">
				<c:set var="total" value="${fn:length(list)}" />
				<c:choose>
					<c:when test="${total==0}">
						<h2 class="m-auto">Không có sản phẩm nào cả!</h2>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="l">
							<div class="col-lg d-flex align-items-stretch">
								<div class="card">
									<img class="card-img-top mb-4" src="${l.productImage}"
										alt="${l.productDescription }" style="max-height: 30vh;">
									<div class="card-body d-flex flex-column">
										<h4 class="card-title">
											<a class="text-break" href="product?productID=${l.productID}"
												title="View Product">${l.productName }</a>
										</h4>
										<c:choose>
											<c:when test="${l.productStatus == 1 && l.productAmount >0}">
												<h5 class="card-subtitle mb-4 text-muted ">${l.productPrice}
													vnd</h5>
												<p class="card-text">${l.productDescription }</p>
												<a href="#"
													class="btn mt-auto align-self-start btn-success btn-block">Add
													to cart</a>
											</c:when>
											<c:when test="${l.productStatus == 0 && l.productAmount >0}">
												<h5 class="card-subtitle mb-4 text-muted ">Không còn
													kinh doanh</h5>
												<p class="card-text">${l.productDescription }</p>
												<a href="#"
													class="btn mt-auto align-self-start btn-success btn-block disabled"
													role="button" aria-disabled="true">Add to cart</a>
											</c:when>
											<c:otherwise>
												<h5 class="card-subtitle mb-4 text-muted ">Hết hàng</h5>
												<p class="card-text">${l.productDescription }</p>
												<a href="#"
													class="btn mt-auto align-self-start btn-success btn-block disabled"
													role="button" aria-disabled="true">Add to cart</a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div class="col-12">
					<nav aria-label="...">
						<ul class="pagination">
							<c:if test="${index >1}">
								<li class="page-item"><a class="page-link"
									href="search?index=${index-1}&txtSearch=${txtSearch}">Previous</a></li>
							</c:if>
							<c:forEach begin="1" end="${endPage}" var="i">
								<li class="page-item ${index == i ? "active" :""}"><a
									class="page-link"
									href="search?index=${i}&txtSearch=${txtSearch}">${i}</a></li>
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

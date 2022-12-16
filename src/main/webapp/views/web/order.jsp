<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<h2 class="font-monospace font-weight-bold text-center">Hóa đơn điện tử</h2>
<div class="container card shadow font-monospace my-4 p-4">
<c:set var = "finalPrice" value = "0"/>
	<c:forEach items="${cart }" var="cartItem" varStatus="Stt">
		<c:set var = "productQuantityPrice" value = "${cartItem.product.productPrice*cartItem.amount}"/>
		<c:set var = "finalPrice" value = "${finalPrice + productQuantityPrice }"/>
	    <div class="p-0 m-0">
	        <div class="d-flex">
	            <p class="col-6">Sản phẩm: ${cartItem.product.productName } <small>Mã danh mục:</small>${cartItem.product.categoryID}</p>
	            <p class="col-4">Giá tiền: ${cartItem.product.productPrice }</p>
	        </div>
	        <div class="d-flex">
	            <p class="col-6">Số lượng: ${cartItem.amount}</p>
	            <p class="col-4">Tổng: <c:out value = "${finalPrice}"/></p>
	        </div>
	        <hr />
	    </div>
	    <br>
	</c:forEach>
	<h2 class="font-weight-bold m-0 p-0"
		style="font-family: Courier New, Courier, monospace;">
		<b class="text-danger">Tổng tiền tạm tính: <c:out
				value="${finalPrice}" /></b>
	</h2>
	<%
       	LocalDateTime currDate = java.time.LocalDateTime.now();
    	out.print("<small class='font-monospace text-sm-left'>Date: " + currDate +"</small>");
    %>

</div>
  
<section class="py-5">
	<div class="container-fluid">
		<div class="myModal">
			<div class="myModalContent">
				<div class="myModalHeader">
					<h2 class="toast-header">
						Enter your location
					</h2>
				</div>
				<div class="myModalBody">
					<c:url value="/web/order" var="checkoutControllerUrl"></c:url>
					<form id="myModalForm" method="post" action="${checkoutControllerUrl }">
						<div class="mb-3">
							<label class="form-label">Phone number:</label> 
							<input type="text" name="phoneNumber" placeholder="Nhập số điện thoại liên lạc:" class="form-control"/>
						</div>

						<div class="mb-5">
							<label class="form-label">Deliver to:</label> 
							<input type="text" name="address" placeholder="Nhập địa chỉ nhận hàng:" class="form-control"/>
						</div>
						
						<a class="btn btn-outline-primary btn-sm" href="<%=request.getContextPath() %>/home">Back</a>
						<button type="submit" id="myModalConfirmedBtn"
							class="btn btn-outline-danger btn-sm">Confirmed</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<h3>Cart Waiting</h3>
<table class="table table-warning">
	<tr class="row">
		<th>CartId</th>
		<th>UserId</th>
		<th>Buy Date</th>
		<th>Status</th>
		<th>Address</th>
		<th>PhoneNumber</th>
	</tr>
	<c:forEach items="${waitingCartsOfUser }" var="cart" varStatus="cartSTT">
		<tr class="row">
			<td>Cart${cart.cartID }</td>
			<td>User${cart.accountID }</td>
			<td>${cart.purchaseDate }</td>
			<td>${cart.status }</td>
			<td>${cart.address }</td>
			<td>${cart.phone }</td>
		</tr>
	</c:forEach>
</table>
<br>
<br>

<h3>Cart Accepted</h3>
<table class="table table-success">
	<tr class="row">
		<th>CartId</th>
		<th>UserId</th>
		<th>Buy Date</th>
		<th>Status</th>
		<th>Address</th>
		<th>PhoneNumber</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${validCartsOfUser }" var="cart" varStatus="cartSTT">
		<tr class="row">
			<td>Cart${cart.cartID }</td>
			<td>User${cart.accountID }</td>
			<td>${cart.purchaseDate }</td>
			<td>${cart.status }</td>
			<td>${cart.address }</td>
			<td>${cart.phone }</td>
			<td>
				<a class="btn btn-sm btn-danger" href="<%=request.getContextPath() %>/order/checkout?cartID=${cart.cartID}&purchaseDate=${cart.purchaseDate}">Checkout</a>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
<br>

<!-- receipt co the xem chi tiet duoc -->
<h3>Receipts</h3>
<table class="table table-danger">
	<tr class="row">
		<th>receiptId</th>
		<th>releaseDate</th>
		<th>cartId</th>		
	</tr>
	<c:forEach items="${receiptsOfUser}" var="receipt" varStatus="receiptSTT">
		<tr class="row">
			<td>Receipt${receipt.receiptID }</td>
			<td>${receipt.receiptDate }</td>
			<td>${receipt.cartID }</td>
		</tr>
	</c:forEach>
</table>
<br>
<br>
<a class="btn btn-primary" href="<%=request.getContextPath() %>/home">Back</a>
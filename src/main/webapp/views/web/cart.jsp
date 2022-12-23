<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="MVC.Models.CartDetailModel" %>
<%@ page import="java.util.List" %>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">E-COMMERCE CART</h1>
     </div>
</section>

<c:if test="${cart == null }">
	<h1 class="text-center mb-4">Bạn chưa mua gì cả!!!</h1>
</c:if>
<c:if test="${cart.size() == 0 }">
	<h1>Bạn chưa mua gì cả </h1>
</c:if>
<c:if test="${cart != null}">
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Sản phẩm</th>
                            <th scope="col">Tồn kho</th>
                            <th scope="col" class="text-center">Số lượng</th>
                            <th scope="col" class="text-right">Giá tiền</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%! float totalPrice; %>
	                    	<%               
	                    		totalPrice = 0;
	                    		List<CartDetailModel> cart = (List<CartDetailModel>)session.getAttribute("cart");
	                    		for(CartDetailModel cartItem: cart) {                    			
	                    			int amount = cartItem.getAmount();
	                    			float productPricePerUnit = cartItem.getProduct().getProductPrice();
	                    			totalPrice += amount * productPricePerUnit;
	                    			out.println("<span> " + amount + "c</span>");
	                    			out.println("<span> " +productPricePerUnit + "c</span><br>");
	                    		}
	                    	%>
                    
                    	<c:forEach items="${cart}" var="cartItem">
                        <tr>
                            <td><img src="${cartItem.product.productImage }" style="height:100px;width:100px;object-fit:cover;" /> </td>
                            <td>${cartItem.product.productName}</td>
                            <td>Số lượng hàng trong kho</td>
                            <td><input class="form-control" type="text" value="${cartItem.amount}" /></td>
                            <td class="text-right">${cartItem.product.productPrice} vnd</td>
                            <td class="text-right"><a class="btn btn-sm btn-danger" href="<%=request.getContextPath() %>/cart?action=remove&productID=${cartItem.product.productID}"><i class="fa fa-trash"></i> </a> </td>
                        </tr>
                        </c:forEach> 
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Sub-Total</td>
                            <td class="text-right"><%=totalPrice %> vnd</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Shipping</td>
                            <td class="text-right">6,90 vnd</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong><%=totalPrice+6.90 %> vnd</strong></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
					<a class="btn btn-block btn-light" href="<%=request.getContextPath() %>/home">Continue Shopping</a>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <c:if test="${isLogin != null }">
		               	<c:set var = "isLogin" value = "false"/>
		               	<c:set var = "username" value = '${session.getAttribute("username")}'/>                    
	               	</c:if>
	               	<c:if test="${username != null }">
	                    <c:set var = "isLogin" value = "true"/>
	               	</c:if>
	               	<c:if test="${username == null }">
	                    <c:set var = "isLogin" value = "false"/>
	               	</c:if>
	               	<c:if test="${isLogin == true }">
	                    <a class="btn btn-block btn-success btn-lg" href="<%=request.getContextPath() %>/order">Order</a>
	               	</c:if>
	               	<c:if test="${isLogin == false }">
	                    <a class="btn btn-block btn-success btn-lg" href="<%=request.getContextPath() %>/views/login.jsp">Order</a>
	               	</c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>
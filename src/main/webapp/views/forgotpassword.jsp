<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<c:url value="/common/forgotPassword" var="fpController"></c:url>
<div class="container w-50 mx-auto mt-5 mb-5">
	<form action="${fpController}" method="post"
		class="border border-primary p-3 mb-5 shadow-lg bg-white rounded">
		<div class="${check == false ? " alert
			alert-danger" : "d-none"}" role="alert">
			<p class="text-center">${thongBao}</p>
		</div>
		<div class="text-center mb-3 bg-black">
			<p class="fw-bold fs-2">Đổi mật khẩu tài khoản</p>
		</div>
		<div class="form-group">
			<label class="ml-2" for="username">Tên tài khoản</label> <input
				type="text" name="username" class="form-control input-lg"
				placeholder="Enter username" />
		</div>

		<div class="form-group">
			<label class="ml-2" for="email">Email</label> <input type="text"
				name="email" class="form-control input-lg" placeholder="Email" />
		</div>

		<div class="form-group">
			<label class="ml-2" for="password">Mật khẩu</label> <input
				type="password" name="password" class="form-control input-lg"
				placeholder="Password" />
		</div>

		<div class="form-group">
			<label for="confirmedPassword">Nhập lại mật khẩu</label> <input
				type="password" name="confirmedPassword" class="form-control"
				id="confirmedPassword" placeholder="Password">
		</div>

		<div class="d-flex flex-row justify-content-between">
			<a href="${pageContext.request.contextPath}/common/register">Đăng
				ký tài khoản</a>
		</div>
		<button type="submit" class="btn btn-primary btn-block mt-3">Reset</button>
	</form>
</div>

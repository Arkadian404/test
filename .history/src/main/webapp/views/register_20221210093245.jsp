<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/common/taglib.jsp"%>>
<c:url value="/common/register" var="registerController"></c:url>
<section class="container w-40 mx-auto mt-5 mb-5">
  <div
    class="border border-primary p-3 mb-5 shadow-lg p-3 mb-5 bg-white rounded"
  >
    <form action="${registerController}" method="post">
      <div class="d-flex align-items-center justify-content-center mb-3">
        Đăng ký vào hệ thống
      </div>
      <div class="form-group">
        <label for="username">Tên tài khoản</label>
        <input
          type="text"
          name="username"
          class="form-control"
          id="username"
          placeholder="Enter username"
        />
      </div>
      <div class="form-group">
        <label for="username">Tên email</label>
        <input
          type="text"
          name="email"
          class="form-control"
          id="email"
          placeholder="Enter email"
        />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          name="password"
          class="form-control"
          id="password"
          placeholder="Password"
        />
      </div>
      <div class="form-group">
        <label for="confirmedPassword">Password</label>
        <input
          type="password"
          name="confirmedPassword"
          class="form-control"
          id="confirmedPassword"
          placeholder="Password"
        />
      </div>
      <button type="submit" class="btn btn-primary btn-block mb-3">
        Tạo tài khoản
      </button>
      <div class="text-center">
        Nếu bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
      </div>
    </form>
  </div>
</section>

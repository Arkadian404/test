<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/admin/category/add" var="add"></c:url>
	<form role="form" action="${add }" method="post"
		enctype="multipart/form-data">
		<div class="form-group">
			<label>Tên danh mục:</label> <input class="form-control"
				placeholder="please enter category Name" name="categoryName" />
		</div>
		<div class="form-group">
			<label>Trang thai</label> <input class="form-control" name="status"/>
		</div>
		<button type="submit" class="btn btn-default">Thêm</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>
</body>
</html>
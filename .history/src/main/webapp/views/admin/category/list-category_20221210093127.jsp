<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="container-xl">
	<div class="table-responsive">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>Manage <b>Employees</b></h2>
					</div>
					<div class="col-sm-6">
						<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
						<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Ten danh muc</th>
						<th>Trang thai</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${categoryList}" var="list">
					<tr>
						<td>${list.categoryID }</td>
						<td>${list.categoryName }</td>
						<td>${list.status }</td>
						<td>
							<a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
							<a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
							<input type="hidden" name="id" id="id" value="${list.categoryID}">
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix">
				<div class="hint-text">
					Showing <b>${index*3}</b> out of <b>${endPage*3}</b> entries
				</div>
				<ul class="pagination">
					<c:if test="${index > 1}">
						<li class="page-item disabled"><a
							href="${pageContext.request.contextPath}/admin/category/list?index=${index-1}">Previous</a></li>
					</c:if>
					<c:forEach begin="1" end="${endPage}" var="i">
						<li class="page-item ${index ==i ?"active" : "" }"><a
							href="${pageContext.request.contextPath}/admin/category/list?index=${i}"
							class="page-link">${i}</a></li>
					</c:forEach>
					<c:if test="${index < endPage }">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/admin/category/list?index=${index+1}"
							class="page-link">Next</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Add Modal HTML -->
<div id="addCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=create" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Add Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Ten danh muc</label> <input type="text" name="categoryName"
							id="categoryName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Trang thai</label> <input type="text" name="status"
							id="status" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal"
						value="Cancel"> <input type="submit"
						class="btn btn-success" value="Add">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Edit Modal HTML -->
<div id="editCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=update" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Edit Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Ten danh muc</label> <input type="text" name="categoryName"
							id="categoryName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Trang thai</label> <input type="text" name="status"
							id="status" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal"
						value="Cancel"> <input type="submit" class="btn btn-info"
						value="Save"> <input type="hidden" name="id" id="id">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteCategoryModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/admin/category/list?action=delete" method="post">
				<div class="modal-header">						
					<h4 class="modal-title">Delete Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<p>Are you sure you want to delete these Records?</p>
					<p class="text-warning">
						<small>This action cannot be undone.</small>
					</p>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal"
						value="Cancel"> <input type="submit"
						class="btn btn-danger" value="Delete"> <input
						type="hidden" name="id" id="id">
				</div>
			</form>
		</div>
	</div>
</div>
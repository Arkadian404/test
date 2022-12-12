<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="container-xxl">
	<div class="table-responsive">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<div class="row">
							<a href="<%=request.getContextPath()%>/admin/home"
								style="margin-right: 20px;"> <svg
									xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									fill="currentColor" class="bi bi-arrow-return-left"
									viewBox="0 0 16 16">
									<path fill-rule="evenodd"
										d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z" /></svg>
							</a>

							<h2>
								Quản lý <b>Danh mục</b>
							</h2>
						</div>
					</div>
					<div class="col-sm-4">
						<a href="#addCategoryModal" class="btn btn-success"
							data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm
								danh mục</span></a>
					</div>
					<div class="col-sm-2">
						<form class="form-inline" action="${pageContext.request.contextPath}/admin/category/search?action=category&index=1" method="post">
							<div class="input-group input-group-sm">
								<input type="text" value="${txtSearch}" name="txtSearch" class="form-control" placeholder="Search...">
								<div class="input-group-append">
									<button type="submit" class="btn btn-secondary btn-number">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
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
							<td><a href="#editCategoryModal" class="edit"
								data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
								href="#deleteCategoryModal" class="delete" data-toggle="modal"><i
									class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
								<input type="hidden" name="id" id="id"
								value="${list.categoryID}"></td>
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
			<form
				action="${pageContext.request.contextPath}/admin/category/list?action=create"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Thêm danh mục</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
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
			<form
				action="${pageContext.request.contextPath}/admin/category/list?action=update"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Sửa danh mục</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
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
			<form
				action="${pageContext.request.contextPath}/admin/category/list?action=delete"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Xóa danh mục</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
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


<script>
	$(document)
			.ready(
					function() {
						// Activate tooltip
						$('[data-toggle="tooltip"]').tooltip();

						$('table .delete').on('click', function() {
							var id = $(this).parent().find("#id").val();
							$('#deleteCategoryModal #id').val(id);
						});

						$('table .edit')
								.on(
										'click',
										function() {
											var id = $(this).parent().find(
													"#id").val();
											$
													.ajax({
														type : 'GET',
														url : '${pageContext.request.contextPath}/admin/category/list',
														data : {
															action : 'find',
															id : id
														},
														dataType : 'json',
														contentType : 'application/json',
														success : function(
																result) {
															$(
																	'#editCategoryModal #id')
																	.val(
																			result.categoryID);
															$(
																	'#editCategoryModal #categoryName')
																	.val(
																			result.categoryName);
															$(
																	'#editCategoryModal #status')
																	.val(
																			result.status);
														}
													});
										});
					});
</script>
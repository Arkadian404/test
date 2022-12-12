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
							<a href="<%=request.getContextPath()%>/admin/staff/list"
								style="margin-right: 20px;"> <svg
									xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									fill="currentColor" class="bi bi-arrow-return-left"
									viewBox="0 0 16 16">
									<path fill-rule="evenodd"
										d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z" />
										</svg>
							</a>
							<h2>
								Quản lý <b>Nhân Viên</b>
							</h2>
						</div>
					</div>
					<div class="col-sm-4">
					</div>
					<div class="col-sm-2">
						<form class="form-inline"
							action="${pageContext.request.contextPath}/admin/staff/search?action=staff&index=1"
							method="post">
							<div class="input-group input-group-sm">
								<input type="text" value="${txtSearch}" name="txtSearch"
									class="form-control" placeholder="Search...">
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
						<th>Mã nhân viên</th>
						<th>Tên nhân viên</th>
						<th>Ngày sinh</th>
						<th>Giới tính</th>
						<th>Số điện thoại</th>
						<th>Mã lương</th>
						<th>Mã Tài Khoản</th>
						<th>Trạng thái</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${staffList}" var="list">
						<tr>
							<td>${list.staffID}</td>
							<td>${list.staffName}</td>
							<td>${list.DOB}</td>
							<td>${list.sex}</td>
							<td>${list.staffPhone}</td>
							<td>${list.salaryID}</td>
							<td>${list.accountID}</td>
							<td>${list.status}</td>
							<td><a href="#editStaffModal" class="edit"
								data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
								href="#deleteStaffModal" class="delete" data-toggle="modal"><i
									class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
								<input type="hidden" name="id" id="id" value="${list.staffID}">
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
							href="${pageContext.request.contextPath}/admin/staff/search?action=staff&index=${index-1}&txtSearch=${txtSearch}">Previous</a></li>
					</c:if>
					<c:forEach begin="1" end="${endPage}" var="i">
						<li class="page-item ${index ==i ?"active" : "" }"><a
							href="${pageContext.request.contextPath}/admin/staff/search?action=staff&index=${i}&txtSearch=${txtSearch}"
							class="page-link">${i}</a></li>
					</c:forEach>
					<c:if test="${index < endPage }">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/admin/staff/search?action=staff&index=${index+1}&txtSearch=${txtSearch}"
							class="page-link">Next</a></li>
					</c:if>
					<!---<c:if test="${index >1}">
								<li class="page-item disabled"><a class="page-link"
									href="category?cateID=${tagActive}&index=${tag-1}">Previous</a></li>
							</c:if>
							<c:forEach begin="1" end="${endPage}" var="i">
								<li class="page-item"><a class="page-link ${tag==i ? "
									active" : "" }" href="category?cateID=${tagActive}&index=${i}">${i}</a></li>
							</c:forEach>
							<c:if test="${tag < endPage }">
								<li class="page-item"><a class="page-link"
									href="category?cateID=${tagActive}&index=${tag + 1 }">Next</a></li>
							</c:if> -->
				</ul>
			</div>
		</div>
	</div>
</div>
!-- Add Modal HTML -->
<div id="addStaffModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form
				action="${pageContext.request.contextPath}/admin/staff/list?action=create"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Thêm nhân viên</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Họ tên</label> <input type="hidden" name="staffName"
							id="staffName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Ngày sinh</label> <input type="text" name="DOB" id="DOB"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label>Giới tính</label> <input type="text" name="sex" id="sex"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label>Số điện thoại</label> <input type="text" name="staffPhone"
							id="staffPhone" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Mã lương</label> <input type="text" name="salaryID"
							id="salaryID" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Mã tài khoản</label> <input type="text" name="accountID"
							id="accountID" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Trạng thái</label> <input type="text" name="status"
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
<div id="editStaffModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form
				action="${pageContext.request.contextPath}/admin/staff/list?action=update"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Sửa nhân viên</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Họ tên</label> <input type="text" name="staffName"
							id="staffName" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Ngày sinh</label> <input type="text" name="DOB" id="DOB"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label>Giới tính</label> <input type="text" name="sex" id="sex"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label>Số điện thoại</label> <input type="text" name="staffPhone"
							id="staffPhone" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Mã lương</label> <input type="text" name="salaryID"
							id="salaryID" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Mã tài khoản</label> <input type="text" name="accountID"
							id="accountID" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Mã trạng thái</label> <input type="text" name="status"
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
<div id="deleteStaffModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form
				action="${pageContext.request.contextPath}/admin/staff/list?action=delete"
				method="post">
				<div class="modal-header">
					<h4 class="modal-title">Xóa nhân viên</h4>
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
						class="btn btn-danger" value="Delete"> <input type="hidden"
						name="id" id="id">
				</div>
			</form>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		// Activate tooltip
		$('[data-toggle="tooltip"]').tooltip();

		$('table .delete').on('click', function() {
			var id = $(this).parent().find("#id").val();
			$('#deleteStaffModal #id').val(id);
		});

		$('table .edit').on('click', function() {
			var id = $(this).parent().find("#id").val();
			alert(id)
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/admin/staff/list',
				data : {
					action : 'find',
					id : id
				},
				dataType : 'json',
				contentType : 'application/json',
				success : function(result) {
					$('#editStaffModal #id').val(result.staffID);
					$('#editStaffModal #staffName').val(result.staffName);
					$('#editStaffModal #DOB').val(result.DOB);
					$('#editStaffModal #sex').val(result.sex);
					$('#editStaffModal #staffPhone').val(result.staffPhone);
					$('#editStaffModal #salaryID').val(result.salaryID);
					$('#editStaffModal #accountID').val(result.accountID);
					$('#editStaffModal #status').val(result.status);
				}
			});
		});
	});
</script>

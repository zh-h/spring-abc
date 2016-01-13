<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<body>

	<div id="wrapper">
		<%@ include file="../common/nav.jsp"%>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="modal  fade" id="delete" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-danger modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-body">
							<div class="modal-header">
								确定要删除？
								(还没有禁言的)
							</div>
						</div>
						<div class="modal-body">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-danger">删除</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12" style="margin-top: 30px">
					<div class="panel panel-info">
						<div class="panel-heading">用户</div>

						<div class="panel-body">
							<ul class="nav nav-tabs">
								<li role="presentation" class="active"><a href="#list"
									aria-controls="list" role="tab" data-toggle="tab">列表</a></li>
								<li class=""><a href="#search" aria-controls="search"
									role="tab" data-toggle="tab">搜索</a></li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="list">
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>
												<tr>
													<th width="5%">#</th>
													<th width="15%">用户名</th>
													<th width="20%">邮箱</th>
													<th width="15%">昵称</th>
													<th width="15%">创建时间</th>
													<th width="10%">编号</th>
													<th width="10">角色</th>
													<th width="10%">处理</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${users}" var="user">
												<tr>
													<td>${user.id}</td>
													<td>${user.username}</td>
													<td>${user.email}</td>
													<td>${user.nick}</td>
													<td>
														<fmt:formatDate value="${user.createAt}" type="both"/>
													</td>
													<td>${user.number}</td>
													<td>${user.role}</td>
													<td>
														<a class="btn btn-info btn-xs" href="${x}/back/users/${user.id}/edit" target="_blank">编辑</a>
														<button class="btn btn-danger btn-xs" data-toggle="modal"
															data-target="#delete">封禁</button>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<div role="tabpanel" class="tab-pane" id="search">
								<form action="${x}/back/users/search">
									<div class="form-horizontal" style="margin-top: 30px">
										<div class="form-group">
											<label class="control-label col-md-2">用户名/昵称/邮箱</label>
											<div class="col-md-8">
												<input type="text" name="someThing" class="form-control">
											</div>
											<div class="col-md-1">
												<input type="submit" class="btn btn-info center-block"
													value="搜索">
											</div>
										</div>
									</div>
								</form>
								</div>
							</div>
						</div>
						<div class="panel-footer">
						<%@ include file="/WEB-INF/jsp/common/pagination.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>

	<!-- /#wrapper -->

	<script src="${x}/js/jquery.min.js"></script>

	<script src="${x}/js/bootstrap.min.js"></script>

	<script src="${x}/js/metisMenu.min.js"></script>

	<script src="${x}/js/sb-admin-2.js"></script>

</body>
</html>

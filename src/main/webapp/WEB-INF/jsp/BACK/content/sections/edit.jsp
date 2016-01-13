<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/head.jsp"%>
<body>
	<div id="wrapper">
		<%@ include file="../../common/nav.jsp"%>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="row">
				<div class="col-md-12" style="margin-top: 30px">
				<%@ include file="../../common/msg.jsp" %>
					<div class="panel panel-info">
						<div class="panel-heading">节点</div>

						<div class="panel-body">
							<ul class="nav nav-tabs">
								<li class=""><a href="${back}/content/sections">列表</a></li>
								<li class=""><a href="${back}/content/sections/new">新增</a></li>
								<li class="active"><a href="#">编辑</a></li>
							</ul>
							<div class="tab-content">
								<div class="form-horizontal" style="margin-top: 30px">
									<form action="${x}/back/content/sections/update" method="POST">
									<input type="hidden" name="id" value="${node.id}">
										<div class="form-group">
											<label class="control-label col-md-2">名称</label>
											<div class="col-md-9">
												<input class="form-control"  name="name" value="${node.name}">
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-md-2">描述</label>
											<div class="col-md-9">
												<input class="form-control" name="description" value="${node.description}">
											</div>
										</div>
										<div class="form-group">
								<label class="control-label col-md-2">分类</label>
								<div class="col-md-3">
									<select class="form-control" name="sectionName">
										<c:forEach items="${sections}" var="section">
											<option value="${section.name}" <c:if test="${section.id eq node.section.id}">selected</c:if> >${section.name}</option>
										</c:forEach>
									</select>
								</div>
								<label class="control-label col-md-1">新建</label>
								<div class="col-md-3">
									<input class="form-control"  name="newSectionName">
								</div>
							</div>
										<div class="form-group">
											<div class="col-md-9 col-md-offset-2">
												<input type="submit" class="btn btn-info " value="保存节点">
											</div>
										</div>
									</form>
								</div>
							</div>
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
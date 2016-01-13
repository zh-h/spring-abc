<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
<body>

	<div id="wrapper">
		<%@ include file="../common/nav.jsp" %>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="row" >
				<div class="col-md-12"  style="margin-top:30px">
				<%@ include file="../common/msg.jsp" %>
				<div class="panel panel-info">
					<div class="panel-heading">
						权限设置
					</div>
					<div class="panel-body">
					<form action="${x}/back/app/authority" method="post">
						<div class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">编辑时限</label>
								<div class="col-md-9">
									<input class="form-control" placeholder="小时，设置为零是不允许" name="limit_edit" type="text" value="${p.limit_edit.content}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">删除时限</label>
								<div class="col-md-9">
									<input class="form-control" type="text" name="limit_delete" value="${p.limit_delete.content}">
								</div>
							</div>
							<div class="form-group">
							<label class="control-label col-md-2">删除评论</label>
							<div class="col-md-9 checkbox ">
									<label>
									<input type="checkbox" name="remind">允许
								</label>
							</div>
							</div>
							<div class="form-group">
								<div class="col-md-9 col-md-offset-2 ">
									<input type="submit" class="btn btn-info center-block" value="保存设置">
								</div>
							</div>
						</div>
						</form>
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

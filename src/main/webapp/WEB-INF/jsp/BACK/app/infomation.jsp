<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
<body>

	<div id="wrapper">
		<%@ include file="../common/nav.jsp" %>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="row">
				<div class="col-md-12" style="margin-top: 30px">
				<%@ include file="../common/msg.jsp" %>
				<div class="panel panel-info">
					<div class="panel-heading">
						基本设置
					</div>
					<div class="panel-body">
						<div class="form-horizontal">
						<form action="${x}/back/app/infomation" method="post">
							<div class="form-group">
								<label class="control-label col-md-2">网站名称</label>
								<div class="col-md-9">
									<input class="form-control" type="text" name="site_name" value="${p.site_name.content}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">网站简介</label>
								<div class="col-md-9">
									<textarea rows="6" class="form-control" name="site_description">${p.site_description.content}</textarea>
								</div>
							</div>
								<div class="form-group">
								<label class="control-label col-md-2">网站关键词</label>
								<div class="col-md-9">
									<textarea rows="6" class="form-control" name="site_keywords">${p.site_keywords.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">ICP备案号</label>
								<div class="col-md-9">
									<input class="form-control" type="text" name="site_ICP" value="${p.site_ICP.content}">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-9 col-md-offset-2 ">
									<input type="submit" class="btn btn-info center-block" value="保存设置">
								</div>
							</div>
							</form>
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

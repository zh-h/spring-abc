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
						底部元素
					</div>
					<div class="panel-body">
					<form action="${x}/back/app/footer" method="post">
						<div class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">社区描述</label>
								<div class="col-md-9">
									<textarea rows="20" class="form-control" name="footer_description">${p.footer_description.content}</textarea>
								</div>
							</div>
								<div class="form-group">
								<label class="control-label col-md-2">附加脚本</label>
								<div class="col-md-9">
									<textarea rows="20" class="form-control" name="footer_script">${p.footer_script.content}</textarea>
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

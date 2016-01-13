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
						提示信息
					</div>
					<div class="panel-body">
					<form action="${x}/back/app/tips" method="post">
						<div class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">公告</label>
								<div class="col-md-9">
									<textarea rows="8" class="form-control" name="tip_notic">${p.tip_notic.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">欢迎语</label>
								<div class="col-md-9">
									<textarea rows="8" class="form-control" name="tip_welcome">${p.tip_welcome.content}</textarea>
								</div>
							</div>
								<div class="form-group">
								<label class="control-label col-md-2">注册提示</label>
								<div class="col-md-9">
									<textarea rows="8" class="form-control" name="tip_signup">${p.tip_signup.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">话题提示</label>
								<div class="col-md-9">
									<textarea rows="16" class="form-control" name="tip_topic">${p.tip_topic.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">评论提示</label>
								<div class="col-md-9">
									<textarea rows="16" class="form-control" name="tip_comment">${p.tip_comment.content}</textarea>
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
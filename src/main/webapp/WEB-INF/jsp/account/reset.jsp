<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp"%>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-info panel-center">
					<div class="panel-heading">更改密码</div>
					<div class="panel-body">
						<form action="${x}/account/reset" method="POST" data-toggle="validator">
							<input type="hidden" name="part" value="password">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-md-2">旧密码</label>
									<div class="col-md-9">
										<input class="form-control" name="password0" type="password" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">新密码</label>
									<div class="col-md-9">
										<input class="form-control" name="password1" id="passowrd1" data-minlength="6" type="password" data-error="密码长6位到18位"/>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">确认密码</label>
									<div class="col-md-9">
										<input class="form-control" name="password2" type="password"
										data-match="#passowrd1"
											data-match-error="密码不匹配" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-9 col-md-offset-2">
										<input type="submit" class="btn btn-info btn-block"
											value="更改密码">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
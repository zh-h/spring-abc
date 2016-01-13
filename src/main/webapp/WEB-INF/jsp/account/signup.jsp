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
					<div class="panel-heading">注册</div>
					<div class="panel-body">
						<form action="${x}/account/signup" data-toggle="validator"
							role="form" method="POST">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-md-2">Email</label>
									<div class="col-md-9">
										<input class="form-control" data-error="请输入正确的邮箱"
											placeholder="正常使用的邮箱" type="email" id="email" name="email" value="${user.email}" />
										<div class="help-block with-errors"></div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-2">用户名</label>
									<div class="col-md-9">
										<input class="form-control" id="username" data-error="数字或字母，6到18位"
											pattern="^[_A-z0-9]{6,18}$" placeholder="数字或字母" type="text"
											value="${user.username}" name="username" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">密码</label>
									<div class="col-md-9">
										<input class="form-control" data-minlength="6" type="password"
											name="password" id="password" placeholder="至少6位" data-error="密码长6位到18位" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">确认密码</label>
									<div class="col-md-9">
										<input class="form-control" data-match="#password"
											data-match-error="密码不匹配" placeholder="再次输入密码" type="password" name="password1" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-9 col-md-offset-2">
										<input type="submit" class="btn btn-info btn-block" value="注册">
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
	<script type="text/javascript">
		$("#email").on("blur",function (e){
			var email=$(this).val();
			var DIV=$(this);
			$.getJSON("${x}/account/checkEmail?email="+email,function (msg){
				if(msg==true){
				DIV.parent().children(".with-errors").html("邮箱已注册");
				DIV.parent().parent().addClass("has-error");}
			});
			DIV.parent().children(".with-errors").html("");
		})
		$("#username").on("blur",function (e){
			var username=$(this).val();
			var DIV=$(this);
			$.getJSON("${x}/account/checkUsername?username="+username,function (msg){
				if(msg==true){
				DIV.parent().children(".with-errors").html("用户名已注册");
				DIV.parent().parent().addClass("has-error");}
			});
			DIV.parent().children(".with-errors").html("");
		})
	</script>
</body>
</html>
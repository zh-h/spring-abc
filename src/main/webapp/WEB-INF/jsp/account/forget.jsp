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
					<div class="panel-heading">忘记密码</div>
					<div class="panel-body">
						<form action="${x}/account/forget" method="POST" data-toggle="validator">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-md-2">邮箱</label>
									<div class="col-md-9">
										<div class="input-group">
										<input class="form-control" data-error="请输入正确的邮箱" name="email" id="email" type="email" value="${userResetForm.email}"/>
										<div class="help-block with-errors"></div> 
										<span
											class="input-group-btn">
											<button class="btn btn-info btn-email" type="button" id="btn-send">发送邮件</button>
										</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">验证码</label>
									<div class="col-md-9">
										<input class="form-control" name="code" required>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-2">新密码</label>
									<div class="col-md-9">
										<input class="form-control" data-minlength="6" type="password"
											name="password1" id="password" placeholder="至少6位" data-error="密码长6位到18位"  />
											<div class="help-block with-errors"></div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-9 col-md-offset-2">
										<button type="button" class="btn btn-info btn-block" id="btn-reset"
											>重设密码</button>
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
	var timeLimit=60;
	$("#btn-send").on("click",function (e){
		var email=$("#email").val();
		$.getJSON("${x}/account/sendEmail?email="+email,function (msg){
			console.log(msg);
		});
		$(this).html("等待60秒").addClass("disabled");
		var decrese=function (){
			if(timeLimit<1){
				timeLimit=60;
				$("#btn-send").html("发送验证码").removeClass("disabled");
				clearInterval(intervalID);//停止倒计时
				return;
			}else{
				timeLimit--;
				$("#btn-send").html("等待"+timeLimit+"秒");
			}
		}
		var intervalID=setInterval(decrese,1000);//倒计时
	});
	$("#btn-reset").on("click",function (e){
		$("form").first().submit();
	});
	$("#email").on("blur",function (e){
		var email=$("#email").val();
		if(email.length>6){//这时候暂时不验证
			$.getJSON("${x}/account/checkEmail?email="+email,function (msg){
				if(msg==0){
					$("#email").parent().children(".with-errors").first().html("邮箱不存在");
					$("#email").parent().parent().parent().addClass("has-error");
				}
			});
		}
	});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp" %>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-info panel-center">
					<div class="panel-heading">
						登陆
					</div>
					<div class="panel-body">
						<form action="${x}/account/signin" method="post">
						<div class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">用户名</label>
								<div class="col-md-9">
									<input class="form-control" type="text" id="username" placeholder="或者邮箱" name="username"/>
									<div class="help-block with-errors" style="display:none;">用户名或邮箱不存在</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">密码</label>
								<div class="col-md-9">
									<input class="form-control" type="password" name="password"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-9 checkbox col-md-offset-2">
									<label>
									<input type="checkbox" name="remind">记住我
								</label>
							</div>
							</div>
							<div class="form-group">
								
								<div class="col-md-9 col-md-offset-2">
									<input type="submit" class="btn btn-info btn-block" value="登陆">
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="panel-footer">
						忘记密码？<a href="${x}/account/forget"><span>找回</span></a>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
<script type="text/javascript">
$("#username").on("blur",function (e){
	var username=$(this).val();
	var usernameDIV=$(this);
	$.getJSON("${x}/account/checkUsername?username="+username,function (msg){
		if(msg!==true){
		usernameDIV.parent().parent().addClass("has-error");
		usernameDIV.parent().children(".with-errors").show();}
	});
})
$("#username").on("focus",function (e){
					$(this).parent().parent().removeClass("has-error");
					$(this).parent().children(".with-errors").hide();
});
</script>
</body>
</html>
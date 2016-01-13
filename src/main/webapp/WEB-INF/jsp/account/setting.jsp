<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp" %>
		<div class="row">
			<div class="col-md-9">
			<ol class="breadcrumb">
  <li class="active">个人中心</li>
</ol>
				<div class="panel panel-info">
					<div class="panel-heading">
						基本设置
					</div>
					<div class="panel-body">
						<form action="${x}/account/setting/update" enctype="multipart/form-data" method="POST">
						<input type="hidden" name="id" value="${user.id}">
						<div class="form-horizontal">
							<div class="form-group ">
								<label class="control-label col-md-2">昵称</label>
								<div class="col-md-9">
									<input class="form-control" name="nick" id="nick" value="${user.nick}">
									<div class="help-block with-errors" style="display:none;">昵称被占用</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">头像</label>
								<div class="col-md-9">
									 <input style="padding: 6px" type="file" id="file" name="file" />
									 <img alt="" id="file-preview" style="max-height: 320px;width: auto" src="${user.avatar}"> 
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">位置</label>
								<div class="col-md-9">
									<input class="form-control" name="location" value="${user.location}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">网站</label>
								<div class="col-md-9">
									<input class="form-control" name="homePage" value="${user.homePage}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">代码库</label>
								<div class="col-md-9">
									<input class="form-control" name="github" value="${user.github}"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">社交网络</label>
								<div class="col-md-9">
									<input class="form-control" name="twitter" value="${user.twitter}"/>
								</div>
							</div>
							<div class="form-group ">
								<label class="control-label col-md-2">签名</label>
								<div class="col-md-9">
									<input class="form-control" name="signature" id="signature" value="${user.signature}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2">个人资料</label>
								<div class="col-md-9">
									<textarea rows="6" class="form-control" name="description">${user.description}</textarea>
								</div>
							</div>
							<div class="form-group">
								
								<div class="col-md-9 col-md-offset-2">
									<input type="submit" class="btn btn-info btn-block" value="更新资料">
								</div>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3 sidebar">
				<div class="panel panel-info">
					<div class="panel-body ">
						<div class="media">
							<div class="media-left text-center">
								<a href="${x}/users/${user.id}/topics">
									<img class="media-object" src="${user.avatar}" width="72px" alt="${user.nick}">
								</a>
							</div>
							<div class="media-body">
								<p>	
									<a href="${x}/users/${user.id}/topics"><h4>${user.nick}</h4></a> 
								</p>
								<p>第${user.number}号会员</p>
								<p>加入于${user.createAt}</p>
								<hr style="margin:0px">
								<p>${user.signature}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-body text-center">
						<a class="btn btn-info btn-block" href="${x}/account/reset">重置密码</a>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
<script type="text/javascript">
$("#nick").on("blur",function (e){
	var nick=$(this).val();
	var nickDIV=$(this);
	$.getJSON("${x}/account/checkNick?nick="+nick,function (msg){
		if(msg==true){
		nickDIV.parent().parent().addClass("has-error");
		nickDIV.parent().children(".with-errors").show();}
	});
})
$("#nick").on("focus",function (e){
					$(this).parent().parent().removeClass("has-error");
					$(this).parent().children(".with-errors").hide();
});
$("#file").on(
		"change",
		function(e) {
			var fileControl = $(this);
			var file = fileControl[0].files[0];//
			fileControl.parent().removeClass("has-error");
			if (file.size > 200000) {
				console.log(file.size);
				alert("文件过大>200KB");
				fileControl.parent().parent().addClass("has-error");
			} else {
				console.log(file.size);
				createURL(file,"file-preview");
			}

		});
</script>
</body>
</html>
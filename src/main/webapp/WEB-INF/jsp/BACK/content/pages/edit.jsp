<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/head.jsp"%>
<body>
	<div id="wrapper">
		<%@ include file="../../common/nav.jsp"%>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="modal fade" id="preview" tabindex="-1" role="dialog"
				aria-labelledby="preview">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">预览</h4>
						</div>
						<div class="modal-body" id="preview-body"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-info" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="upload" tabindex="-1" role="dialog"
				aria-labelledby="upload">
				<div class="modal-dialog modal-md" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">上传</h4>
						</div>
						<div class="modal-body" id="upload-body">
							<form action="${x}/upload" class="dropzone" id="dropzone"></form>
							<div class="media">
								<div class="media-body">
									<img alt="" src="" style="max-height: 640px; width: auto"
										id="file-preview">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-info" id="btn-upload">上传</button>
							<button type="button" class="btn btn-info" id="btn-insert"
								data-dismiss="modal">完成</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12" style="margin-top: 30px">
				<%@ include file="../../common/msg.jsp" %>
					<div class="panel panel-info">
						<div class="panel-heading">页面</div>

						<div class="panel-body">
							<ul class="nav nav-tabs">
								<li class=""><a href="${x}/back/content/pages">列表</a></li>
								<li class=""><a href="${x}/back/content/pages/create" >创建</a></li>
								<li class="active"><a href="#" >编辑</a></li>
							</ul>
							<div class="tab-content">
								<form role="form" action="${x}/back/content/pages/update" method="POST"
									data-toggle="validator">
									<input type="hidden" name="id" value="${thePage.id }">
									<div class="form-group">
										<label>标题</label> <input name="title" class="form-control" value="${thePage.title}">
										<div class="help-block with-errors"></div>
									</div>
									<div class="form-group">
										<label>链接</label> <input name="url"  class="form-control" value="${thePage.url}">
										<div class="help-block with-errors"></div>
									</div>
									<div class="form-group">
										<label>正文</label>
										<div class="btn-group pull-right">
											<button type="button" id="btn-preview" class="btn btn-info"
												style="border-right-width: 2px; border-right-color: #555;"
												data-toggle="modal" data-target="#preview">preview</button>
											<button type="button" id="btn-upload" class="btn btn-info"
												data-toggle="modal" data-target="#upload">upload</button>
										</div>
										<textarea rows="30" class="form-control" name="content"
											id="content" data-minlength="6" data-error="正文不少于六个字">${thePage.content}</textarea>
										<div class="help-block with-errors"></div>
									</div>
									<div class="btn-group">
										<button type="submit" class="btn btn-info">保 存</button>
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<%@ include file="/WEB-INF/jsp/common/pagination.jsp"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>

	<!-- /#wrapper -->

	<script src="${x}/js/bootstrap.min.js"></script>

	<script src="${x}/js/metisMenu.min.js"></script>

	<script src="${x}/js/sb-admin-2.js"></script>
	<script type="text/javascript" src="${x}/js/marked.min.js"></script>
	<script type="text/javascript" src="${x}/js/dropzone.js"></script>
	<script type="text/javascript" src="${x}/js/validator.js"></script>
	<script type="text/javascript">
		$("#btn-preview").on("click", function(e) {
			var content = $("#content").val();
			console.log(content);
			var content_marked = marked(content);
			$("#preview-body").html(content_marked);
		});
		
		Dropzone.options.dropzone = {
				  autoProcessQueue: false,
				  init: function() {
				    var submitButton = document.querySelector("#btn-upload")
				        dropzone = this; // closure

				    submitButton.addEventListener("click", function() {
				    	dropzone.processQueue(); // Tell Dropzone to process all queued file.
				    });
				    this.on("success", function(file, response) {
				    	var url=response.url;
				    	var name=response.key;
				    	var btnInsert="<button class=\"btn btn-info btn-sm btn-insert col-md-6 col-md-offset-3\""
				    	+" id=\""+url+"\""
				    	+" type=\"button\">插入</button>";
				    	var divInsert=document.createElement("div");
				    	divInsert.setAttribute("class", "row");
				    	divInsert.setAttribute("style","margin-top:1em");
				    	divInsert.innerHTML=btnInsert;
				    	file.previewTemplate.appendChild(divInsert);
				    	document.getElementById(url).onclick=function(e){//给这个按钮添加方法
				    		url=e.srcElement.id;
				    		var textareaContent=document.getElementById("content");
				    		var mdImg="!["+name+"]("+url+")";//md的图片元素
				    		inserStr(textareaContent,mdImg);//光标位置插入
				    	}
				    });
				  }
				};
	</script>
</body>
</html>
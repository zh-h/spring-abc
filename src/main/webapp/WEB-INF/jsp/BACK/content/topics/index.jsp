<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/head.jsp"%>
<body>

	<div id="wrapper">
		<%@ include file="../../common/nav.jsp"%>
		<div id="page-wrapper" style="min-height: 243px;">
			<div class="modal  fade" id="modal-delete" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-danger modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-body">
							<div class="modal-header">
								确定要删除？
							</div>
						</div>
						<div class="modal-body">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-danger btn-sure">删除</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12" style="margin-top: 30px">
				<%@ include file="../../common/msg.jsp" %>
					<div class="panel panel-info">
						<div class="panel-heading">话题</div>

						<div class="panel-body">
							<ul class="nav nav-tabs">
								<li role="presentation" class="active"><a href="#list"
									aria-controls="list" role="tab" data-toggle="tab">列表</a></li>
								<li class=""><a href="#search" aria-controls="search"
									role="tab" data-toggle="tab">搜索</a></li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="list">
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>
												<tr>
													<th width="5%">#</th>
													<th width="35%">标题</th>
													<th width="10%">作者</th>
													<th width="10%">创建时间</th>
													<th width="10%">最后回复</th>
													<th width="5%">浏览</th>
													<th width="5%">回答</th>
													<th width="10%">处理</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${topics}" var="topic">
												<tr>
													<td>${topic.id}</td>
													<td>
              										<c:choose>  
                   										<c:when test="${fn:length(topic.title) > 2}">  
                      									<c:out value="${fn:substring(topic.title, 0, 20)}" /> . . .  
                   										</c:when>  
                  										<c:otherwise>  
                   										<c:out value="${topic.title}" />  
                   										</c:otherwise>  
              										</c:choose>  
													</td>
													<td>
														${fn:substring(topic.user.nick, 0, 100)}
													</td>
													<td>
														<fmt:formatDate value="${topic.createAt}" type="both"/>
													</td>
													<td>
														<fmt:formatDate value="${topic.lastCommentAt}" type="both"/>
													</td>
													<td>${topic.viewCount}</td>
													<td>${topic.commentCount}</td>
													<td>
														<a class="btn btn-info btn-xs" href="${x}/back/content/topics/${topic.id}/edit" target="_blank">编辑</a>
														<button class="btn btn-danger btn-xs btn-delete" data-url="${x}/back/content/topics/${topic.id}/delete" >删除</button>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<div role="tabpanel" class="tab-pane" id="search">
								<form action="${x}/back/content/topics/search" method="get">
									<div class="form-horizontal" style="margin-top: 30px">
										<div class="form-group">
											<label class="control-label col-md-2">昵称</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="nick" value="${nick}">
											</div>
										</div>
									<div class="form-group">
											<label class="control-label col-md-2">标题</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="title" value="${title }">
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-9 col-md-offset-2 ">
												<input type="submit" class="btn btn-info center-block"
													value="搜索">
											</div>
										</div>
									</div>
									</form>
								</div>
							</div>
						</div>
						<div class="panel-footer">
						<%@ include file="/WEB-INF/jsp/common/pagination.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<script src="${x}/js/bootstrap.min.js"></script>
	<script src="${x}/js/metisMenu.min.js"></script>
	<script src="${x}/js/sb-admin-2.js"></script>
	<script type="text/javascript">
	$(".btn-delete").on("click",function (e){
		var url=$(this).attr("data-url");
		$("#modal-delete").modal('show');
		$(".btn-sure").on("click",function (e){
			location.href=url;
		})
	})
	</script>
</body>
</html>

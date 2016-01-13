<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp" %>
		<div class="row">
			<div class="col-md-9">
				<ol class="breadcrumb">
  <li><a href="${x}/notifications">通知</a></li>
  <li class="active">未读</li>
</ol>
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="btu-group ">
							<a href="${x}/notifications" class="btn btn-info btn-xs">未读</a>
							<a href="${x}/notifications/all" class="btn  btn-xs">全部</a>
						</div>				
					</div>
					
					<ul class="list-group-panel-body list-group">
						<c:forEach items="${notifications}" var="notification">
						<li class="list-group-item">
							<div class="media">
								<div class="media-body">
									<p>	
										<a href="${x}/users/${notification.comment.user.id}/comments">${notification.comment.user.nick}</a> •
										于
										<span><my:Flashback time="${notification.comment.createAt}"/></span>
										 •在
										<a href="${x}/topics/${notification.comment.topic.id}#floor${notification.comment.floor}">${notification.comment.topic.title}</a> •
										${notification.type}了你
										
									</p>
									<p>
										${notification.comment.content}
									</p>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
					<div class="panel-footer ">
						<%@ include file="/WEB-INF/jsp/common/pagination.jsp" %>
					</div>
				</div>
			</div>
			<div class="col-md-3 sidebar">
			<%@ include file="common/ad.jsp" %>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
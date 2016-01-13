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
  <li><a href="${x}/users/${id}/topics">个人中心</a></li>
  <li class="active">评论</li>
</ol>
				<div class="panel panel-info">
					<ul class="nav nav-tabs">
						<li class=""><a href="${x}/users/${id}/topics">帖子</a></li>
						<li class="active"><a href="${x}/users/${id}/comments">回复</a></li>
						<li class=""><a href="${x}/users/${id}/collections">收藏</a></li>
						<li class=""><a href="${x}/users/${id}/following">追随着</a>
						</li><li class=""><a href="${x}/users/${id}/followers">追随者</a></li></ul>
						<ul class="list-group-panel-body list-group list-group-users">
							<c:forEach items="${page.content}" var="comment">
							<li class="list-group-item">
								<div class="media">
									<div class="media-body">
										<p>
											于<my:Flashback time="${comment.createAt}"/>在
											<a href="${x}/topics/${comment.topic.id}#floor${comment.floor}">
												<span>
													${comment.topic.title}
												</span>
											</a>
											回复道
										</p>
										<p>
											${comment.content}
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
				<%@ include file="common/card.jsp" %>
				<%@ include file="common/introduce.jsp" %>
				</div>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
<%@ include file="common/script.jsp" %>
</body>
</html>
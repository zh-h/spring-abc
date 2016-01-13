<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp"%>
		<div class="row">
			<c:forEach items="${nodes}" var="node">
				<div class="col-md-3">
				<div class="panel panel-info">
					<div class="panel-body ">
						<div class="media">
							<%-- <div class="media-left text-center">
								<a href="#"> <img class="media-object" src="${node.avatar}"
									width="72px" alt="${node.name}">
								</a>
							</div> --%>
							<div class="media-body">
								<p>
									<a href="${x}/nodes/${node.id}"><h4>${node.name}</h4></a>
								</p>
								<p>共有${node.topicCount}个话题</p>
								<hr style="margin: 0px">
								<p>${node.description}</p>
							</div>
						</div>
					</div>
				</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
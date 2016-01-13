<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>
	<div class="main-container container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="media">
							<div class="media-body">
								<h2 class="media-heading">${page.title}</h2>
								<p>
									<span>发表于${page.createAt}</span>
									<c:if test="${not empty page.updateAt}">
										•<span>修改于${page.updateAt}</span>
										</c:if>
										•<span>${page.viewCount}次浏览</span>
								</p>
							</div>
						</div>
					</div>
					<div class="panel-body" id="page-content">${page.content}</div>
				</div>
			</div>
			<div class="col-md-3 sidebar">
			<div class="panel panel-danger">
				<div class="panel-heading">
					AD
				</div>
				${p.ad_side.content}
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="//cdn.bootcss.com/marked/0.3.5/marked.min.js"></script>
	<script type="text/javascript">
	$(function (){
		var originHTML=marked($("#page-content").html());
		$("#page-content").html(originHTML);
	})
	</script>
</body>
</html>
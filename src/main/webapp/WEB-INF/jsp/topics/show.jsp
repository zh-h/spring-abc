<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
	<div class="main-container container">
		<%@ include file="/WEB-INF/jsp/common/msg.jsp"%>
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
			<div class="col-md-9">
			<ol class="breadcrumb">
  <li><a href="${x}/">Topic</a></li>
  <li><a href="${x}/nodes/${topic.node.id}">${topic.node.name}</a></li>
  <li class="active">${topic.title}</li>
</ol>
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="media">
							<div class="media-body">
								<h2 class="media-heading">${topic.title}</h2>
								<p>
									<span>发表于<my:Flashback time="${topic.createAt}"/></span>
									<c:if test="${not empty topic.updateAt}">
										•<span>修改于<my:Flashback time="${topic.updateAt}"/></span>
										</c:if>
									<c:if test="${not empty topic.lastCommentUser.id}">
										•<a href="${x}/users/${topic.lastCommentUser.id}/topics">${topic.lastCommentUser.name}</a>
										最后回复于<my:Flashback time="${topic.lastCommentAt}"/>
									</c:if>
										•<span>${topic.viewCount}次浏览</span>
								</p>
							</div>
						</div>
					</div>

					<div class="panel-body" id="topic-content">${topic.content}</div>
					<div class="panel-footer ">
						<div class="btu-group ">
							<button class="btn btn-info btn-sm fa-bookmark fa" id="btn-collect"><c:if test="${isCollected eq true }">√</c:if></button>
							<c:if test="${sessionScope.user.id eq topic.user.id or sessionScope.user.role eq 'admin'}">
							<a class="btn btn-info btn-sm glyphicon glyphicon-pencil" href="${x}/topics/${topic.id}/edit" target="_blank"></a>
							<button class="btn btn-danger btn-sm btn-delete glyphicon glyphicon-trash" data-url="${x}/topics/${topic.id}/delete"></button>
							</c:if>
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">共${topic.commentCount} 条回复</div>

					<ul id="comments" class="list-group-panel-body list-group">
						<c:forEach items="${comments}" var="comment">
							<li id="floor${comment.floor}" class="list-group-item <c:if test="${comment.isLike}">list-group-item-info</c:if>">
								<div class="media">
									<div class="media-left">
										<a href="${x}/users/${comment.user.id}/topics"> <img
											class="media-object" src="${comment.user.avatar}"
											width="48px" alt="${comment.user.nick}">
										</a>
										<a  class="fa fa-thumbs-o-up btn" data-url="${x}/comments/${comment.id}/like">${comment.likeCount eq 0?'':comment.likeCount}</a>
									</div>
									<div class="media-body">
										<p class="meta"><a href="#">${comment.floor}
												#</a>  •
											<a href="${x}/users/${comment.user.id}/topics">${comment.user.nick}</a>
											• <span>回复于<my:Flashback time="${comment.createAt}"/></span>
										<c:if test="${sessionScope.user.id eq comment.user.id or sessionScope.user.role eq 'admin'}">
										<a  class="glyphicon glyphicon-edit btn" href="${x}/comments/${comment.id}/edit" target="_blank"></a>
										<a  class="glyphicon glyphicon-trash btn btn-delete" data-url="${x}/comments/${comment.id}/delete"></a>
										</c:if>
										</p>
										<p>${comment.content}</p>
									</div>
									<div class="media-right media-middle">
										<input type="hidden" name="nick" value="${comment.user.nick }"> 
										<a  class="fa fa-reply btn"></a>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
					<div class="panel-footer ">
						<%@ include file="/WEB-INF/jsp/common/pagination.jsp"%>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-body">
						<c:choose>
							<c:when test="${empty sessionScope.user}">
								<form action="${x}/topics/comment" method="post">
									<div class="form-group">
										<textarea class="form-control " rows="7" disabled></textarea>
									</div>
									<button class="btn btn-info disabled">回复</button>
									<a href="${x}/account/signin">&nbsp;&nbsp;<span>需要登录</span></a>
								</form>

							</c:when>
							<c:otherwise>
								<form id="form-comment" action="${x}/topics/comment" method="post">
									<div class="form-group">
										<input type="hidden" name="topicId" value="${topic.id}">
										<textarea class="form-control" rows="7" name="content" id="comment-content">${comment.content}</textarea>
									</div>
									<button class="btn btn-info btn-comment" type="button">回复</button>
								</form>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="col-md-3 sidebar">
				<%@ include file="common/card.jsp" %>
				<div class="panel panel-info">
					<div class="panel-heading">AD</div>
					${p.ad_inner.content}
				</div>
				<div class="panel panel-info">
			<div class="panel-heading">其他话题</div>
				<ul class="list-group">
					<c:forEach var="topic" items="${otherTopics}">
						<li class="list-group-item">
							<a href="${x}/topics/${topic.id}">
								<c:choose>  
                   					<c:when test="${fn:length(topic.title) > 12}">  
                      					<c:out value="${fn:substring(topic.title, 0, 12)}" /> . . .  
                   						</c:when>  
                  					<c:otherwise>  
                   						<c:out value="${topic.title}" />  
                   					</c:otherwise>  
              					</c:choose> 
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
	<script src="//cdn.bootcss.com/marked/0.3.5/marked.min.js"></script>
	<script type="text/javascript">
	$(function (){
		var originHTML=marked($("#topic-content").text());
		$("#topic-content").html(originHTML);
	})
	$("#btn-collect").on("click",function (e){
		var topicURL="${x}/topics/${topic.id}/";
		$.getJSON(topicURL+"isCollected",function (msg){
			if(msg==1){
				$.getJSON(topicURL+"unCollect",function (res){
					if(res==1){
						$("#btn-collect").html("");
					}
				})
			}else if(msg==0){
				$.getJSON(topicURL+"collect",function (done){
					if(done==1){
						$("#btn-collect").html(" √");
					}
				})
			}
		})
		return true;
	});
	$(".fa-reply").on("click",function (e){
		var nick=$(this).parent().children("input").first().val();
		var atStr=" @"+nick+" ";//这个是回复的内容
		var divConent=document.getElementById("comment-content");
		inserStr(divConent,atStr);
		return false;
	});
	$(".btn-delete").on("click",function (e){
		var url=$(this).attr("data-url");
		var divItem=$(this).parent().parent().parent();
		$("#modal-delete").modal("show");
		$(".btn-sure").on("click",function (e){
			$.getJSON(url,function (msg){
				if(msg==true){
					$("#modal-delete").modal('hide');
					divItem.hide("slow");
				}
			});
		});
	})
	$(".fa-thumbs-o-up").on("click",function(e){
		var likeBtn=$(this);
		var url=likeBtn.attr("data-url");
		var divComment=likeBtn.parent().parent().parent();
		var count=parseInt(likeBtn.html());
		$.getJSON(url,function (msg){
			console.log(msg);
			if(msg==true){
				likeBtn.html(count+1);
				divComment.addClass("list-group-item-info");
			}else{
				likeBtn.html(count==1?'':count-1);
				divComment.removeClass("list-group-item-info");
			}
		});
	})
	$(".btn-comment").on('click',function (e){
		var data=$("#form-comment").serialize();
		var url="${x}/comments/create";
		$.post(url,data,function (msg){
			$("#comments").append(msg);
			$("#comments .list-group-item").last().hide().show('slow');
		})
	});
	</script>
</body>
</html>
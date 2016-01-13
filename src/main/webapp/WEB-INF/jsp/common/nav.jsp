<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar  navbar-static-top navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a href="${x}/" class="navbar-brand">${p.site_name.content}</a>
		</div>
		<span>
			<ul class="nav navbar-nav">
				${p.nav_main.content}
			</ul>
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${x}/account/signin"><span class="glyphicon glyphicon-log-in"></span> 登陆</a></li>
						<li><a href="${x}/account/signup"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav user-bar navbar-right">
					<li class="notification-count"><a href="${x}/topics/create"
							class="" title="发表新话题"><span
								class="glyphicon glyphicon-pencil"></span></a></li>
						<li class="notification-count"><a href="${x}/notifications"
							class="" title="通知"><span
								class="glyphicon glyphicon-bell"></span> <strong id="notification-count">0</strong></a> </li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${sessionScope.user.nick}
								<span class="caret"></span>
						</a>
							<button class="navbar-toggle" type="button"
								data-toggle="dropdown" role="button" aria-expanded="false">
								<span class="sr-only">Toggle</span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li class="">
								<a href="${x}/users/${sessionScope.user.id}/topics">
								<span class="glyphicon glyphicon-home" aria-hidden="true"></span> 我的主页</a>
								</li>
								<li class="">
								<a href="${x}/account/setting"><span class="glyphicon glyphicon-cog"></span> 个人设置</a>
								</li>
								<li class="">
								<a href="${x}/users/${sessionScope.user.id}/collections">
								<span class="glyphicon glyphicon-bookmark"></span> 收藏话题</a>
								</li>
								<li class="">
								<a href="${x}/users/${sessionScope.user.id}/following">
								<span class="glyphicon glyphicon-asterisk"></span> 我所关注</a>
								</li>
							<c:if test="${sessionScope.user.role eq p.comment_edit.content}">
								<li class=""><div class="divider"></div></li>
								<li class="">
								<a href="${x}/back/">
								<span class="glyphicon glyphicon-cloud"></span> 后台管理</a>
							</li>
								</c:if>
								<li class=""><div class="divider"></div></li>
								<li class=""><a rel="nofollow" data-method="delete"
									href="${x}/account/signout">
									<span class="glyphicon glyphicon-off"></span> 退出</a></li>
							</ul></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
</nav>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${x}/back/">${p.site_name.content}</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" role="button" aria-expanded="false"> <span
				class="glyphicon glyphicon-user" aria-hidden="true"></span>
				${sessionScope.user.nick} <span class="caret"></span>
		</a>
			<button class="navbar-toggle" type="button" data-toggle="dropdown"
				role="button" aria-expanded="false">
				<span class="sr-only">Toggle</span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li class=""><a
					href="${x}/users/${sessionScope.user.id}/topics"> <span
						class="glyphicon glyphicon-home" aria-hidden="true"></span> 我的主页
				</a></li>
				<li class=""><a href="${x}/account/setting"><span
						class="glyphicon glyphicon-cog"></span> 个人设置</a></li>
				<li class=""><a
					href="${x}/users/${sessionScope.user.id}/collections"> <span
						class="glyphicon glyphicon-bookmark"></span> 收藏话题
				</a></li>
				<li class=""><a
					href="${x}/users/${sessionScope.user.id}/following"> <span
						class="glyphicon glyphicon-asterisk"></span> 我所关注
				</a></li>
				<li class=""><div class="divider"></div></li>
				<li class=""><a href="${x}/back/"> <span
						class="glyphicon glyphicon-cloud"></span> 后台管理
				</a></li>
				<li class=""><div class="divider"></div></li>
				<li class=""><a rel="nofollow" data-method="delete"
					href="${x}/account/signout"> <span
						class="glyphicon glyphicon-off"></span> 退出
				</a></li>
			</ul></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
						运行状态<span class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="${back}/status/overview">统计信息</a></li>
						<li><a href="${back}/status/log">日志报告</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa  fa-gear"></i> 站点设置<span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="${back}/app/infomation">站点信息</a></li>
						<li><a href="${back}/app/nav">导航安排</a></li>
						<li><a href="${back}/app/footer">底部元素</a></li>
						<li><a href="${back}/app/ad">广告栏目</a></li>
						<li><a href="${back}/app/tips">提示信息</a></li>
						<li><a href="${back}/app/authority">权限设置</a></li>
					</ul> <!-- /.nav-second-level --></li>

				<li><a href="#"><i class="fa fa-book"></i> 社区内容<span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="${back}/content/topics">话题</a></li>
						<li><a href="${back}/content/sections">分类和节点</a></li>
						<li><a href="${back}/content/comments">评论</a></li>
						<li><a href="${back}/content/pages">页面</a></li>
					</ul> <!-- /.nav-second-level --></li>

				<li><a href="#"><i class="fa  fa-user"></i> 用户管理<span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="${back}/users/admin">管理员们</a></li>
						<li><a href="${back}/users/all">用户列表</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa  fa-envelope"></i> 邮件批发<span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li><a href="${back}/emails/overview">统计</a></li>
						<li><a href="${back}/emails/send">测试发件</a></li>
					</ul> <!-- /.nav-second-level --></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
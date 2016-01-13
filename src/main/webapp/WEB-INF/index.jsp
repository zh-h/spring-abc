<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${requestScope.ContenPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<meta charset="utf-8">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="apple-mobile-web-app-capable" content="no">
	<meta content="True" name="HandheldFriendly">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<style type="text/css">
		.tag{

		}
		.thumbnail{
			width: 60px;
		}
		.meta a{
			color: #959595;
		}
	</style>
	${headmeta}
</head>
<body>
	<nav class="navbar  navbar-static-top navbar-default">
		${nav}
	</nav>
	<div class="main-container container">
		${msg}
		<div class="row">
			<div class="col-md-6">
				${body.left}
			</div>
			<div class="col-md-3">
				${body.right}
			</div>
		</div>
<footer class="footer" style="padding:10px 0px 20px 0px">
	<div class="container">
		<div class="media">
			<div class="media-left" style="margin-right:20px;">
				<img class="media-object" src="http://ruby-china-files.b0.upaiyun.com/photo/5982eaaa64f467d9dbda03ad4f40ea27.png" style="width:56px;">
			</div>

			<div class="media-body">
				<div class="links">
					<a href="/wiki/about">关于</a> / 
					<a href="/users">活跃会员</a> / 
					<a href="/api">API</a> 
				</div>
				<div class="copyright">
					中国 Ruby 社区，由众多爱好者共同维护，致力于构建完善的 Ruby 中文社区。<br>
					图片存储由 <a href="https://www.upyun.com/?utm_source=ruby-china&amp;utm_medium=ad&amp;utm_campaign=upyun&amp;md=ruby-china" target="_blank" style="display:inline-block;" rel="twipsy" title="" data-original-title="图片存储由又拍云赞助">又拍云存储</a> 提供。
				</div>
				<div class="links" style="margin-top:8px" data-no-turbolink="">
					<a href="?locale=zh-CN" rel="nofollow">简体中文</a> / <a href="?locale=zh-TW" rel="nofollow">正體中文</a> / <a href="?locale=en" rel="nofollow">English</a>
				</div>
			</div>
			<div class="media-right" style="width:200px; text-align:right;">
				<a href="http://www.ucloud.cn/?ref=ruby-china" target="_blank" rel="twipsy" title="" style="display:inline-block;margin-right:5px; margin-top: 5px;" data-original-title="本站服务器由 UCloud 赞助">
					<img src="http://ruby-china-files.b0.upaiyun.com/photo/2015/60202bb15bf6dc06fc8dd7e8baea061c.png" style="height:24px;">
				</a>
			</div>

		</div>
	</div>
</footer>
</div>
</body>
</html>
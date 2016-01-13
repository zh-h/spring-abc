<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="panel panel-info">
	<div class="panel-heading">会员信息</div>
	<ul class="list-group">
		<li class="list-group-item"><i class="fa fa-map-marker"></i> 城市:
		<span>${user.location}</span>
		</li>
		<li class="list-group-item"><i class="fa  fa-envelope"></i> 邮箱: 
		<span>******</span>
		</li>
		<li class="list-group-item"><i class="fa  fa-github"></i> 代码: 
		<a href="${user.github}"><span>${user.github}</span></a>
		</li>
		<li class="list-group-item"><i class="fa fa-paper-plane"></i> 主页:
		<a href="${user.homePage}"><span>${user.homePage}</span></a>
		</li>
		<li class="list-group-item"><i class="fa  fa-twitter"></i> 推特: 
		<a href="${user.twitter}"><span>${user.twitter}</span></a></li>
		<li class="list-group-item"><i class="fa  fa-info-circle fa-lg"></i> 自描:
		<p>${user.description}</p></li>
	</ul>

</div>
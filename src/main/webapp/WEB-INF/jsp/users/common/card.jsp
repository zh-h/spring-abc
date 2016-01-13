<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="panel panel-info">
	<div class="panel-body ">
		<div class="media">
			<div class="media-left text-center">
				<a href="#"> <img class="media-object"
					src="${user.avatar}"
					width="72px" alt="${user.nick}">
				</a>
				<p>
					<span class="badge">${user.role}</span>
				</p>
				<p>
					<button class="btn btn-info btn-xs" id="btn-follow"></button>
				</p>
			</div>
			<div class="media-body">
				<p>
					<a href="${x}/users/${user.id}/topics"><h4>${user.nick}</h4></a>
				</p>
				<p>第${user.number}号会员</p>
				<p>加入于${user.createAt}</p>
				<hr style="margin: 0px">
				<p>${user.signature}</p>
			</div>
		</div>
	</div>
</div>
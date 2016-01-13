<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<li id="floor22" class="list-group-item " style="display: block;">
<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<strong>error!</strong><c:forEach items="${error }" var="errorMsg">
							${errorMsg.defaultMessage}!
					</c:forEach>
</div>
</li>
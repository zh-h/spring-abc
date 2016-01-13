<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<c:if test="${not empty msg}">
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<strong>success!</strong> ${msg}
		</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<strong>error!</strong>
			<c:choose>
				<c:when test="${empty error.size() }">
					${error}
				</c:when>
				<c:otherwise>
					<c:forEach items="${error }" var="errorMsg">
						${errorMsg.defaultMessage}!
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
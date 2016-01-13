<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${page.totalElements>0}">
	<ul class="pagination" style="margin: 0px">
		<c:set value="8" var="style"></c:set>
		<li><a href="${requestScope.serverPath}?p=1">←</a></li>
		<c:choose>
			<c:when test="${page.totalPages ge style}">
				<c:forEach var="item" varStatus="status" begin="1" end="${style-3}">
					<c:choose>
						<c:when test="${page.number+1 eq status.index }">
							<li class="active"><a
								href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="disabled"><a>...</a></li>
				<c:forEach var="item" varStatus="status"
					begin="${page.totalPages-2}" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number+1 eq status.index }">
							<li class="active"><a
								href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="item" varStatus="status" begin="1"
					end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number+1 eq status.index }">
							<li class="active"><a
								href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${requestScope.serverPath}?p=${status.index}">${status.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</c:otherwise>
		</c:choose>
		<li><a rel="next"
			href="${requestScope.serverPath}?p=${page.totalPages}">→</a></li>
	</ul>
</c:if>
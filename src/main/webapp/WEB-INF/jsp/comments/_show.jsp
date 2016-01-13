<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib prefix="my" uri="http://spring-abc.xyz/mytaglib" %><c:set value="<%=request.getContextPath() %>" var="x"></c:set><c:set value="${applicationScope}" var="p"></c:set><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="footer" style="border-top:1px;border-bottom:0px;border-left:0px;border-right:0px; border-style: solid;    border-color: #bce8f1;">
<div class="container">
	${p.nav_footer.content}
	<div class="row">
		<div class="col-md-4">
		♡ lovely developed by zonghua 
		</div>
		<div class="col-md-4 text-center">
			Copyright @ Spring abc
		</div>
		<div class="col-md-4 text-right">
		从接收HTTP请求到渲染完成视图耗时<b><%=System.currentTimeMillis()-(Long)request.getAttribute("startTime") %></b>ms
		</div>
	</div>
	${p.footer_script.content}
</div>
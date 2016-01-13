<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><c:set
	value="<%=request.getContextPath() %>" var="x"></c:set>
	<c:set value="${x}/back" var="back"></c:set>
	<c:set value="${applicationScope}" var="p"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${p.site_name.content}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-mobile-web-app-capable" content="no">
<meta content="True" name="HandheldFriendly">
<link rel="shortcut icon" href="${x}/img/favicon.png" type="image/png" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${x}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${x}/css/bootstrap-flat.css">
<link rel="stylesheet" type="text/css" href="${x}/css/metisMenu.min.css">
<link rel="stylesheet" type="text/css" href="${x}/css/timeline.css">
<link rel="stylesheet" type="text/css" href="${x}/css/sb-admin-2.css">
<link rel="stylesheet" type="text/css" href="${x}/css/dropzone.css">
<link rel="stylesheet" type="text/css"
	href="${x}/css/font-awesome.min.css">
	<script src="${x}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${x}/js/app.js"></script>
</head>
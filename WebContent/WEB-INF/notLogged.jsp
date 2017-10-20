<%@page import="model.db.PostDao"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
	<body>
	<jsp:include page="headerNotLogged.jsp"></jsp:include>
	
		<table>
			<c:forEach items="${requestScope.allPosts}" var="post" >
			<h1><c:out value="${ post.description }">no description</c:out></h1><br>
			<img <c:out value="${ post.postUrl } ">no image</c:out> width="100px" height="100px">
			<p><c:out value="${ post.points }"></c:out></p>
			<p><c:out value="${ post.user.username }"></c:out></p>
			</c:forEach>
		</table>
	</body>
</html>
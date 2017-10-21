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
	<style type="text/css">
		#parent{  width: 900px; height: 20px;}
		#child{background-color: blue; width: 350px; float: right; }
		#namePoints{background-color: red; display: inline-flex; margin-top: 0px; }
		.points{margin-right: 5px;}
	</style>
</head>
	<body>
	<jsp:include page="headerNotLogged.jsp"></jsp:include>
	
		<div id="parent">
			<div id="child">
				<c:forEach items="${requestScope.allPosts}" var="post" >
				
				<div>
					<h1><c:out value="${ post.description }">no description</c:out></h1><br>
					<img src="postpicServlet?postId=${ post.postId }" width="100px" height="100px">
				</div>
				
				<div id="tags">
				
				</div>
				
				<div id="namePoints">
					<p class="points">points: <c:out value="${ post.points }"></c:out></p>
					<p>user: <c:out value="${ post.user.username }"></c:out></p>
				</div>
				
				</c:forEach>
			</div>
		</div>
	</body>
</html>
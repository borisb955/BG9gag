<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post</title>
</head>
<body>

		<jsp:include page="headerNotLogged.jsp"></jsp:include>
		
		<div>
		<h1><c:out value="${ requestScope.post1.postId }">no description</c:out></h1>
		<c:forEach items="${ requestScope.post1.comments }" var="comment" >
				
				<c:out value="${ comment.comment }"></c:out>
		</c:forEach>
		</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${ sessionScope.post.description }"></c:out></title>
</head>
<body>
				<jsp:include page="headerLogged.jsp"></jsp:include>
		<div>
		<h1><c:out value="${ sessionScope.post.description }"></c:out></h1><br>
		<c:forEach items="${sessionScope.post.tags}" var="tag" >
						<a href=""> #<c:out value="${ tag.tagName }"></c:out></a>
		</c:forEach>
		<br>
		<img src="postpic?postUrl=${ sessionScope.post.postUrl }" width="50%" height=auto>
		<br>
		
		<span>Points:<c:out value="${ sessionScope.post.points }"></c:out></span>
		<h3>Comments:</h3>
			<form action="postComment" method="post">	
				<textarea name="commentText" rows="6" cols="50" maxlength="1000" style="resize:none;" placeholder="Comment here..."></textarea>
				<input type="submit" value="Comment">
			</form>
		<c:if test="${ sessionScope.post.comments.size()>0 }">
		<c:forEach items="${ sessionScope.post.comments }" var="comment" >
			<div>
				<h3><c:out value="${ comment.user.username }"></c:out>
				:DATE:<c:out value="${ comment.dateTime }"></c:out>
				:POINTS:<c:out value="${ comment.points }"></c:out></h3>
				<h1><c:out value="${ comment.comment }"></c:out></h1>
				</div>
		</c:forEach>
		</c:if>
		<c:if test="${ sessionScope.post.comments.size()==0 }">
		<h2>No comments yet...</h2>
		</c:if>
		</div>
		<form action="" method="post">
		<input type="submit" value="BACK"/>
		</form>
</body>
</html>
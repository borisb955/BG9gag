<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${ requestScope.postPage.description }"></c:out></title>
</head>
<body>
<jsp:include page="headerLogged.jsp"></jsp:include>
		
		<div>
		<h1><c:out value="${ requestScope.postPage.description }"></c:out></h1><br>
		<c:forEach items="${requestScope.postPage.tags}" var="tag" >
						<a href=""> #<c:out value="${ tag.tagName }"></c:out></a>
		</c:forEach>
		<br>
		<img src="postpicServlet?postId=${ requestScope.postPage.postId }" width="100px" height="100px">
		<br>
		<span>Points:<c:out value="${ requestScope.postPage.points }"></c:out></span>
		<h3>Comments:</h3>
			<form action="postComment" method="post">
					<input type="text" style="display:none" value="${requestScope.postPage.postId }" name="postId">
					<input type="text" style="display:none" value="${requestScope.postPage.user.id }" name = "userId">
				<textarea name="commentText" rows="10" cols="50" maxlength="1000" style="resize:none;" placeholder="Comment here..."></textarea>
				<input type="submit" value="Comment">
			</form>
		<c:if test="${ requestScope.postPage.comments.size()>0 }">
		<c:forEach items="${ requestScope.postPage.comments }" var="comment" >
				<h1><c:out value="${ comment.comment }"></c:out></h1>
		</c:forEach>
		</c:if>
		<c:if test="${ requestScope.postPage.comments.size()==0 }">
		<h2>No comments yet...</h2>
		</c:if>
		</div>
		<form action="" method="post">
		<input type="submit" value="BACK"/>
		</form>
</body>
</html>
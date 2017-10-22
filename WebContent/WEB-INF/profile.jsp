<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>My Profile</title>
	
	<style type="text/css">
		#myHeader{height: 200px; width: 100%; background-color: #2d2d2d; margin-top:0px; display: block;}
		.username{font-size: 200%;  text-align: center; color: white; margin: 10px;}
		.funnyCollection{font-size: 110%; text-align: center; color: #b5b5b5; margin:-22px; margin:0;}
		.img{width: 100px; height: 100px; display: block; margin: 0 auto;}
		#navigation{border-bottom: 3px ridge; width: 100%; margin-top: 10px;}
		.navButtons{display: inline-flex; margin-left: 70px; }
	</style>
</head>
	<body>
		<jsp:include page="headerLogged.jsp"></jsp:include>
		
		<div id="myHeader">
			<img class="img" src="avatar">
			<p class="username"><c:out value="${ sessionScope.user.username }"></c:out></p>
			<p class="funnyCollection">My funny collection</p>
		</div>
		
		<div id="navigation">
			<div class="navButtons">
				<form action="/overview">
					<input type="submit" value="OVERVIEW">
				</form>
				<form action="myposts">
					<input type="submit" value="POSTS">
				</form>
				<form action="/myupvotes">
					<input type="submit" value="UPVOTES">
				</form>
				<form action="/mycomments">
					<input type="submit" value="COMMENTS">
				</form>
			</div>
		</div>
	</body>
</html>
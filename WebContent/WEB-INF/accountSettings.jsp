<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style type="text/css">
		#container{width: 800px; margin: 40px auto 0 200px; position: relative; display: inline-flex; }
		#leftContainer{ padding-left: 0px; background-color: red;}
		#rightContainer{ margin-left: 200px;}
		.ulLeft{list-style-type: none; position: absolute;}
		.rightContainerHeader{font-size: 260%;}
	</style>
</head>
	<body>
		<jsp:include page="headerLogged.jsp"></jsp:include>
		<div id="container">
			<div id="leftContainer">
				<ul class="ulLeft">
					<li><a href="accountSettings">Account</a></li>
					<li><a>Profile</a></li>
					<li><a>Password</a></li>
				</ul>
			</div>
			
			<div id="rightContainer">
				<h1 class="rightContainerHeader">Account</h1>
				<form action="changeAccount" method="post">
					<p>change username</p>
					<input type="text" placeholder="${ sessionScope.user.username }" name="username">
					
					<p>change email</p>
					<input type="text" placeholder="${ sessionScope.user.email }" name="email">
					
					<p>hide upvotes</p>
					<input type="checkbox"  name="email"><br><br>
					
					<input type="submit">
				</form>	
			</div>
			
		</div>
	</body>
</html>
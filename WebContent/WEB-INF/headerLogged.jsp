<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style>
		body{background: white; margin: 0px;}
		#header{width: 98%; height: 26px; background: black; padding: 10px; text-align: left;}
		#logo img{margin-top:-10px; height: 45px; widght: 45px; float: left; border:}
		#loginReg{width: 180px; float: right; }
		#buttonsHeader{float: left; padding-left: 25px;}
		.header-links{color: gray; padding-left: 35px; text-decoration: none; font-weight: bold;}
		.upload-button{background: blue; cursor: pointer; border: none; font-size: 16px;}
		.button-links{color: white; text-decoration: none;}
		a:hover{color: white}
		
		
			/* Dropdown Button */
		.dropbtn {
		    background-color: white;
		    color: black;
		    font-size: 16px;
		    border: none;
		    cursor: pointer;
		}
		
		/* The container <div> - needed to position the dropdown content */
		.dropdown {
		    position: relative;
		    display: inline-block;
		}
		
		/* Dropdown Content (Hidden by Default) */
		.dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 100px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		/* Links inside the dropdown */
		.dropdown-content a {
		    color: black;
		    text-align: left;
		    padding: 5px 7px;
		    width: 120px;
		    text-decoration: none;
		    display: block;
		}
		
		/* Change color of dropdown links on hover */
		.dropdown-content a:hover {background-color: #f1f1f1}
		
		/* Show the dropdown menu on hover */
		.dropdown:hover .dropdown-content {
		    display: block;
		}
		
		
	</style>
</head>
		<div id="header">
		<div id="logo">
			<img src="https://upload.wikimedia.org/wikipedia/commons/9/97/9GAG_new_logo.svg" >
		</div>
		<div id="buttonsHeader">
			<a class="header-links" href="">Hot</a>
			<a class="header-links" href="">Trending</a>
			<a class="header-links" href="">Fresh</a>
			<a class="header-links" href="">GIF</a>
			<a class="header-links" href="">Video</a>
		</div>
		<div id="loginReg">
		
			
			<div class="dropdown">
			  <button class="dropbtn">Dropdown</button>
				<div class="dropdown-content">
					<img src="avatar">
			    	<button><a class="button-links" href="">My Profile</a></button>
			    	<button><a class="button-links" href="">Settings</a></button>
			    	<form action="logout" method="post">
			    		<button><input type="submit" value="Logout"/></button>
			    	</form>
				</div>
			</div>

			<button class="upload-button"><a class="button-links" href="upload">+ Upload</a></button>	
			
			
		</div>
	</div>
</html>
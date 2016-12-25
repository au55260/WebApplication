<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Login Page</title>
</head>
<body> <p>${loginError}</p>

<p>Hello login page </p>

<form action="login" method="post"> 
		<div>
			<div class="row">
				<label>UserName</label> <input placeholder="UserName" id="username" name="username">
			</div>

			<div class="row">
				<label>Password </label> <input placeholder="password"
					type="password" id="password" name="password">
			</div>

			<div class="row">
				<button id="loginButton">Submit</button>
				
			</div>
		</div>
	</form>
</body>
</html>
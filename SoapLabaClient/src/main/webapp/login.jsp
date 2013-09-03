<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Login</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/styles.css"/>">

</head>
<body>

	<div id="loginPane">

		<form action="<c:url value='j_spring_security_check' />" method='POST'>

			User <br />
			<input type='text' name='j_username' value=''> 
			<br />Password:
			<br />
			<input type='password' name='j_password' /> <br />
			<input name="submit" type="submit" value="submit" />
		</form>
		
		<br/>
		<s:a namespace="/serviceroom" action="registrationForm">registration</s:a>
		
	</div>

</body>
</html>



<!-- <table> -->
<!-- 				<tr> -->
<!-- 					<td>User:</td> -->
<!-- 					<td><input type='text' name='j_username' value=''></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>Password:</td> -->
<!-- 					<td><input type='password' name='j_password' /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td colspan='2'><input name="submit" type="submit" -->
<!-- 						value="submit" /></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
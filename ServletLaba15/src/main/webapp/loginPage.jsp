<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<link rel="stylesheet" type="text/css" href="resources/css/styles.css">

	<div class="loginFrame">


		<c:forEach items="${errors}" var="error">

			<c:out value="${error}" />
	<br/>
		</c:forEach>
<br/><br/>

		<form action="login.htm" method="POST">
			
					Login
					<br/>
					<input type="text" name="username">
				<br/><br/>
					Password
					<br/>
					<input type="password" name="password">
<br/><br/>
				
					<input type="submit" value="Submit"/> 
			

		</form>


	</div>
</body>
</html>
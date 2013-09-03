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
			<br />
		</c:forEach>
		<br />
		<br />

		<form  name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

			Login <br /> <input type="text" name="j_username"> <br />
			<br /> Password <br /> <input type="password" name="j_password">
			<br />
			<br /> <input type="submit" value="Submit" /> 
			<br/><br/>
			<a	href="<c:url value="registration.htm"/>">registration</a>

		</form>

	</div>
</body>
</html>
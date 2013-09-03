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

	
<div class="topLeft">
	<h3>Edit</h3>
</div>

<div class="topRight">
	<%@ include file="/WEB-INF/pages/topRight.jsp" %>
</div>

<div>
	

	<form method="POST" action="<c:url value="/editUser.htm"/>">
	
		<table class="tableEdit">
		<input type="hidden" name="id" value="${user.id}"/>
		
		<tr>
				<c:forEach items="${errors}" var="error">
					<c:out value="${error}" />
					<br />
				</c:forEach>
				<br />
				<br />
			</tr>
		
		<tr>
			<td>Login</td>
			<td><input type="text" name="login" value="${user.login}"  readonly /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" value="${user.password}" /></td>
		</tr>

		<tr>
			<td>E-mail</td>
			<td><input type="text" name="email" value="${user.email}" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><input type="text" name="firstName"
				value="${user.firstName}" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><input type="text" name="lastName" value="${user.lastName}" /></td>
		</tr>
		<tr>
			<td>Birth date</td>
			<td><input type="text" name="birthDate"
				value="${user.birthDateFormat}" /> (yyyy-MM-dd)</td>
		</tr>
		<tr>
			<td>Role</td>
			<td><select name="role">
					<c:forEach items="${roles}" var="role">
						<option value="${role.id}">${role.name}</option>
					</c:forEach>
			</select></td>
			
			
		</tr>
		<tr><td><input type="submit" value="Edit"></td></tr>
	</table>

<%-- 		<%@ include file="/WEB-INF/pages/formUser.jsp"%> --%>

		

	</form>
</div>
</body>
</html>
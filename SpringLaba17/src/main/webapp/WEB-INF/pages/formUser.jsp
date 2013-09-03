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
DELETE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	<table>
		<input type="hidden" name="id" value="${user.id}" />
		<tr>
			<td>Login</td>
			<td><input type="text" name="login" value="${user.login} 
			
			
			
			" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>Password again</td>
			<td><input type="password" name="passwordAgain" /></td>
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
				value="${user.birthDateFormat}" /></td>
		</tr>
		<tr>
			<td>Role</td>
			<td><select name="role">
					<c:forEach items="${roles}" var="role">
						<option value="${role.id}">${role.name}</option>
					</c:forEach>
			</select></td>
		</tr>
	</table>

</body>
</html>
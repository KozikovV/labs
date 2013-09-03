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
		<%@ include file="/WEB-INF/pages/topRight.jsp"%>
	</div>

	<div>


		<form:form modelAttribute="user">

			<form:hidden path="id" />

			<table class="tableEdit">

				<tr>
					<td>Login</td>
					<td><form:input path="login" readonly="true" /></td>
					<td><form:errors path="login" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="password" showPassword="true" /></td>
					<td><form:errors path="password" /></td>
				</tr>

				<tr>
					<td>E-mail</td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" /></td>
				</tr>
				<tr>
					<td>First name</td>
					<td><form:input path="firstName" /></td>
					<td><form:errors path="firstName" /></td>
				</tr>
				<tr>
					<td>Last name</td>
					<td><form:input path="lastName" /></td>
					<td><form:errors path="lastName" /></td>
				</tr>
				<tr>
					<td>Birth date (yyyy-MM-dd)</td>
					<td><form:input path="birthDate" /></td>
					<td><form:errors path="birthDate" /></td>
				</tr>
				<tr>
					<td>Role</td>
					<td><form:select path="role" items="${roles}">
							<form:option value="${user.role}"></form:option>
						</form:select></td>
					<td></td>
				</tr>

				<tr>
					<td><img src="<c:url value="/captcha-image.htm"/>" /></td>
					<td><form:input path="captcha" /></td>
					<td><form:errors path="captcha" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Edit"></td>
				</tr>

			</table>

		</form:form>
	</div>
</body>
</html>
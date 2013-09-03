<%@ include file="/WEB-INF/pages/include.jsp"%>

<form:form modelAttribute="user">

	<table class="tableEdit">

		<tr>
			<td>Login</td>
			<td><form:input path="login" /></td>
			<td><form:errors path="login" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><form:password path="password" /></td>
			<td><form:errors path="password" /></td>
		</tr>
		<tr>
			<td>Password again</td>
			<td><form:password path="passwordAgain" /></td>
			<td><form:errors path="passwordAgain" /></td>
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
			<td>Birth date  (yyyy-MM-dd)</td>
			<td><form:input path="birthDate" /></td>
			<td><form:errors path="birthDate" /></td>
		</tr>
		<tr>
			<td>Role</td>
			<td><form:select path="role" items="${roles}">
				</form:select></td>
			<td></td>
		</tr>

		<tr>
			<td><img src="<c:url value="/captcha-image.htm"/>" /></td>
			<td><form:input path="captcha" /></td>
			<td><form:errors path="captcha" /></td>
		</tr>

		<tr>
			<td><input type="submit" value="Add"></td>
		</tr>

	</table>

</form:form>
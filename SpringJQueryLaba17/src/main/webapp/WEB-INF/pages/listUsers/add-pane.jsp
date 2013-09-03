<%@ include file="/WEB-INF/pages/include.jsp"%>

<div id="add-pane" class="add-pane">

<%-- <c:url value="addUserJQ.htm"/> --%>
	<form action="#" id="add-form">
		<table>

			<tr>
				<td>Login</td>
				<td><input type="text" name="login" class="inputText"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" class="inputText"/></td>
			</tr>
			<tr>
				<td>Password again</td>
				<td><input type="password" name="passwordAgain"
					class="inputText" /></td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email" class="inputText" /></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><input type="text" name="firstName" class="inputText" /></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input type="text" name="lastName" class="inputText" /></td>
			</tr>
			<tr>
				<td>Birth date (yyyy-MM-dd)</td>
				<td><input type="text" name="birthDate" class="inputText" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><select name="role" id="role-select"></td>
			</tr>

			<tr>
				<td><img src="<c:url value="/captcha-image.htm"/>" /></td>
				<td><input type="text" name="captcha" class="inputText" /></td>
			</tr>

			<tr>
				<td></td>

					
					<td><input type="submit" value="Add" class="btn" id="ok"> 
					<input type="submit" value="Cancel" class="btn" id="cancel"></td>
			</tr>

		</table>
	</form>
</div>
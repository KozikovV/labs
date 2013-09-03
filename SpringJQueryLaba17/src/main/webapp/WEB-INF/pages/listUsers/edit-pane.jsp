<%@ include file="/WEB-INF/pages/include.jsp"%>

<div id="edit-pane">

	<form class="e-f" id="edit-form" action="<c:url value="/editUserJQ.htm"/>">
		<input type="hidden" name="id">
		<table class="editUsersTable">
			<tr>
				<td>Login</td>
				<td><input type="text" name="login" readonly="readonly"></td>
				<td>First name</td>
				<td><input type="text" name="firstName"></td>
				<td>Last name</td>
				<td><input type="text" name="lastName" value=""></td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email" value=""></td>
				<td>Password</td>
				<td><input type="password" name="password" value=""></td>
				<td>Birthday</td>
				<td><input type="text" name="birthDate" value=""></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><select name="role" id="role-select"></td>
				<td><a href="#" onclick="okEdit(this)" class="btn">OK</a></td>
				<td><a href="#" onclick="cancel(this)" class="btn">Cancel</a></td>

			</tr>

		</table>
	</form>
</div>
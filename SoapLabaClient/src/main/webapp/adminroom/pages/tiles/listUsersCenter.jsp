<%@ include file="/include.jsp"%>

<div class="list-users-pane" id="listUsersPane">
	<table class="user-table">
		<thead>
			<tr>
				<th>Login</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Age</th>
				<th>Role</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>

			<s:iterator value="users" status="user">

				<s:url var="edit" action="editUserForm">
					<s:param name="login" value="login" />
				</s:url>

				<s:url var="remove" action="removeUser">
					<s:param name="id" value="id" />
				</s:url>

				<tr>
					<td><label id="login"><s:property value="login" /></label></td>
					<td><label id="firstName"><s:property
								value="firstName" /></label></td>
					<td><label id="lastName"><s:property value="lastName" /></label></td>
					<td><label id="age"><s:property value="age" /></label></td>
					<td><label id="role"><s:property value="role.name" /></label></td>
					<td><s:a href="%{edit}"> edit </s:a> <s:a href="%{remove}"> remove </s:a></td>
					<td class="hidden"><label id="id"><s:property
								value="id" /></label> <label id="email"><s:property
								value="email" /></label> <label id="birthDate"><s:property
								value="birthDate" /></label></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

<div id="userData" class="user-data">
	<table>
		<tr>
			<td>Id</td>
			<td><label id="id"></label></td>
		</tr>
		<tr>
			<td>Login</td>
			<td><label id="login"></label></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><label id="email"></label></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><label id="firstName"></label></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><label id="lastName"></label></td>
		</tr>
		<tr>
			<td>Birthday</td>
			<td><label id="birthDate"></label></td>
		</tr>
		<tr>
			<td>Age</td>
			<td><label id="age"></label></td>
		</tr>
		<tr>
			<td>Role</td>
			<td><label id="role"></label></td>
		</tr>
	</table>

</div>
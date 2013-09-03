<%@ include file="/include.jsp"%>

<div class="control-pane">

	<table id="bottons">
		<tbody>
			<tr>
				<td><label id="title">Users list</label></td>
			</tr>
			<tr>
				<td><label id="youAre">You are: <security:authentication
							property="principal.username" /></label></td>
			</tr>
			<tr>
				<td><s:a action="addUserForm">Add user</s:a></td>
			</tr>
			<tr>
				<td><s:a action="listUsers">List users</s:a></td>
			</tr>
			<tr>
				<td><s:a namespace="/serviceroom" action="logout">logout</s:a></td>
			</tr>
		</tbody>
	</table>

</div>
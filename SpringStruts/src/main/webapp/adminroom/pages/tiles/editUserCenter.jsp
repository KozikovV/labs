<%@ include file="/include.jsp"%>

<div id="formPane">
	<s:form action="editUser" method="post">
		<s:hidden name="userBean.id" />
		<s:textfield name="userBean.login" label="Login" readonly="true" />
		<s:password name="userBean.password" label="Password"/>
		<s:textfield name="userBean.email" label="E-mail" />
		<s:textfield name="userBean.firstName" label="First name" />
		<s:textfield name="userBean.lastName" label="Last name" />
		<s:textfield name="userBean.birthDate" label="Birthday" title="for example 2001-02-14"/>

		<s:select list="roles" label="Role" name="userBean.role"
			listKey="id+\":\"+name" listValue="name"
			value="userBean.role.id+\":\"+userBean.role.name">
		</s:select>

		<s:submit />
	</s:form>
</div>
<%@ include file="/include.jsp"%>


<div id="formPane">

	<s:form action="addUser" method="post" tabindex="false">

		<s:textfield name="userBean.login" label="Login" />
		<s:password name="userBean.password" label="Password" />
		<s:password name="userBean.passwordAgain" label="Password again" />
		<s:textfield name="userBean.email" label="E-mail" />
		<s:textfield name="userBean.firstName" label="First name" />
		<s:textfield name="userBean.lastName" label="Last name" />
		<s:textfield name="userBean.birthDate" label="Birthday" title="for example 2001-02-14"/>
		<s:select list="roles" label="Role" name="userBean.role"
			listKey="id+\":\"+name" listValue="name">
		</s:select>
		<s:submit value="OK"/>

	</s:form>

</div>
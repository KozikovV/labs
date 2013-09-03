<%@ include file="/include.jsp"%>

<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<c:url value="css/styles.css"/>">

</head>
<body>

<div id="regPane">
	<s:form action="registration" method="post">

		<s:textfield name="userBean.login" label="Login" />
		<s:textfield name="userBean.password" label="Password" />
		<s:textfield name="userBean.passwordAgain" label="Password again" />
		<s:textfield name="userBean.email" label="E-mail" />
		<s:textfield name="userBean.firstName" label="First name" />
		<s:textfield name="userBean.lastName" label="Last name" />
		<s:textfield name="userBean.birthDate" label="Birthday"
			title="for example 2001-02-14" />
		<s:select list="roles" label="Role" name="userBean.role"
			listKey="id+\":\"+name" listValue="name">
		</s:select>
		<s:textfield name="userBean.captcha" label="Captcha" />
		
		<img src="<c:url value="/captchaImage.action"/>">
		<s:submit />
	</s:form>
</div>
</body>
</html>
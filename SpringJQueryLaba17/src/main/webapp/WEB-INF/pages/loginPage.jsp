<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Login</title>



<link rel="stylesheet" type="text/css" media="all" href="css/styles.css">
<link rel="stylesheet" type="text/css" media="all"
	href="css/bootstrap.css">


<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/addForm.js"></script>

<script type="text/javascript" src="js/registration.js"></script>

<style type="text/css">

.h{
	max-height: 1000px;
	max-width: 1000px;
	width: ;
}

</style>

<script type="text/javascript" src="js/modal.js"></script>
</head>
<body>

	<div class="loginFrame">

		<c:forEach items="${errors}" var="error">

			<c:out value="${error}" />
			<br />
		</c:forEach>
		<br /> <br />

		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>

			<br> Login <br> <input type="text" name="j_username">
			<br /> <br /> Password <br /> <input type="password"
				name="j_password"> <br /> <br /> <input type="submit"
				value="Submit" /> <br />

		</form>

		<br> <a href="#registrationModal" data-toggle="modal"
			class="btn" onclick="registration()">Registration</a> <br> <br> <br>



	</div>


	<div id="registrationModal" class="modal hide fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<header class="modal-header"> <a href="#" class="close"
			data-dismiss="modal">x</a>
		<h3>
			Registration
		</h3>
		</header>
		
		<div class="modal-body h">
			<div id="add-pane">
				<form action="#" id="add-form">
					<table>

						<tr>
							<td>Login</td>
							<td><input type="text" name="login" class="inputText" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" class="inputText" /></td>
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
		</div>
		<footer class="modal-footer"> </footer>



	</div>
</body>
</html>
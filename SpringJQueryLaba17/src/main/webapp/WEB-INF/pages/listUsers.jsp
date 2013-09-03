<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib prefix="t" uri="com.nixsolutions.tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/styles.css">

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>

<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/addForm.js"></script>


<script type="text/javascript" src="js/listUsers.js"></script>
<script type="text/javascript" src="js/validation.js"></script>

</head>
<body>



	<div class="topLeft">
		<h2>List of users</h2>
	</div>

	<div class="topRight">
		<%@ include file="/WEB-INF/pages/topRight.jsp"%>
	</div>

	<div class="center">
		<a href="#" onclick="add()" class="btn" id="addUserLink">Add new user</a>
		<%@ include file="/WEB-INF/pages/listUsers/add-pane.jsp"%>
		<t:table users="${users}" cssClass="usersTable" />
		<%@ include file="/WEB-INF/pages/listUsers/edit-pane.jsp"%>

	</div>


</body>
</html>
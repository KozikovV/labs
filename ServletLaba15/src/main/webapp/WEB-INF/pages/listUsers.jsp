<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib prefix="t" uri="com.nixsolutions.tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	

	<link rel="stylesheet" type="text/css" href="resources/css/styles.css">


<div class="topLeft">
	<a href="<c:url value="addNewUserForm.htm"/>">Add new user</a>
</div>

<div class="topRight">
	<%@ include file="/WEB-INF/pages/topRight.jsp" %>
</div>

<div class="center">
	<t:table users="${allUsers}" cssClass="usersTable" />
</div>


</body>
</html>

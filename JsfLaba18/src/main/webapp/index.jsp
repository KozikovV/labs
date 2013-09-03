<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>


<head>

</head>


<body>
ind
	<%
		if (request.isUserInRole("ROLE_ADMIN")) {
	%>
	<jsp:forward page="listUsers.jsf" />
	<%
		}
	%>

	<%
		if (request.isUserInRole("ROLE_USER")) {
	%>
	<jsp:forward page="hello.jsf" />
	<%
		}
	%>

	

</body>
</html>

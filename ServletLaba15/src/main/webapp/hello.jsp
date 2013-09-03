<%@page import="com.nixsolutions.web.filter.Principal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<body>

<link rel="stylesheet" type="text/css" href="resources/css/styles.css" >

<div class="helloPanel">
	<h2>
		Hello,
		<%= ((Principal)request.getSession().getAttribute(Principal.PRINCIPAL)).getName() %>!
	</h2>
	<br />
	<br /> Click
	<a href="<c:url value="logout.htm"/>">here</a> to logout.
</div>

</body>
</html>

<%@ include file="/WEB-INF/pages/include.jsp" %>


<html>
<body>

<link rel="stylesheet" type="text/css" href="resources/css/styles.css" >

<div class="helloPanel">
	<h2>
		Hello,
		<%= request.getRemoteUser() %> !
	</h2>
	<br />
	<br /> Click
	<a href="<c:url value="j_spring_security_logout" />" > here</a> to logout.
</div>

</body>
</html>

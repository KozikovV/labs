<%@ include file="/include.jsp"%>

<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<c:url value="css/styles.css"/>">

</head>
<body>

	<div id="helloPane">
		<h2>
			Hello
			<security:authentication property="principal.username" />
			!
		</h2>
		<br /> click
		<s:a namespace="/serviceroom" action="logout">here</s:a>
		to logout

	</div>

</body>
</html>
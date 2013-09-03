<%@ include file="/include.jsp"%>

<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<c:url value="css/styles.css"/>">


<script type="text/javascript"
	src="<c:url value="/js/jquery-1.9.1.js"/>" /></script>


<script type="text/javascript" src="<s:url value="js/listUsers.js"/>"></script>

</head>
<body>

	<tiles:insert attribute="controlPane" />
	<tiles:insert attribute="center" />

</body>
</html>
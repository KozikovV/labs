<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>

	

	<c:url var="action" value="/send.htm" />
	<form action="${action}" method="POST">

		Send : <input name="message"> <input type="submit" value="  Send  " />

	</form>



</body>
</html>
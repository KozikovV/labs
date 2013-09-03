<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	
	${messages}
	<br/>

	Rceive:
	<br/>
	
	<c:forEach items="${messages}" var="m"> 
		<br/> --> ${m}
	</c:forEach>


</body>
</html>
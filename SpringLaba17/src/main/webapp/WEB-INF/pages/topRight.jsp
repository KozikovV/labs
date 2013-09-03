<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





Admin
<%= request.getRemoteUser() %>

(
<a href="<c:url value="j_spring_security_logout" />" > logout </a>
)

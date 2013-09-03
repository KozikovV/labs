<%@page import="com.nixsolutions.web.filter.Principal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





Admin
<%=((Principal) request.getSession().getAttribute(
                    Principal.PRINCIPAL)).getName()%>!

(
<a href="<c:url value="logout.htm"/>">logout</a>
)

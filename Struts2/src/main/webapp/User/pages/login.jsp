<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head></head>
<body>
<h1>Struts 2 Hello World Example</h1>

<s:form action="Welcome">
    <s:textfield name="myEnum" label="myEnum"/>
    <%--<s:textfield name="field" label="field"/>--%>
    <%--<s:textfield name="bean.field" label="bean.field"/>--%>
    <%--<s:textfield name="bean.subBean.field" label="bean.subBean.field"/>--%>
    <%--<s:textfield name="beans[0].field" label="beans[0].field"/>--%>
    <%--<s:textfield name="beans[1].field" label="beans[1].field"/>--%>
	<%--<s:textfield name="username" label="Username"/>--%>
	<%--<s:password name="password" label="Password"/>--%>
	<s:submit/>
</s:form>

</body>
</html>
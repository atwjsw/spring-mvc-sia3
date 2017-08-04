<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>You are welcome to spring security!</h1>
    <spring:url var="logoutUrl" value="/static/j_spring_security_logout" />
    <h2><a href="${logoutUrl}">LOGOUT</a></h2>
</head>
<body>

</body>
</html>

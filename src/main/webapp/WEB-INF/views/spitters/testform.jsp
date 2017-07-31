<%--
  Created by IntelliJ IDEA.
  User: wenda
  Date: 7/30/2017
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test form</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data">
<%--<form method="POST">--%>
    <input name="fullName" type="text" size="15"/><br/>
    <input name="username" type="text" size="15"/><br/>
    <input name="password" type="password" size="15"/><br/>
    <input name="email" type="text" size="15"/><br/>
    <input name="updateByEmail" type="checkbox"/><br/>
    <input name="commit" type="submit" value="I accept. Create my account."/>

</form>

</body>
</html>

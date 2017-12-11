<%--
  Created by IntelliJ IDEA.
  User: yz
  Date: 2017/10/7
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>success</h1>
    <h2>time : ${requestScope.time}</h2>
    <h3>name : ${requestScope.name}</h3>
    <h3>name : ${sessionScope.name}</h3>
    <br>
    <fmt:message key="i18n.username">username</fmt:message>
    <fmt:message key="i18n.password">password</fmt:message>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: yz
  Date: 2017/12/10
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty requestScope.students }">
    没有任何学生信息.
</c:if>
<c:if test="${!empty requestScope.students }">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Age</th>
        </tr>

        <c:forEach items="${requestScope.students }" var="student">
            <tr>
                <td>${student.id }</td>
                <td>${student.name }</td>
                <td>${student.age }</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

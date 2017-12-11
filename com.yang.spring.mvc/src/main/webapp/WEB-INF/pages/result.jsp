<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>提交的学生信息如下 -</h2>
<table>
    <tr>
        <td>姓名：</td>
        <td>${requestScope.name}</td>
    </tr>
    <tr>
        <td>年龄：</td>
        <td>${requestScope.age}</td>
    </tr>
    <tr>
        <td>编号：</td>
        <td>${requestScope.id}</td>
    </tr>
</table>
</body>
</html>
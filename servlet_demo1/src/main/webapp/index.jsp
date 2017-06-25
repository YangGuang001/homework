<span style="font-size:18px;"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
response.sendRedirect("login.html");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜鸟教程(runoob.com)</title>
</head>
<body>
<form action="/servlet_demo1" method="POST" target="_blank">
    <input type="checkbox" name="runoob" checked="checked"/> 菜鸟教程
    <input type="checkbox" name="runoob" checked="checked" /> 菜鸟教程
    <input type="checkbox" name="google"  /> Google
    <input type="checkbox" name="taobao" checked="checked" /> 淘宝
    <input type="submit" value="选择站点" />
</form>
</body>
</html>

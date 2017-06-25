<span style="font-size:18px;"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
      <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.6.2.min.js" ></script>
    <script type="text/javascript">
     function  sub(){
         $.ajax({
             dataType:"json",    //数据类型为json格式
             contentType: "application/x-www-form-urlencoded; charset=utf-8",
             type:"GET",
             url:"/testJson",
             statusCode: {404: function() {
                 alert('page not found'); }
             },
             success:function(data,textStatus){
                 $("#sp").html("<table border='1'><tr><td>序号</td><td>姓名</td><td>年龄</td></tr>"+
                     "<tr><td>"+data.people[0].id+"</td><td>"+data.people[0].name+"</td><td>"+data.people[0].age+"</td>"+
                     "<tr><td>"+data.people[1].id+"</td><td>"+data.people[1].name+"</td><td>"+data.people[1].age+"</td></tr></table>");
             }
         });
     }
    </script>
  </head>
  <body>
    This is my JSP page. <br>
       <input type="button" value="点击获取结果" id="btn" onclick="sub()">
    <br>
    result :
    <span id="sp"></span>


  </body>
</html>
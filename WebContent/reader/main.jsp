<%--
  Created by IntelliJ IDEA.
  User: Creams
  Date: 2017/12/1
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css"  type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<h1 align="center">欢迎进入图书馆</h1>
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("reader") == null){
    	out.println("nihao");
        response.sendRedirect("/Library/readerLogin.jsp");
    }
%>
<jsp:include page="readernav.html"/>
</body>
</html>

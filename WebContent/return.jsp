<%--
  Created by IntelliJ IDEA.
  User: Creams
  Date: 2018/1/17
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/returnpage.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/return.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<html>
<head>
    <title>图书归还</title>
</head>
<body>
<h1 align="center">欢迎进入图书馆管理系统</h1>
<jsp:include page="nav.html"/>
</body>
<div class="returninfo">
    <div class="title">
        请输入读者学号
    </div>
    <form action="ReaderAction" onsubmit="return errorsubmit()">
    	<input type="hidden" name="action" value="GetBorrowListById"/>
        <div>
            <span class="infotitle">读者学号：</span><input type="text" onkeyup="readercheck()" name="readerid" id="readerid"><span id="readeridcheck" class="error"></span><br/>
        	<span class="infotitle">借阅时间：</span><input type="text" name="borrowtime" id="borrowtime">
        	<br/><span>(*请按照读者的借阅时间来还书*)</span>
        </div>
        <div class="button">
            <button type="submit" class="btn btn-success">提交</button>
            <button type="reset" class="btn btn-default">重填</button>
        </div>
    </form>
</div>
</html>

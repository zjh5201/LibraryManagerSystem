<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Log" %><%--
  Created by IntelliJ IDEA.
  User: Creams
  Date: 2018/1/16
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<html>
<head>
    <title>图书借进归还日志</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
	out.println(request.getParameter("borrowtime"));
    if(session.getAttribute("reader") == null){
        response.sendRedirect("/Library/index.jsp");
    }
%>
<script>
<script>

function get(par){
    //获取当前URL
    var local_url = document.location.href;
     
    //截取get字符串
    var getstr = local_url.substr(local_url.indexOf('?')+1)
     
    //解析成get数组
    var get = getstr.split('&')
     
    //查找要找到参数（par）
    for(var i in get){
        if(get[i].indexOf(par+'=')>=0){
            return get[i].replace(par+'=','');
        }
    }
     
    //如果找不到返回false
    return false;
}
</script>
<h1 align="center">欢迎进入图书馆管理系统</h1>
<jsp:include page="readernav.html"/>
<h5 style="margin-left:50px;margin-top:20px;">续借天数</h5>
<form action="/Library/IOAction" method="get" id="myForms">
	<input type="hidden" name="action" value="xujie"/>
	<input type="hidden" name="borrowtime" value="<%=request.getParameter("borrowtime") %>"/>
	<div class="col-sm-2 form-group" style="margin-left:30px;">
	<select class="form-control" name="days">
		<option value="7">7</option>
  		<option value="14">14</option>
	</select>
	<button id="loginbutton" class="btn btn-warning" onclick="submi()" style="margin-top:20px;">续借</button>
	</div>
	<script>
		function submi(){
			var form = document.getElementById("myForms");
			form.submit();
		}
	</script>
</form>


</body>
</html>
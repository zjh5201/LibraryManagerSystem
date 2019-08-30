<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Log" %>
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
    if(session.getAttribute("reader") == null){
        response.sendRedirect("/Library/readerLogin.jsp");
    }
%>
<h1 align="center">欢迎进入图书系统</h1>
<jsp:include page="readernav.html"/>
	<div style="width:500px;height:550px;margin:30px auto;">
		<p style="font-size:25px;font-weight:bold;font-family:'微软雅黑'">我的信息</p>
		<form action="ReaderAction" method="get" id="myForm">
		<input type="hidden" name="action" value="editInfo"/>
		<div class="form-group" >
			用户名：<input text="text" value="${reader.username }" readonly="readonly" class="form-control"/>
		</div>
		<div class="form-group" >
			姓&nbsp;&nbsp;&nbsp;名：<input type="text" value="${reader.name }" name="name"  class="form-control"/>
		</div>
		<div class="form-group" >
			密&nbsp;&nbsp;&nbsp;码：<input type="text" value="${reader.password }" name="password"  class="form-control"/>
		</div>
		<div class="col-sm-3 form-group" style="margin-left:-15px;">
         	性&nbsp;&nbsp;&nbsp;别：<select class="form-control" name="sex" value="${reader.sex }">
		  		<c:choose>
		  			<c:when test="${reader.sex eq '男' }">
		  				<option value="男" selected="selected">男</option>
		  				<option value="女">女</option>
		  			</c:when>
		  			<c:otherwise>
		  				<option value="男" >男</option>
		  				<option value="女" selected="selected">女</option>
		  			</c:otherwise>
		  		</c:choose>
			</select>
        </div>
		<div class="form-group" >
			邮&nbsp;&nbsp;&nbsp;箱：<input type="text" value="${reader.mail }" name="mail"  class="form-control"/>
		</div>
		<div class="form-group" >
			电&nbsp;&nbsp;&nbsp;话：<input type="text" value="${reader.tel }" name="tel"  class="form-control"/>
		</div>
		<div class="form-group" >
			年&nbsp;&nbsp;&nbsp;级：<input type="text" value="${reader.grade }" name="grade"  class="form-control"/>
		</div>
		<div class="form-group" >
			班&nbsp;&nbsp;&nbsp;级：<input type="text" value="${reader.classnum }" name="classnum"  class="form-control"/>
		</div>
		<button id="loginbutton" class="btn btn-primary" onclick="editSubmit()" style="width:80px;">修改</button>
		</form>
		<script>
			window.onload = function(){
				function editSubmit(){
					var myForm = document.getElementById("myForm");
					myForm.submit();
				}
			}
		</script>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/readerloginScript.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width:800px;height: 800px;margin:30px auto;">
<form action="ReaderLogin" method="post" id="myForm">
<input type="hidden" name="action" value="regist"/>
<div class="loginform">
    <div class="col-md-12 column">
        <h2>读者注册</h2><br>
        <div class="form-group">
            <input type="text" class="form-control" id="usernameinput" name="username" placeholder="用户名"/>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="passwordinput" name="password"  placeholder="密码"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="nameinput" name="name" value="${reader.name }" placeholder="姓名"/>
        </div>
        <div class="col-sm-2 form-group" style="margin-left:-15px;">
         	<select class="form-control" name="sex" value="${reader.sex }">
		  		<option value="男">男</option>
		  		<option value="女">女</option>
			</select>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="nameinput" value="${reader.mail }" name="mail" placeholder="邮箱"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="nameinput" value="${reader.tel }" name="tel" placeholder="电话"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="nameinput" value="${reader.grade }" name="grade" placeholder="年级"/>
        </div>
        <div class="form-group">
           	<input type="text" class="form-control" id="nameinput" value="${reader.classnum }" name="classnum" placeholder="班级"/>
        </div>
        <button id="loginbutton" class="btn btn-warning" onclick="registerCheck()">注册</button>
        <span class="errorsubmit" id="checkinfo">${msg }</span>
    	</div>
	</div>
	</form>
</div>
</body>
</html>
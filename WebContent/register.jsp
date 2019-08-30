<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="${pageContext.request.contextPath}/css/demo.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js" ></script>
    <script src="${pageContext.request.contextPath}/js/jquery.ffform.js"></script>
</head>
<body class="bounceIn animated">
    <section id="getintouch" class="rotateInUpLeft animated">
        <div class="container" style="border-bottom: 0;">
            <h1 style="color:black;">
                <span>食品安全知识系统注册</span>
            </h1>
        </div>
        <div class="container">
        	<p style="color:red;">${error }</p>
            <form class="contact" action="user/regist.action" method="post" id="form">
            <div class="row clearfix">
                <div class="lbl">
                    <label for="username">
                        用户名：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="username" name="username" 
                        placeholder="(张三)">
                </div>
            </div>
            <br/>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="password">
                        密码：</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="password" name="password" 
                       placeholder="***">
                </div>
            </div>
			<div class="row clearfix">
                <div class="lbl">
                    <label for="repassword">
                        确认密码：</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="repassword" name="repassword"
                         placeholder="">
                </div>
            </div>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="email">
                        手机号：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="phone" name="phone"
                        placeholder="TEL">
                </div>
            </div>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="subject">
                        性别：</label>
                </div>
                <div class="ctrl">
                    <input type="radio" name="sex" id="subject" value="男" checked="checked">男
					<input type="radio" name="sex" id="subject" value="女">女
                </div>
            </div>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="message">
                        民族：</label>
                </div>
				<div class="ctrl">
					<select style="width:100px;height:30px;" name="nation">
						<option value="汉族">汉族</option>
						<option value="回族">回族</option>
						<option value="维吾尔族">维吾尔族</option>
						<option value="藏族">藏族</option>
						<option value="其他">其他</option>
					</select>
				</div>
		
            </div>
            <div class="row  clearfix">
                <div class="span10 offset2">
                    <input type="submit" name="submit" id="submit" class="submit" value="注   册">
                </div>
            </div>
            </form>
        </div>
    </section>
</body>
</html>
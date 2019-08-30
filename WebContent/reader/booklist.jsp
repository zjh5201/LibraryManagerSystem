<%@ page import="Dao.BookDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: Creams
  Date: 2017/12/2
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("reader") == null){
        response.sendRedirect("/Library/readerLogin.jsp");
    }
    ArrayList<Book> booklist = (ArrayList<Book>)request.getAttribute("allbooklist");
%>
<html>
<head>
    <title>图书管理</title>
    	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<h1 align="center">欢迎进入图书馆管理系统</h1>
<jsp:include page="readernav.html"/> 
<script>
	function select(){
		var selectForm = document.getElementById("selectForm");
		selectForm.submit();
	}
</script>
<div class="form-group" style="width:400px;height:100px;margin-left:20px;">
	<form action="BookAction" id="selectForm">
	<input type="hidden" name="user" value="1"/>
	<input type="hidden" name="action" value="getall"/>
     <h5 style="margin-top:20px;">按书名查询：</h5><input type="text" class="form-control" name="bookname" style="width:200px;" placeholder="书名"/>
   <button id="loginbutton" class="btn btn-warning" style="width:80px;margin-top:4px;" onclick="select()">查询</button>
	</form>
</div>
<table class="table">
    <thead>
    <tr>
        <th style="text-align:center;">书本编号</th>
        <th style="text-align:center;">书名</th>
        <th style="text-align:center;">作者</th>
        <th style="text-align:center;">出版社</th>
        <th style="text-align:center;">价格</th>
        <th style="text-align:center;">类目</th>
        <th style="text-align:center;">库存总量</th>
        <th style="text-align:center;">借出数量</th>
        <th style="text-align:center;">剩余数量</th>
        <th style="text-align:center;">所在位置(柜号)</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(booklist!=null && booklist.size() > 0)
        {
            for(int i = 0; i < booklist.size(); i++)
            {
                Book b = booklist.get(i);
    %>
    <tr>
        <td style="text-align:center;"><%=b.getId()%></td>
        <td style="text-align:center;"><a href="BookAction?action=querybookbyid&id=<%=b.getId()%>&next=r_check"><%=b.getName()%></a> </td>
        <td style="text-align:center;"><%=b.getAuthor()%></td>
        <td style="text-align:center;"><%=b.getPublisher()%></td>
        <td style="text-align:center;"><%=b.getPrice()%></td>
        <td style="text-align:center;"><%=b.getCategory()%></td>
        <td style="text-align:center;"><%=b.getStore()%></td>
        <td style="text-align:center;"><%=b.getLend()%></td>
        <td style="text-align:center;"><%=b.getRemain()%></td>
        <td style="text-align:center;"><%=b.getLocation()%></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

</body>
</html>

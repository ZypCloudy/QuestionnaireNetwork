<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="myTools.DisplayObject" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>全部问卷</title>
	<link rel="stylesheet" href="../css/questionnaire.css">
	<link href="../css/public.css" rel="stylesheet" />
	   	<link href="../css/bootstrap.css" rel="stylesheet" />
    <link rel="icon" href="../images/title.ico" type="image/x-icon" />
</head>
<body>
 <div class="list_head clearfix">
     <h1>
         <a href="../index.jsp">
             <img src="../images/logo.png"  alt="问卷网_领先的网络调查平台，问卷调查，报名表、考试测验" />
         </a>
     </h1>
     <div class="head_nav">
         <a href="../index.jsp">主 页</a>
         <a href="../views/allQuestionnaire.jsp">问卷调查</a>
         <a href="#">模板库</a>
     </div>
     <div class="userHead">
         <div class="userNavHov">
			<%
				String sessionValues=(String)request.getSession().getAttribute("userName");  
				if(sessionValues!=null){
			%>    
             <div class="userPortrait">
                 <img  class="img"src="../images/portrait-default.png" />
                 <span></span>
             </div>          
             <div class="userInfo">
 				<p class="userName" title=""><%=sessionValues %></p>
             </div>
<!--              <ul class="userNav">
                 <li><a href="#" onclick=""><i class="icon bbnot"></i>退出</a></li>
             </ul> -->
             <%} else{%>
             <div class="userInfo">
                 <a href="../index.jsp" class="userName" title="">亲，请登录!!!</a>
             </div>             
             <%} %>
         </div>
     </div>
 </div>
 		<div class="input-group">
			<input id="search" type="text" class="form-control">
			<span onclick="SearchQuertionnaire()" class="input-group-addon">搜索</span>
		</div>
	<div class="list">
		<table id="proj_list" class="table_list" width="100%" cellspacing="0" cellpadding="0">
		</table>
		<div id="pagenum" class="pagenum">
		</div>
	</div>
	<script src="../js/jquery-2.2.3.min.js"></script>
	<script src="../js/allQuertionnaire.js"></script>
</body>
</html>

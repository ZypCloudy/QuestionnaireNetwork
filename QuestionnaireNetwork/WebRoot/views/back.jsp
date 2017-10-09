<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>问卷网_领先的网络调查平台，问卷调查，报名表、考试测验</title>
    <meta charset="utf-8" />
    <link rel="icon" href="../images/title.ico" type="image/x-icon" />
    <link href="../css/list.css" rel="stylesheet" />
    <link href="../css/public.css" rel="stylesheet" />
	<link href="../css/back.css" rel="stylesheet" />
</head>
<body>
<%
	String sessionValues=(String)request.getSession().getAttribute("userName");  
%>
<%
	Object obj = session.getAttribute("userId");
	if(obj==null){
// 		out.print("你没有登录");
		response.sendRedirect("../index.jsp");
	}
%>

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
         </div>
     </div>
 </div>
   <a class="list_notice_adv" style="display: block; width: 100%; height: 74px; cursor: pointer; background: url(../images/biye2017_list_img.jpg) repeat;">
   <span style="display:block; width: 100%; height: 74px; background: url(../images/biye2017_list_img.jpg) no-repeat center top;">
       </span>
   </a>

     <div class="list_nr">
         <div class="listBox hzBox" style="display: block;width: 1210px;margin: 0px auto;">
             <ul class="clearfix ul_list" id="listHz">
             
             </ul>
         </div>

     </div>
  
<script src="../js/jquery-2.2.3.min.js"></script>
<script src="../js/back.js"></script>
</body>
</html>


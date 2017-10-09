<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>问卷设计</title>
	<meta charset="utf-8" />
    <link href="../css/create.css" rel="stylesheet" />
    <link rel="icon" href="../images/title.ico" type="image/x-icon" />
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
	<div class="head_nav">
		<a href="../views/back.jsp" class="back">返回</a>
		<div class="nav">
			<a href="" class="create-paper active">创建全新问卷</a>
			<a href="" class="quote">引用问卷模板</a>
		</div>
	</div>
	<div class="main">
	     <div class="box step_one tabs_content">
	         <form id="form1" method="get" action="/QuestionnaireNetwork/servlets/CreateServlet">
	             <h2>问卷标题</h2>
	             	<input type="hidden" name="questionType" value="createPaper"/>
	             	<input class="title" name="title" id="title">
	             	<input type="hidden" id="type"  name="type" />
	             <h2>选择类别</h2>
	             <ul class="ssort clearfix">
	                 <li class="survey_0 active" id="0">
	                     <a href="javascript:;" >
	                     	<img alt="" src="../images/selected.png">
	                     </a>
	                 </li>
	                 <li class="survey_1" id="1">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_2" id="2">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_3" id="3">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_4" id="4">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_5" id="5">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_6" id="6">
	                     <a href="javascript:;"></a>
	                 </li>
	                 <li class="survey_7" id="7">
	                     <a href="javascript:;"></a>
	                 </li>
	             </ul>
	             <div class="btn_create">
	                 <div class="btn1" onclick="CreatePaper()">从空白创建</div>
	                 <a class="report_bt" href="javascript:;">使用模板</a>
	             </div>
	         </form>
	     </div>
	 </div>
    <script src="../js/jquery-2.2.3.min.js"></script>
	<script src="../js/create.js"></script>
</body>
</html>


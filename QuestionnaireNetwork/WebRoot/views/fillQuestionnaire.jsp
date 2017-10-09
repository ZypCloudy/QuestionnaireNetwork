<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="myTools.DisplayObject" %>
<%@ page import="entity.*" %>
<%@ page import="service.GetData" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>填写设计</title>
	<meta charset="utf-8" />
    <link href="../css/create.css" rel="stylesheet" />
    <link rel="icon" href="../images/title.ico" type="image/x-icon" />
</head>
<body>
<%
	String sessionValues=(String)request.getSession().getAttribute("userName"); 
%>
	<div class="head_nav">
		<a href="../views/allQuestionnaire.jsp" class="back">返回</a>
		<div class="nav">
			<a href="../index.jsp" >主页</a>
			<a href="../views/allQuestionnaire.jsp" >问卷调查</a>
			<a href="#" >模板库</a>
		</div>
	</div>

    <div class="create" style="margin-top: 110px;">
		<%
		GetData getData = new GetData(); 
		
		Paper paper = new Paper();
   		String paperId = request.getAttribute("paperId").toString();
		ArrayList<Object>  papers = new ArrayList<Object>();
		papers.addAll(getData.getObjById(paper, paperId));
		
		Question question = new Question();
		ArrayList<Object>  questions = new ArrayList<Object>();

		questions.addAll(getData.getObjByForeignId(question, "paper", paperId));
		%>
		<div class="header">
		    <div class="title">
		    	<span>标题：</span>	
		    	<span>
		    	<%
					for (Object object : papers) {
						Class<?> clazz = object.getClass();
						Object[] fields = DisplayObject.getFields(object,clazz);
				%>
				<input type="hidden" id="paperId" value ="<%=fields[0].toString()%>">	
				<%	
						out.print(fields[2].toString());
					}
				%>
		    	</span>
		    </div>
		    <div class="tip">欢迎参加本次问卷</div>
		</div>
		
		<div class="counter">
		<% 
		ArrayList<Object>  options = new ArrayList<Object>();
		int i=1;
		for (Object object : questions) {
			Class<?> clazz = object.getClass();
			Object[] fields = DisplayObject.getFields(object,clazz);
			Option option = new Option();
			
		 	options.addAll(getData.getObjByForeignId(option, "question", fields[0].toString()));
		%>
			 <div class="list-item">
			    <div class="left">Q<%=i++%></div>
			    <%
			    if(fields[3].equals(0)){
			    %>
			    <div class="right">
			        <div>
			        	<span>单选题：</span>
			        	<span><%=fields[2].toString()%></span>
			        </div>
			        <ul>
			        <%
			        for (Object optionItem : options) {
						Class<?> cla = optionItem.getClass();
						Object[] ops = DisplayObject.getFields(optionItem,cla);
			        %>
			            <li>
			            	<label>
			            		<input value="<%=ops[0].toString() %>" name="choosed<%=i%>" type="radio" />
			                	<span><%=ops[2].toString()%></span>
			            	</label>
			            </li>
			            <%} options.clear();%>
			        </ul>
			    </div>
			    <%} %>
			    <%
			    if(fields[3].equals(1)){
			    %>
			    <div class="right">
			        <div>
			        	<span>多选题：</span>
			        	<span><%=fields[2].toString()%></span>
			        </div>
			        <ul>
			        <%
			        for (Object optionItem : options) {
						Class<?> cla = optionItem.getClass();
						Object[] ops = DisplayObject.getFields(optionItem,cla);
			        %>
			            <li>
			            	<label>
			            	 	<input type="checkbox"  value="<%=ops[0].toString() %>"/>
			                	<span><%=ops[2].toString()%></span>
			            	</label>
			            </li>
			            <%} options.clear();%>
			        </ul>
			    </div>
			    <%} %>
			    <%
			    if(fields[3].equals(3)){
			    %>
			    <div class="right">
			        <div>
			        	<span>填空题：</span>
			        	<span><%=fields[2].toString()%></span>
			        </div>
			        <textarea rows="" cols=""></textarea>
			    </div>
			    <%} %>
			</div>
			<%} %>
		</div>
		<div class="footer">
			<button onclick="subFilled()">提交</button>
		</div>

	</div>
    <script src="../js/jquery-2.2.3.min.js"></script>
	<script src="../js/create.js"></script>
</body>
</html>


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
    <title>问卷设计</title>
	<meta charset="utf-8" />
    <link href="<%=path %>/css/create.css" rel="stylesheet" />
    <link rel="icon" href="<%=path %>/images/title.ico" type="image/x-icon" />
</head>
<body>
	<div class="head_nav">
		<a href="../views/back.jsp" class="back">返回</a>
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
		ArrayList<Object>  papers = new ArrayList<Object>();
		String paperId = request.getAttribute("paperId").toString();
		papers.addAll(getData.getObjById(paper, paperId));
		
		Question question = new Question();
		ArrayList<Object>  questions = new ArrayList<Object>();
		questions.addAll(getData.getObjByForeignId(question, "paper", paperId));
		%>
        <div>
        	<input type="hidden" id="paperId" value="<%=paperId%>">
            <div class="header">
                <div class="title">
                <%
					for (Object object : papers) {
						Class<?> clazz = object.getClass();
						Object[] fields = DisplayObject.getFields(object,clazz);
					%>
                	<span>标题：</span>	
                	<input  id="title"  value="<%=fields[2].toString()%>" />
                	<input  id="paperId" type="hidden" value="<%=fields[0].toString()%>" />
               <%} %>
                </div>
                <div class="tip">欢迎参加本次问卷</div>
            </div>

            <div class="counter">
                <div class="left-block">
                    <p onclick="CreateRadio()">单选题</p>
                    <p onclick="CreateChecked()">多选题</p>
                    <p onclick="CreateCompletion()">填空题</p>
                </div>
                <div class="right-block">
                
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
			        <div  class="question">
			        	<span>单选题：</span>
			        	<input id="<%=fields[0].toString()%>" value="<%=fields[2].toString()%>" />
			        </div>
			        <ul  class="option">
			        <%
			        for (Object optionItem : options) {
						Class<?> cla = optionItem.getClass();
						Object[] ops = DisplayObject.getFields(optionItem,cla);
			        %>
						<li>
							 <input type="radio" />
							 <input class="inputs" id="<%=ops[0].toString()%>"  value="<%=ops[2].toString()%>" />
						</li>
			            <%} options.clear();%>
			        </ul>
			    </div>
			    <%} %>
			    <%
			    if(fields[3].equals(1)){
			    %>
			    <div class="right">
			        <div  class="question">
			        	<span>多选题：</span>
			        	<input id="<%=fields[0].toString()%>" value="<%=fields[2].toString()%>" />
<!-- 			        	<span><%=fields[2].toString()%></span> -->
			        </div>
			        <ul  class="option">
			        <%
			        for (Object optionItem : options) {
						Class<?> cla = optionItem.getClass();
						Object[] ops = DisplayObject.getFields(optionItem,cla);
			        %>
<!-- 			            <li> -->
<!-- 			                <input type="checkbox"  value="<%=ops[0].toString() %>"/> -->
<!-- 			                <span><%=ops[2].toString()%></span> -->
<!-- 			            </li> -->
						<li>
							 <input type="checkbox" />
							 <input class="inputs" id="<%=ops[0].toString()%>"  value="<%=ops[2].toString()%>" />
						</li>
			            <%} options.clear();%>
			        </ul>
			    </div>
			    <%} %>
			    <%
			    if(fields[3].equals(3)){
			    %>
<!-- 			    <div class="right"> -->
<!-- 			        <div> -->
<!-- 			        	<span>填空题：</span> -->
<!-- 			        	<span><%=fields[2].toString()%></span> -->
<!-- 			        </div> -->
<!-- 			        <textarea rows="" cols=""></textarea> -->
<!-- 			    </div> -->
				    <div class="right">
				        <div  class="question">填空题：<input value="<%=fields[2].toString()%>"/></div>
				        <ul  class="option">
				            <li>
				                <textarea class="form-control" rows="3"></textarea>
				            </li>
				        </ul>
				    </div>
			    <%} %>
			</div>
			<%} %>
                </div>
            </div>
            <div class="footer">
				<button onclick="subPaper()">发布问卷</button>
            </div>
        </div>
    </div>
    <script src="<%=path %>/js/jquery-2.2.3.min.js"></script>
	<script src="<%=path %>/js/create.js"></script>
</body>
</html>


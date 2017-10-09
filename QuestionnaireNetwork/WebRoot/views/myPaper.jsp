<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="myTools.DisplayObject" %>
<li class="create">
		<a href="create.jsp"><i></i>新建</a>
</li>
<%
    ArrayList<Object> objects = (ArrayList<Object>) request.getAttribute("objects");
    for (Object object : objects) {
        Class<?> clazz = object.getClass();
        Object[] fields = DisplayObject.getFields(object,clazz);
        %>
<li class="hz survey" id="<%=fields[0].toString()%>">
	<div><span>标题：</span><span><%=fields[2] %></span></div>
	<br />
	<div><span>创建时间：</span><span><%=fields[6] %></span></div>
	<br />
	<div>
	<% if(fields[5].toString().equals("0")){%>
		<span>未发布</span>
	<% }if(fields[5].toString().equals("1")){%>
		<span>收集中</span>
		<span><%=fields[4] %></span>
	<% }if(fields[5].toString().equals("2")){%>
		<span>已完成</span>
		<span><%=fields[4] %></span>
		<%} %>
	</div>
	<br />
	<div class="operate">
	<form method="post" action="/QuestionnaireNetwork/servlets/BackServlet">
		<input type="hidden" name="choose" value="edit"/>
		<input type="hidden"  name="paperId" value="<%=fields[0] %>"/>
		<button type="submit">编辑</button>
	</form>
	<form method="post" action="/QuestionnaireNetwork/servlets/BackServlet">
		<input type="hidden" name="choose" value="seePaper"/>
		<input type="hidden"  name="paperId" value="<%=fields[0] %>"/>
		<button type="submit">预览</button>
	</form>
	<form method="post" action="/QuestionnaireNetwork/servlets/BackServlet">
		<input type="hidden" name="choose" value="seeAnswers"/>
		<input type="hidden"  name="paperId" value="<%=fields[0] %>"/>
		<button type="submit">数据</button>
	</form>
	<!-- 	<button>删除</button> -->
	</div>
</li><%}%>
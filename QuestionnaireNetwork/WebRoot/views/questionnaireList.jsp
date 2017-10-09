<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="myTools.DisplayObject" %>
<thead>
	<tr>
		<th>问卷标题</th>
		<th>创建时间</th>
	</tr>
</thead>
<tbody>
<%
    ArrayList<Object> objects = (ArrayList<Object>) request.getAttribute("objects");
    for (Object object : objects) {
        Class<?> clazz = object.getClass();
        Object[] fields = DisplayObject.getFields(object,clazz);
        %>
	<tr>
		<td>
		<form action="/QuestionnaireNetwork/servlets/AllQuertionnaireServlet" method="post">
			<input type="hidden" name="paperId" value="<%=fields[0].toString() %>" />
			<button type="submit">
				<span><%=fields[2].toString()%></span>
			</button>
		</form>
		</td>
		<td><%=fields[6].toString() %></td>
	</tr>
<%}%>
</tbody>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="myTools.DisplayObject" %>
<%@ page import="entity.Option" %>
<%@ page import="entity.Question" %>
<div class="list-item">
     <div class="left">Q1</div>
     <div class="right">
         <div class="question">
	         <%
	   		Question question =	(Question)request.getAttribute("question");
	        Class<?> claz = question.getClass();
	        Object[] values = DisplayObject.getFields(question,claz);
	        %>
	         <span>单选题:</span>  
	         <input id="<%=values[0].toString()%>" value="<%=values[2].toString()%>" />
         </div>
         <ul class="option">
         <%
    	ArrayList<Option> options = (ArrayList<Option>) request.getAttribute("options");
    		for (Object object : options) {
        		Class<?> clazz = object.getClass();
        		Object[] fields = DisplayObject.getFields(object,clazz);
        %>
             <li>
                 <input type="radio" />
                 <input class="inputs" id="<%=fields[0].toString()%>"  value="<%=fields[2].toString()%>" />
             </li>
		<%}%>
         </ul>
     </div>
 </div>
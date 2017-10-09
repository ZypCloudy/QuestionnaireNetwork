package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Paper;
import myTools.DisplayObject;
import service.GetData;


public class AllQuertionnaireServlet  extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		GetData _qs = new GetData(); 
		Paper paper = new Paper();
		ArrayList<Object>  objects =_qs.getAllObjects(paper);
		
		for (Object object : objects) {
		        Class<?> clazz = object.getClass();
		        Object[] fields = DisplayObject.getFields(object,clazz);
		
		     for (Object field : fields) {
		    	 System.out.print(field.toString()+" ");
		     }
	    	 System.out.println();
		 }
        request.setAttribute("objects",objects);
		request.getRequestDispatcher("/views/questionnaireList.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String paperId =  request.getParameter("paperId").toString(); 
		System.out.println(paperId);
		request.setAttribute("paperId",paperId);
		request.getRequestDispatcher("../views/fillQuestionnaire.jsp").forward(request, response);	
	}
}


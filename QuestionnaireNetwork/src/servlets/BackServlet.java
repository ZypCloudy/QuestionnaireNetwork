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


public class BackServlet  extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		GetData _qs = new GetData(); 
//		User user = new User();
		Paper paper = new Paper();
		String userId =  request.getSession().getAttribute("userId").toString();  
		System.out.println(userId);
		ArrayList<Object>  objects =_qs.selectByCon(userId,paper,"userId");
		
		for (Object object : objects) {
		        Class<?> clazz = object.getClass();
		        Object[] fields = DisplayObject.getFields(object,clazz);
		
		     for (Object field : fields) {
		    	 System.out.print(field.toString()+" ");
		     }
	    	 System.out.println();
		 }
        request.setAttribute("objects",objects);
		request.getRequestDispatcher("/views/myPaper.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String choose =  request.getParameter("choose"); 
		if(choose.equals("edit")){
			editPaper(request,response);
		}if(choose.equals("seeAnswers")){
			seeAnswers(request,response);
		}if(choose.equals("seePaper")){
			seePaper(request,response);
		}
	}
	private void editPaper (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String paperId =  request.getParameter("paperId"); 
		System.out.println(paperId);
		request.setAttribute("paperId",paperId);
		request.getRequestDispatcher("../views/createPaper.jsp").forward(request, response);	
	}
	private void seeAnswers (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String paperId =  request.getParameter("paperId").toString(); 
		System.out.println(paperId);
		request.setAttribute("paperId",paperId);
		request.getRequestDispatcher("../views/seeAnswers.jsp").forward(request, response);	
	}
	private void seePaper (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String paperId =  request.getParameter("paperId").toString(); 
		System.out.println(paperId);
		request.setAttribute("paperId",paperId);
		request.getRequestDispatcher("../views/seePaper.jsp").forward(request, response);	
	}
}

package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myTools.DisplayObject;
import entity.User;
import service.GetData;
import service.SendEmail;
import service.UpdateData;

public class RegisterServlet  extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("targetEmail");
	    int identifyCode = SendEmail.sendEmail(email);
	    HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("identifyCode", identifyCode);
        response.getWriter().print(identifyCode);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int identify= Integer.parseInt(request.getParameter("identifyCode"));
		if(request.getSession().getAttribute("identifyCode") == null){
			response.getWriter().print(1);
			return;
		}
		int identifyCode=(Integer) request.getSession().getAttribute("identifyCode");
		User checkUser = new User();
		GetData getData = new GetData();
		ArrayList<Object> list= new ArrayList<Object>();
		list.add(getData.selectByCon(email, checkUser, "email"));
		for (Object object : list) {
			Class<?> clazz = object.getClass();
			Object[] fields = DisplayObject.getFields(object,clazz);
			if(fields[2]!=null){
				response.getWriter().print(0);
				return;
			}else{
				if(identifyCode==identify){
					Date date=new Date();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=format.format(date);
					System.out.println(time);	
					User user = new User();
					user.setEmail(email);
					user.setPassword(password);
					user.setRegTime(time);
					
					UpdateData updateData = new UpdateData();
					updateData.save(user);
					response.getWriter().print(3);
				}else{
					response.getWriter().print(1);
				}
			}
		}
	}
}

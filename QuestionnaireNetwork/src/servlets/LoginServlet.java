package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.GetData;
import service.SendEmail;
import service.UpdateData;

public class LoginServlet  extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		GetData getData = new GetData();
		User user = new User();
		ArrayList<Object> userList = new ArrayList<Object>();
		userList.addAll(getData.selectByCon(email, user, "email"));
		user = (User) userList.get(0);
		if(user.getPassword().equals(password)){
			   //使用request对象的getSession()获取session，如果session不存在则创建一个
	        HttpSession session = request.getSession();
	        //将数据存储到session中
//	        session.setAttribute("user", user);
	        session.setAttribute("userId", user.getUserId());
	        session.setAttribute("userName", user.getNickname());
//	        //获取session的Id
//	        String sessionId = session.getId();
//	        //判断session是不是新创建的
//	        if (session.isNew()) {
//	            response.getWriter().print("session创建成功，session的id是："+sessionId);
//	        }else {
//	            response.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
//	        }
	        response.getWriter().print(1);
		}else{
	        response.getWriter().print(0);
		}
	}
}

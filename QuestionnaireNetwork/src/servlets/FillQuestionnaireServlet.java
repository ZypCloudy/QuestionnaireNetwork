package servlets;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myTools.DisplayObject;
import service.GetData;
import service.UpdateData;
import entity.Answer;
import entity.Option;
import entity.OptionAnswer;
import entity.Paper;
import entity.Question;
public class FillQuestionnaireServlet  extends HttpServlet {
	private UpdateData updateData = new UpdateData();
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		String[] choosed = request.getParameterValues("choosed[]");
		String paperId = request.getParameter("paperId");
		if(paperId==null){
			response.getWriter().print(1);
			return;
		}

		String ip = getIP(request);
		System.out.println(ip);
		
		GetData getData = new GetData();
		Answer check = new Answer();
		ArrayList<Object> answerList = new ArrayList<Object>();
		answerList.addAll(getData.selectByCon(ip, check, "userIp"));
		for (Object object : answerList) {
			Class<?> clazz = object.getClass();
			Object[] fields = DisplayObject.getFields(object,clazz);
			if(fields[1].toString().equals(paperId)){
				response.getWriter().print(0);
				return;
			}
		}
		Paper paper = new Paper();
		ArrayList<Object> paperList = new ArrayList<Object>();
		paperList.addAll(getData.getObjById(paper, paperId));
		for (Object object : paperList) {
			Class<?> clazz = object.getClass();
			Object[] fields = DisplayObject.getFields(object,clazz);
			int answerCount = (Integer)fields[4]+1;
			UpdateData up = new UpdateData();
			String count = Integer.toString(answerCount);
			up.updateOneById("Paper", count, paperId, "answerCount");
		}

		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		System.out.println(time);	
		Answer answer = new Answer();
		answer.setAnswerId(0);
		answer.setPaperId(Integer.parseInt(paperId));
		answer.setUserIp(ip);
		answer.setAnswerTime(time);
		int answerId =  updateData.save(answer);
		for(int i=0;i<choosed.length;i++){
			OptionAnswer oa = new OptionAnswer();
			oa.setOptionAnswerId(0);
			oa.setOptionId(Integer.parseInt((choosed[i]).toString()));
			oa.setAnswerId(answerId);
			oa.setQuestionId(1);
			updateData.save(oa);
		}
		response.getWriter().print(2);
		return;
	}
	 private static String getIP(HttpServletRequest request) {
		 Enumeration allNetInterfaces = null;    
	        try {    
	            allNetInterfaces = NetworkInterface.getNetworkInterfaces();    
	        } catch (java.net.SocketException e) {    
	            e.printStackTrace();    
	        }    
	        InetAddress ip = null;    
	        while (allNetInterfaces.hasMoreElements())    
	        {    
	            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces    
	                    .nextElement();        
	            Enumeration addresses = netInterface.getInetAddresses();    
	            while (addresses.hasMoreElements())    
	            {    
	                ip = (InetAddress) addresses.nextElement();    
	                if (ip != null && ip instanceof Inet4Address)    
	                {    
	                    if(ip.getHostAddress().equals("127.0.0.1")){  
	                        continue;  
	                    }  
	                    return ip.getHostAddress(); 
	                }    
	            }    
	        }    
	        System.out.println("网络无连接！"); 
			return null;
	    }
}


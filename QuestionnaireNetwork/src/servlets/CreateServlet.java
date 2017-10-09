package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Option;
import entity.Paper;
import entity.Question;
import myTools.DisplayObject;
import service.GetData;
import service.UpdateData;


public class CreateServlet  extends HttpServlet {
	private UpdateData updateData = new UpdateData();
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String questionType = request.getParameter("questionType");
		if(questionType.equals("createPaper")){
			createPaper(request,response);
		}else if(questionType.equals("createRadio")){
			createRadio(request,response);
		}else if(questionType.equals("createChecked")){
			createChecked(request,response);
		}else if(questionType.equals("createCompletion")){
			createCompletion(request,response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		String questionType = request.getParameter("title");
		String paperId =request.getParameter("paperId");
	    String[] questionInputs = request.getParameterValues("questionInputs[]");
        String[] questionIds = request.getParameterValues("questionIds[]");
	    String[] optionInputs = request.getParameterValues("optionInputs[]");
        String[] optionIds = request.getParameterValues("optionIds[]");
	    String[] completionInputs = request.getParameterValues("completionInputs[]");
//	    request.getParameter(questionType);
//	    Question question =	(Question)request.getAttribute("question");
//        Class<?> claz = question.getClass();
//        Object[] values = DisplayObject.getFields(question,claz);
	    
//		GetData getData = new GetData();
//	    Paper paper = new Paper();
//		ArrayList<Object> paperList = new ArrayList<Object>();
//		paperList.addAll(getData.getObjById(paper, paperId));
//		for (Object object : paperList) {
//			Class<?> clazz = object.getClass();
//			Object[] fields = DisplayObject.getFields(object,clazz);
//			int answerCount = (Integer)fields[5]+1;
//			UpdateData up = new UpdateData();
//			String count = Integer.toString(answerCount);
//			up.updateOneById("Paper", count, paperId, "answerCount");
//		}
	    updateData.updateOneById("Paper", "1", paperId, "paperStatus");
	    updateData.updateById("Question",questionInputs,questionIds,"question");
	    updateData.updateById("Option",optionInputs,optionIds,"option");
	}
	public void createPaper(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String title = request.getParameter("title");
		int type = Integer.parseInt(request.getParameter("type"));
		int userId=(Integer)request.getSession().getAttribute("userId"); 
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		System.out.println(time);	
		Paper paper = new Paper();
		paper.setPaperId(0);
		paper.setTitle(title);
		paper.setUserId(userId);
		paper.setAnswerCount(0);
		paper.setPaperStatus(0);
		paper.setPaperType(type);
		paper.setCreateTime(time);
		int paperId = updateData.save(paper);
        request.setAttribute("paperId",paperId);
        request.getRequestDispatcher("../views/createPaper.jsp").forward(request, response);	
	}
	public void createRadio(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int paperId = Integer.parseInt(request.getParameter("paperId"));
		Question question = new Question();
		question.setQuestionId(0);
		question.setPaperId(paperId);
		question.setQuestionName("题目");
		question.setQuestionType(0);
		int questionId = updateData.save(question);
		question.setQuestionId(questionId);
		request.setAttribute("question",question);
		
		ArrayList<Option> options = new ArrayList<Option>();
		for(int i=0;i<4;i++){
			Option option = new Option();
			option.setOptionId(0);
			option.setQuestionId(questionId);
			option.setOptionName("选项"+i);
			int optionId = updateData.save(option);
			option.setOptionId(optionId);
			System.out.print(optionId);
			options.add(option);
		}
		System.out.print(options);
		request.setAttribute("options",options);
		request.getRequestDispatcher("/views/_createRadio.jsp").forward(request, response);
	}
	public void createChecked(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int paperId = Integer.parseInt(request.getParameter("paperId"));
		Question question = new Question();
		question.setQuestionId(0);
		question.setPaperId(paperId);
		question.setQuestionName("题目");
		question.setQuestionType(1);
		int questionId = updateData.save(question);
		System.out.println(questionId);
		question.setQuestionId(questionId);
		request.setAttribute("question",question);
		
		ArrayList<Option> options = new ArrayList<Option>();
		for(int i=0;i<4;i++){
			Option option = new Option();
			option.setOptionId(0);
			option.setQuestionId(questionId);
			option.setOptionName("选项"+i);
			int optionId = updateData.save(option);
			option.setOptionId(optionId);
			System.out.println(optionId);
			options.add(option);
		}
		System.out.print(options);
		request.setAttribute("options",options);
		request.getRequestDispatcher("/views/_createChecked.jsp").forward(request, response);
	}
	public void createCompletion(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int paperId = Integer.parseInt(request.getParameter("paperId"));
		Question question = new Question();
		question.setQuestionId(0);
		question.setPaperId(paperId);
		question.setQuestionName("题目");
		question.setQuestionType(3);
		int questionId = updateData.save(question);
		System.out.println(questionId);
		request.getRequestDispatcher("/views/_createCompletion.jsp").forward(request, response);
	}
}


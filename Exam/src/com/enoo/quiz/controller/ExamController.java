package com.enoo.quiz.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enoo.quiz.Exam;
import com.enoo.quiz.QuizQuestion;

/**
 * 시험 컨트롤러
 */
@WebServlet("/exam")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean finish = false;

		HttpSession session = request.getSession();
		try {
			//만들어진 문제지가 없으면
			if (session.getAttribute("currentExam") == null) {
				session = request.getSession();
				
				//챕터명
				String selectedExam = (String) request.getSession().getAttribute("exam");

				//총 문제 갯수
				Object ob = session.getAttribute("totalNumberOfQuizQuestions");

				String sob = (String) ob;
				System.out.println("ExamController.selectedExam="+selectedExam);
				Exam newExam = new Exam(selectedExam, Integer.parseInt(sob));
				
				//만들어진 문제지를 세션에 보관
				session.setAttribute("currentExam", newExam);
				System.out.println("ExamController.currentExam="+newExam);
				
				String sq = (String) request.getSession().getAttribute("totalNumberOfQuizQuestions");
				
				newExam.setTotalNumberOfQuestions(Integer.parseInt(sq));
				//시험 시작시간
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started = dateFormat.format(date);
				session.setAttribute("started", started);
			}

		} catch (Exception e) {
			//예외발생시 홈으로
			e.printStackTrace();
			request.getRequestDispatcher("/home").forward(request, response);
		}

		Exam exam = (Exam) request.getSession().getAttribute("currentExam");

		if (exam.currentQuestion == 0) {
			exam.setQuestion(exam.currentQuestion);
			QuizQuestion q = exam.questionList.get(exam.currentQuestion);
			session.setAttribute("quest", q);
		}

		String action = request.getParameter("action");

		int minute = -1;
		int second = -1;
		if (request.getParameter("minute") != null) {
			minute = Integer.parseInt(request.getParameter("minute"));
			second = Integer.parseInt(request.getParameter("second"));
			request.getSession().setAttribute("min", request.getParameter("minute"));
			request.getSession().setAttribute("sec", request.getParameter("second"));
		}

		//제출한 답안으로 Exam 의 답안을 만든다
		String radio = request.getParameter("answer");
		int selectedRadio = -1;
		//exam.selections.put(exam.currentQuestion, selectedRadio);
		System.out.println("exam.currentQuestion="+exam.currentQuestion+"   radio="+radio);
		if ("1".equals(radio)) {
			selectedRadio = 1;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
		} else if ("2".equals(radio)) {
			selectedRadio = 2;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
		} else if ("3".equals(radio)) {
			selectedRadio = 3;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
		} else if ("4".equals(radio)) {
			selectedRadio = 4;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);
		}

		System.out.println("action=" + action);

		//다음버튼 눌렀어요
		if ("Next".equals(action)) {
			exam.currentQuestion++;
			exam.setQuestion(exam.currentQuestion);
			QuizQuestion q = exam.questionList.get(exam.currentQuestion);
			session.setAttribute("quest", q);
			System.out.println("exam.questionList.size()="+exam.questionList.size());
		} else if ("Previous".equals(action)) {
			System.out.println("Previous 버튼을 눌렀어요 exam.currentQuestion="+exam.currentQuestion);
			
			//첫 문제일때는 홈으로 
			if(exam.currentQuestion == 0) {
				request.getRequestDispatcher("/WEB-INF/jsps/home.jsp").forward(request, response);
				return;
			}else {
				//첫문제가 아닐때는 앞문제로
				exam.currentQuestion--;
				exam.setQuestion(exam.currentQuestion);
				QuizQuestion q = exam.questionList.get(exam.currentQuestion);
				session.setAttribute("quest", q);
			}

		} else if ("Finish Exam".equals(action) || (minute == 0 && second == 0)) {
			//종료버튼 눌렀어요
			finish = true;
			int result = exam.calculateResult(exam, exam.questionList.size());
			request.setAttribute("result", result);
			request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request, response);

		}

		if (finish != true) {
			//끝날때 까지 끝난게 아니다 계속 문제를 풀도록 반복 호출 
			request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request, response);
		}

	}

}

package com.enoo.quiz.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enoo.quiz.CreateQuestions;
import com.enoo.quiz.QuizQuestion;

/**
 * 메인 컨트롤러
 */
@WebServlet(urlPatterns = { "/index", "/login", "/register", "/takeExam", "/logout" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String applicationContextPath = request.getContextPath();
		// 홈으로
		if (request.getRequestURI().equals(applicationContextPath + "/index")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(applicationContextPath + "/login")) {
			// 로그인 화면으로
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(applicationContextPath + "/register")) {
			//회원가입 화면으로
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(applicationContextPath + "/takeExam")) {
			//시험장으로
			request.getSession().setAttribute("currentExam", null);
			request.getSession().setAttribute("totalNumberOfQuizQuestions", null);
			request.getSession().setAttribute("quizDuration", null);
			request.getSession().setAttribute("min", null);
			request.getSession().setAttribute("sec", null);

			String exam = request.getParameter("test");
			request.getSession().setAttribute("exam", exam); // 과목 단원명

			System.out.println(request.getSession().getAttribute("user"));

			// 로그인 상태확인
			if (request.getSession().getAttribute("user") == null) {
				request.getRequestDispatcher("/login").forward(request, response);

			} else {
				//안내페이지 보여주기
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/quizDetails.jsp");
				try {

					request.getSession().setAttribute("totalNumberOfQuizQuestions", "10"); // 10문제
					request.getSession().setAttribute("quizDuration", "2"); // 2분
					request.getSession().setAttribute("min", "2"); // 2분
					request.getSession().setAttribute("sec", 0);

				} catch (Exception e) {
					e.printStackTrace();
				}
				dispatcher.forward(request, response);
			}
		} else if (request.getRequestURI().equals(applicationContextPath + "/logout")) {
			//로그아웃 처리
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}

	}

}

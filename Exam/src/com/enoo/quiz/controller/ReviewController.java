package com.enoo.quiz.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enoo.quiz.Exam;
import com.enoo.quiz.QuizQuestion;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/exam/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Exam exam=(Exam)request.getSession().getAttribute("currentExam");
		
		request.setAttribute("totalQuestion",exam.getTotalNumberOfQuestions());
		
		ArrayList<QuizQuestion> reviewQuestionList=new ArrayList<QuizQuestion>();
		
		
		
		for(int i=0; i< 10;i++){
			QuizQuestion q = exam.getQuestion(i);
			q.setUserSelected(exam.getUserSelectionForQuestion(i));
			reviewQuestionList.add(i,q);
			System.out.println("ReviewController.getUserSelected["+i+"]="+q.getUserSelected());
		}
		request.setAttribute("reviewQuestions",reviewQuestionList);		
		request.getRequestDispatcher("/WEB-INF/jsps/examReview2.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

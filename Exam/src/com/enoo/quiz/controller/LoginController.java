package com.enoo.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enoo.quiz.DatabaseConnectionFactory;

/**
 * 로그인 컨트롤러
 */
@WebServlet("/checkLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultSet set = null;
		int i = 0;
		try {
			Statement st = con.createStatement();
			// 유저 디비 체크
			String sql = "Select * from  users where username='" + username + "' and password='" + password + "' ";
			System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {
				i = 1;
			}

			//사용자 디비에서 확인 되면 홈으로 
			if (i != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
				rd.forward(request, response);

			} else {
				//사용자가 없거나 틀렸으니 확인해라
				request.setAttribute("errorMessage", "존재하지 않는 사용자 또는 비밀번호가 입력되었습니다");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException sqe) {
			System.out.println("Error : 데이터베이스 조회 오류");
		}
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : 접속 해제 오류");
		}
	}

}

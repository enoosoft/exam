package com.enoo.quiz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * 문제지 생성
 */
public class CreateQuestions {
	
	public static ArrayList<QuizQuestion> getQuestions(String cat)
			throws SAXException, ParserConfigurationException, IOException, URISyntaxException {
		return getQuestionList(cat);
	}
	

	/**
	 *mysql db 에서 문제를 가져옴
	 * @param cat 챕터명
	 * @return
	 */
	public static ArrayList<QuizQuestion> getQuestionList(String cat) {
		ArrayList<QuizQuestion> oList = new ArrayList<QuizQuestion>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnectionFactory.createConnection();
			//문제 디비로부터 입력받은 챕터에서 랜덤으로 10개의 문제를 가져온다
			StringBuilder sb = new StringBuilder().append("SELECT * FROM questions WHERE cat = ? ORDER BY RAND() LIMIT 10");
			
			
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, cat); //챕터 입력
			
			rs = ps.executeQuery();
			int no = 1;
			while (rs.next()) {
				//가져온 디비 리스트에 담음
				QuizQuestion q = new QuizQuestion();
				q.setQuestionNumber(no++);
				//문제
				q.setQuestion(rs.getString(3));
				//예문 4개
				String[] options = new String[4];
				options[0] = rs.getString(4);
				options[1] = rs.getString(5);
				options[2] = rs.getString(6);
				options[3] = rs.getString(7);
				q.setQuestionOptions(options);
				//정답 번호
				q.setCorrectOptionIndex(rs.getInt(8));
				oList.add(q);
			}
		} catch (SQLException e) {e.printStackTrace();} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (Exception ex) {ex.printStackTrace();}
			if (ps != null) try {ps.close();} catch (Exception ex) {ex.printStackTrace();}
			if (con != null)try {con.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return oList;
	}
}

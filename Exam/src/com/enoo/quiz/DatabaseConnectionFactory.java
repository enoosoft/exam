package com.enoo.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 데이터베이스 연결
 */
public class DatabaseConnectionFactory {
	// 한글이 깨져서 구글링으로 mysqlEncoding=euckr 옵션 추가
	private static String dbURL = "jdbc:mysql://localhost/quiz?useUnicode=true&characterEncoding=euckr&mysqlEncoding=euckr";
	private static String dbUser = "quiz";
	private static String dbPassword = "Quiz1234!";

	public static Connection createConnection() {
		Connection con = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error: JDBC  드라이버를 찾을 수 없습니다");
				System.exit(1);
			}
			con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (SQLException sqe) {
			System.out.println("Error: 데이터베이스 연결 생성 실패");
			sqe.printStackTrace();
		}
		return con;
	}

}

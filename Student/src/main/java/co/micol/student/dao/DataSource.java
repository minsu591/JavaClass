package co.micol.student.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource { //Singletone 클래스 생성
	//나 자신으로 변수 생성
	private static DataSource dataSource = new DataSource();
	private Connection conn; //return value에 대한 변수 생성
	private String driver;
	private String url;
	private String user;
	private String password;
	
	//생성자 잠구기
	private DataSource() {}
	
	
	public static DataSource getInstance() {
		return dataSource;
	}
	
	private void configuration() { //driver, url, user, password를 읽어오기 위해서
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileReader(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}catch(IOException e){ //파일이 없으면 발생하는 오류
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConnection() {
		try {
			configuration();
			 //드라이버 로드
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결 성공!!!");
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("DB 연결 실패...");
			e.printStackTrace();
		}
		
		return conn;
	}
}

package co.micol.student.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource { //Singletone 클래스 생성
	//나 자신으로 객체 생성
	private static DataSource dataSource = new DataSource();
	//나를 외부에서 호출하지 못하게 생성자 잠구기
	private DataSource() {}
	
	private Connection conn; //return value에 대한 변수 생성
	private String driver;
	private String url;
	private String user;
	private String password;
	
	//외부에서 getInstance만을 통해 나를 쓸 수 있게
	public static DataSource getInstance() {
		return dataSource;
	}
	
	private void dbconfiguration() { //driver, url, user, password를 읽어오기 위해서
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		//읽어온 값이 문자열로 return됨.
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
			dbconfiguration();
			 //드라이버 로드
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결 성공!!!");
			
		}catch(ClassNotFoundException | SQLException e) {//클래스를 찾지 못하면 , DB가 켜져있지 않으면
			System.out.println("DB 연결 실패...");
			e.printStackTrace();
		}
		
		return conn;
	}
}

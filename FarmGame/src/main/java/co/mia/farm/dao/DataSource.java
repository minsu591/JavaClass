package co.mia.farm.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource { //연결용
	private static DataSource dataSource = new DataSource();
	private DataSource() {};
	
	public static DataSource getInstance() {
		return dataSource;
	}
	
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	private void dbConfig() { //properties 자료 가져오기
		Properties properties = new Properties();
		String resource = getClass().getResource("/farmdb.properties").getPath();
		
		try {
			properties.load(new FileInputStream(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() { //커넥션 반환
		dbConfig();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	

}

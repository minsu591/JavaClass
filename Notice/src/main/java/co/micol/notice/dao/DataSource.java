package co.micol.notice.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource { // Singleton 타입
	private static DataSource datasource = new DataSource();
	private DataSource() {};

	public static DataSource getInstance() {
		return datasource;
	}

	// 사용할거
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;

	public Connection getConnection() {//커넥션을 연결
		dbConfig();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void dbConfig() {//driver, url, user, password 정보 읽어오기
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
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
	

}

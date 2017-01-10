//Conexao com o banco.
package me.dabpessoa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	
	private static Connection conexao = null;
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
//	static {
//		try {
//			
//			Class.forName(driver);
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	public ConnectionDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConexao() {
		try {
			if ( conexao == null || conexao.isClosed()) {
				if (driver == null) Class.forName(driver);
				conexao = DriverManager.getConnection(url, username, password);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public static void closeConnection() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		ConnectionDAO.driver = driver;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConnectionDAO.url = url;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ConnectionDAO.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConnectionDAO.password = password;
	}
	
}

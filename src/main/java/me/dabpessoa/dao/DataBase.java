package me.dabpessoa.dao;

import java.sql.SQLException;

public class DataBase {

	public static final int POSTGRE = 0;
	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static boolean bancoStatus = false;
	private StringBuilder ddl;
	
	public DataBase() {}
	
	public DataBase(StringBuilder ddl) {
		this.setDdl(ddl);
	}
	
	public boolean createDataBase(StringBuilder ddl) {
		
		try {
			return ConnectionDAO.getConexao().createStatement().execute(ddl.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public void setDdl(StringBuilder ddl) {
		this.ddl = ddl;
	}

	public StringBuilder getDdl() {
		return ddl;
	}
	
}

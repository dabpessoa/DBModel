package me.dabpessoa.dao;

import me.dabpessoa.util.Constants;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class DataBaseManager {

	public DataBase dataBase;
	
	public DataBaseManager() {
		this.dataBase = new DataBase();
	}
	
	public void getConnection() {
		if (DataBase.bancoStatus) ConnectionDAO.getConexao();
		else System.out.println("Defina um banco!");
	}
	
	public void closeConnection() {
		ConnectionDAO.closeConnection();
	}
	
	public boolean createBanco(StringBuilder ddl) {
		return this.dataBase.createDataBase(ddl);
	}
	
	public void setBanco(int banco) throws Exception {
		this.setDialect(banco);
		DataBase.bancoStatus = true;
		
		/*
		 * setar as propriedades do banco escolhido.
		 * as informa��es de cada banco est� no arquivo "br.unifor.properties.bd.properties
		 */
		
		Properties p = new Properties();
		p.load(this.readConteudo(Constants.PROPERTIES_PATH));
		
		String nomeBanco = null;
		switch (banco) {
			case 0: nomeBanco = "postgre";break;
			case 1: nomeBanco = "mysql";break;
			case 2: nomeBanco = "sqlserver";break;
		}
		
		
		System.out.println("BANCO: "+nomeBanco+ " codigo: "+banco);
		System.out.println("PROPERTY Driver....: "+p.getProperty(nomeBanco+".driver"));
		System.out.println("PROPERTY url.......: "+p.getProperty(nomeBanco+".url"));
		System.out.println("PROPERTY username..: "+p.getProperty(nomeBanco+".username"));
		System.out.println("PROPERTY password..: "+p.getProperty(nomeBanco+".password"));
		
		ConnectionDAO.setDriver(p.getProperty(nomeBanco+".driver"));
		ConnectionDAO.setUrl(p.getProperty(nomeBanco+".url"));
		ConnectionDAO.setUsername(p.getProperty(nomeBanco+".username"));
		ConnectionDAO.setPassword(p.getProperty(nomeBanco+".password"));
		
	}
	
	private void setDialect(int banco) {
		/*
		 * convert os sql's para o dialeto do banco especificado.
		 */
	}
	
	private FileReader readConteudo(String path) {

		File file = new File(path);
		
		if (!file.exists())
			return null;

		FileReader fr = null;
		try {
			fr = new FileReader(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fr;
	}
	
	public static void main(String args[]) {
		
		try {
			new DataBaseManager().setBanco(DataBase.POSTGRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

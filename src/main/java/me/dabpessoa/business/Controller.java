package me.dabpessoa.business;

import me.dabpessoa.bean.Relationship;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.business.listeners.ControllerListener;
import me.dabpessoa.business.sql.SQLFactory;
import me.dabpessoa.business.xml.DBModelXMLParser;
import me.dabpessoa.business.xml.XMLFilter;
import me.dabpessoa.dao.DataBase;
import me.dabpessoa.dao.DataBaseManager;
import me.dabpessoa.gui.PrincipalUI;
import me.dabpessoa.gui.RelationshipUI;
import me.dabpessoa.gui.SqlUI;
import me.dabpessoa.gui.TabelaUI;
import me.dabpessoa.util.FileUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ControllerListener {

	public static final int ADICIONAR = 1;
	public static final int REMOVER = 2;
	public static final int EDITAR = 3;
	public static final int RELACAO_TABELAS = 4;
	public static final int GERAR_SQL = 5;
	public static final int ADD_RELATIONSHIP = 6;
	public static final int SALVAR_MODELO = 7;
	public static final int CARREGAR_MODELO = 8;
	public static final int EXPORT_SQL = 9;
	public static final int CRIAR_BANCO = 10;
	
	private List<Tabela> tabelas;
	private List<Relationship> relationships;
	private PrincipalUI principalUI;
	private DataBaseManager dataBaseManager;
	private SqlUI sqlUI;
	
	public Controller() {
		this.tabelas = new ArrayList<Tabela>();
		this.relationships = new ArrayList<Relationship>();
		this.setDataBaseManager(new DataBaseManager());
	}

	public Controller(List<Tabela> tabelas) {
		super();
		this.tabelas = tabelas;
	}
	
	public void run() {
		principalUI = new PrincipalUI();
		principalUI.createAndShowGUI();
		principalUI.addTableListener(this);
	}
	
	public void addTabela(Tabela tabela) {
		this.tabelas.add(tabela);
	}
	
	public void removerTabela(Tabela tabela) {
		this.tabelas.remove(tabela);
	}

	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	@Override
	public void doAction(Object obj, int acao) {
		
		if (acao == Controller.ADICIONAR) {
			
			Tabela tabela = (Tabela) obj;
			this.addTabela(tabela);
			
		} else if (acao == Controller.REMOVER) {
			
			Tabela tabela = (Tabela) obj;
			this.removerTabela(tabela);
			
		} else if (acao == Controller.EDITAR) {
			
			Tabela tabela = (Tabela) obj;
			
			for (int i = 0 ; i < tabelas.size() ; i++) {
				if (tabelas.get(i).getId().equalsIgnoreCase(tabela.getId())) {
					tabelas.remove(i);
					tabelas.add(tabela);
				}
			}
			
			
		} else if (acao == Controller.RELACAO_TABELAS) {
			
			RelationshipUI relationShip = RelationshipUI.getInstance(principalUI.getListaTabelas());
			relationShip.setListener(principalUI);
			
			
		} else if (acao == Controller.GERAR_SQL) {
			
			for (int i = 0 ; i < tabelas.size() ; i++) {
				System.out.println("TITULO: "+tabelas.get(i).getTitulo());
			}
			
			List<Tabela> lt = new ArrayList<Tabela>(); 
			for (TabelaUI tu : principalUI.getListaTabelas()){
				lt.add(tu.getTabela());
			}
			
			SQLFactory sql = new SQLFactory();
			System.out.println(""+sql.GenerateCreateTable(lt).toString());
			System.out.println();
			System.out.println();
			System.out.println(""+sql.GenerateAlterTable(relationships).toString());
			
			
			StringBuilder temp = new StringBuilder();
			temp.append(sql.GenerateCreateTable(lt).toString());
			temp.append(sql.GenerateAlterTable(relationships).toString());
			SqlUI.getInstance(temp).addListener(this);
			
			
		} else if (acao == Controller.ADD_RELATIONSHIP) {
			
			
			
			Relationship rs = (Relationship) obj;
			
			System.out.println("rsNome: "+rs.getNome());
			System.out.println("TAB_1: "+rs.getLeftTable().getTitulo());
			System.out.println("TAB_2: "+rs.getRightTable().getTitulo());
			
			
			this.relationships.add(rs);
			
		} else if (acao == Controller.SALVAR_MODELO) {
			
			DBModelXMLParser parser = new DBModelXMLParser(principalUI.getListaTabelas(), relationships);
			System.out.println(parser.generateXML().toString());
			
			String extensions[] = {".xml"};
			FileUtils.createFileChooser(this.principalUI.getFrame(), "Salvar Modelo", extensions, parser.generateXML().toString());
						
		} else if (acao == Controller.CARREGAR_MODELO) {
			
			// passar arquivo carregado para PrincipalUI
			
			StringBuilder xml = new StringBuilder();
			
			JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new XMLFilter());
			fc.setDialogTitle("Abrir Modelo");
			
			int returnVal = fc.showOpenDialog(this.principalUI.getFrame());
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				xml = ResourceManager.readLinesFromTextFile(fc.getSelectedFile().getPath());
				this.principalUI.readModel(xml);
				
			}
			
		} else if (acao == Controller.EXPORT_SQL){
			
			
			/*
			 * M�todo para abrir caixa de di�logo
			 * para ser escolhido o local onde ser�
			 * salvo o arquivo ".sql"
			 */
			
			String sql = (String) obj;
			
			String[] extensions = {".sql"};
			FileUtils.createFileChooser(this.principalUI.getFrame(), "Gerar SQL", extensions, sql);
			
		} else if (acao == Controller.CRIAR_BANCO) {
			
			String sql = (String) obj;
			
			System.out.println("CRIAR BANCO..........!");
			
			try {
				dataBaseManager.setBanco(DataBase.POSTGRE);
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			boolean status = dataBaseManager.createBanco(new StringBuilder(sql));
			if (!status) JOptionPane.showMessageDialog(this.principalUI.getFrame(), "Comandos DDL executados com sucesso no SGBD configurado!");
			else JOptionPane.showMessageDialog(this.principalUI.getFrame(), "Falha ao executar comandos!");

		}
		
	}

	public void setSqlUI(SqlUI sqlUI) {
		this.sqlUI = sqlUI;
	}

	public SqlUI getSqlUI() {
		return sqlUI;
	}

	public void setDataBaseManager(DataBaseManager dataBaseManager) {
		this.dataBaseManager = dataBaseManager;
	}

	public DataBaseManager getDataBaseManager() {
		return dataBaseManager;
	}
	
}

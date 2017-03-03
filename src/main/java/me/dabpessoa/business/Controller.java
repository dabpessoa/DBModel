package main.java.me.dabpessoa.business;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.java.me.dabpessoa.bean.Relationship;
import main.java.me.dabpessoa.bean.Tabela;
import main.java.me.dabpessoa.bean.enums.UserAction;
import main.java.me.dabpessoa.business.xml.DBModelXMLParser;
import main.java.me.dabpessoa.business.xml.XMLFilter;
import main.java.me.dabpessoa.dao.DataBaseManager;
import main.java.me.dabpessoa.dao.DataBase;
import main.java.me.dabpessoa.gui.PrincipalUI;
import main.java.me.dabpessoa.gui.RelationshipUI;
import main.java.me.dabpessoa.gui.SqlUI;
import main.java.me.dabpessoa.gui.TabelaUI;
import main.java.me.dabpessoa.business.listeners.TablesListener;
import main.java.me.dabpessoa.business.sql.SQLFactory;
import main.java.me.dabpessoa.util.FileUtils;

public class Controller implements TablesListener {

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
		principalUI.addListener(this);
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
	public void updateTables(Object obj, UserAction acao) {
		
		if (acao == UserAction.ADICIONAR) {
			
			Tabela tabela = (Tabela) obj;
			this.addTabela(tabela);
			
		} else if (acao == UserAction.REMOVER) {
			
			Tabela tabela = (Tabela) obj;
			this.removerTabela(tabela);
			
		} else if (acao == UserAction.EDITAR) {
			
			Tabela tabela = (Tabela) obj;
			
			for (int i = 0 ; i < tabelas.size() ; i++) {
				if (tabelas.get(i).getId().equalsIgnoreCase(tabela.getId())) {
					tabelas.remove(i);
					tabelas.add(tabela);
				}
			}
			
			
		} else if (acao == UserAction.RELACAO_TABELAS) {
			
			RelationshipUI relationShip = RelationshipUI.getInstance(principalUI.getListaTabelas());
			relationShip.setListener(principalUI);
			
			
		} else if (acao == UserAction.GERAR_SQL) {
			
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
			
			
		} else if (acao == UserAction.ADD_RELATIONSHIP) {
			
			
			
			Relationship rs = (Relationship) obj;
			
			System.out.println("rsNome: "+rs.getNome());
			System.out.println("TAB_1: "+rs.getLeftTable().getTitulo());
			System.out.println("TAB_2: "+rs.getRightTable().getTitulo());
			
			
			this.relationships.add(rs);
			
		} else if (acao == UserAction.SALVAR_MODELO) {
			
			DBModelXMLParser parser = new DBModelXMLParser(principalUI.getListaTabelas(), relationships);
			System.out.println(parser.generateXML().toString());
			
			String extensions[] = {".xml"};
			FileUtils.createFileChooser(this.principalUI.getFrame(), "Salvar Modelo", extensions, parser.generateXML().toString());
						
		} else if (acao == UserAction.CARREGAR_MODELO) {
			
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
			
		} else if (acao == UserAction.EXPORT_SQL){
			
			
			/*
			 * M�todo para abrir caixa de di�logo
			 * para ser escolhido o local onde ser�
			 * salvo o arquivo ".sql"
			 */
			
			String sql = (String) obj;
			
			String[] extensions = {".sql"};
			FileUtils.createFileChooser(this.principalUI.getFrame(), "Gerar SQL", extensions, sql);
			
		} else if (acao == UserAction.CRIAR_BANCO) {
			
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

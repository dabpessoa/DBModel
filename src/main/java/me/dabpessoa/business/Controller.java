package main.java.me.dabpessoa.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.java.me.dabpessoa.bean.Relationship;
import main.java.me.dabpessoa.bean.Tabela;
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
	public void updateTables(Object obj, int acao) {
		
		if (acao == Controller.ADICIONAR) {
			
			Tabela tabela = (Tabela) obj;
			
			System.out.println("ENTROU NO ADICIONAR DO CONTROLLER...");
			System.out.println("ADICIONAR ID: "+tabela.getId());
			System.out.println("TITULO TESTE: "+tabela.getTitulo());
			this.addTabela(tabela);
			
		} else if (acao == Controller.REMOVER) {
			
			Tabela tabela = (Tabela) obj;
			
			System.out.println("ENTROU NO REMOVER DO CONTROLLER...");
			this.removerTabela(tabela);
			
		} else if (acao == Controller.EDITAR) {
			
			Tabela tabela = (Tabela) obj;
			
			System.out.println("ENTROU NO EDITAR DO CONTROLLER...");
			
			for (int i = 0 ; i < tabelas.size() ; i++) {
				System.out.println("TITULO TABELA: "+tabela.getTitulo());
				System.out.println("ID: "+tabela.getId());
				if (tabelas.get(i).getId().equalsIgnoreCase(tabela.getId())) {
					tabelas.remove(i);
					tabelas.add(tabela);
				}
			}
			
			
		} else if (acao == Controller.RELACAO_TABELAS) {
			
			RelationshipUI relationShip = RelationshipUI.getInstance(principalUI.getListaTabelas());
//			RelationshipUI relationShip = RelationshipUI.getInstance(principalUI.getFrame(), principalUI.getListaTabelas());
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
//			sqlUI.addListener(this);
			
			//GeneratingSQLUI genaratingSQL = new GeneratingSQLUI(principalUI.getListaTabelas());
			
			
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
				
				xml = this.readConteudo(fc.getSelectedFile().getPath());
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
			
			
//			JDialog childDialog = new JDialog(this.principalUI.getFrame(), true);
//			childDialog.setSize(250, 250);
//			childDialog.setVisible(true);
//			childDialog.setTitle("Teste");
			
		}
		
	}
	
	private StringBuilder readConteudo(String path) {

		StringBuilder conteudo = new StringBuilder();
		File file = new File(path);
		
		if (!file.exists())
			return null;

		try {
			FileReader fr;
			fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			while (linha != null) {
				conteudo.append(linha).append('\n');
				linha = br.readLine();
			}
			br.close();fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conteudo;
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

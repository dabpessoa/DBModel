package me.dabpessoa.gui;

import me.dabpessoa.business.listeners.RelationShipListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.List;

public class RelationshipUI extends javax.swing.JFrame implements ActionListener, WindowListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static RelationshipUI instance;
	private JLabel relationshipName;
	private JTextField textRelationshipName;
	private JLabel primaryKeyTable;
	private JLabel foreingKeyTable;
	private JComboBox cardinalidadeCombo;
	private JLabel cardinalidadeTxt;
	private JTable jTable2;
	private JButton cancelButton;
	private JButton saveButton;
	private JTable jTable1;
	private JComboBox comboForeignTable;
	private JComboBox comboPrimaryTable;
	private JComboBox primaryTableCombo;
	private JComboBox foreignTableCombo;
	private List<TabelaUI> listaTabelas;
	private RelationShipListener listener;
	private JScrollPane scrollTable1;
	private JScrollPane scrollTable2;
	
	private RelationshipUI() {
		super();
	}
	
	public static RelationshipUI getInstance(List<TabelaUI> listaTabelas) {
		if (instance == null) {
			instance = new RelationshipUI();
			instance.setListaTabelasSingleton(listaTabelas);
			instance.init();
			instance.addWindowListener(instance);
			instance.setLocationRelativeTo(null);
			instance.setResizable(false);
			instance.setTitle("Relacionamentos");
		}
		instance.setVisible(true);
		return instance;
	}
	
	private void init() {
		initGUI();
	}
	
	private void setListaTabelasSingleton(List<TabelaUI> listaTabelas) {
		this.listaTabelas = listaTabelas;
	}
	
	public void setListener(RelationShipListener listener) {
		this.listener = listener;
	}
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RelationshipUI inst = new RelationshipUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				relationshipName = new JLabel();
				getContentPane().add(relationshipName);
				relationshipName.setText("Nome da Relação:");
				relationshipName.setBounds(12, 12, 118, 14);
			}
			{
				textRelationshipName = new JTextField();
				getContentPane().add(textRelationshipName);
				textRelationshipName.setBounds(142, 9, 141, 21);
			}
			{
				primaryKeyTable = new JLabel();
				getContentPane().add(primaryKeyTable);
				primaryKeyTable.setText("Tabela com chave primária:");
				primaryKeyTable.setBounds(12, 42, 245, 14);
			}
			{
				foreingKeyTable = new JLabel();
				getContentPane().add(foreingKeyTable);
				foreingKeyTable.setText("Tabela com chave estrangeira:");
				foreingKeyTable.setBounds(284, 42, 279, 14);
			}
			
				String[] tabelasNames = new String[listaTabelas.size()+1];
				tabelasNames[0] = "";
				for (int i = 0 ; i < listaTabelas.size() ; i++) {
					tabelasNames[i+1] = listaTabelas.get(i).getTabela().getTitulo();
				}
			
			{
				ComboBoxModel comboPrimaryTableModel = new DefaultComboBoxModel(tabelasNames);
				comboPrimaryTable = new JComboBox();
				getContentPane().add(comboPrimaryTable);
				comboPrimaryTable.setModel(comboPrimaryTableModel);
				comboPrimaryTable.setBounds(12, 62, 266, 21);
				comboPrimaryTable.addActionListener(this);
				comboPrimaryTable.setActionCommand("comboPrimaryTable");
			}
			{
				ComboBoxModel comboForeignTableModel = new DefaultComboBoxModel(tabelasNames);
				comboForeignTable = new JComboBox();
				getContentPane().add(comboForeignTable);
				comboForeignTable.setModel(comboForeignTableModel);
				comboForeignTable.setBounds(284, 62, 279, 21);
				comboForeignTable.addActionListener(this);
				comboForeignTable.setActionCommand("comboForeignTable");
			}
			{
				String[] titulos = {"Chave Primaria"};
				TableModel jTable1Model = new DefaultTableModel(new Object[][] {}, titulos);
				jTable1 = new JTable();
				jTable1.setModel(jTable1Model);
				jTable1.setRowHeight(20);
				{
					scrollTable1 = new JScrollPane(jTable1);
					getContentPane().add(scrollTable1);
					scrollTable1.setBounds(12, 89, 271, 178);
					scrollTable1.addMouseListener(this);
				}
			}
			{
				saveButton = new JButton();
				getContentPane().add(saveButton);
				saveButton.setText("Salvar");
				saveButton.setBounds(358, 282, 99, 21);
				saveButton.addActionListener(this);
				saveButton.setActionCommand("saveButton");
			}
			{
				cancelButton = new JButton();
				getContentPane().add(cancelButton);
				cancelButton.setText("Cancelar");
				cancelButton.setBounds(468, 282, 95, 21);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("cancelButton");
			}
			{
				String[] titulos = {"Chave Estrangeira"};
				TableModel jTable2Model = new DefaultTableModel(new Object[][] {}, titulos);
				jTable2 = new JTable();
				jTable2.setModel(jTable2Model);
				jTable2.setRowHeight(20);
				{
					scrollTable2 = new JScrollPane(jTable2);
					getContentPane().add(scrollTable2);
					scrollTable2.setBounds(283, 89, 280, 178);
					scrollTable2.addMouseListener(this);
				}
				{
					cardinalidadeTxt = new JLabel();
					getContentPane().add(cardinalidadeTxt);
					cardinalidadeTxt.setText("Cardinalidade:");
					cardinalidadeTxt.setBounds(303, 12, 101, 14);
				}
				{
					String cardinalidades[] = {"1:1","1:N","N:1","N:N"};
					ComboBoxModel cardinalidadeComboModel = new DefaultComboBoxModel(cardinalidades);
					cardinalidadeCombo = new JComboBox();
					getContentPane().add(cardinalidadeCombo);
					cardinalidadeCombo.setModel(cardinalidadeComboModel);
					cardinalidadeCombo.setBounds(411, 9, 148, 21);
				}
			}
			this.setSize(583, 349);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equalsIgnoreCase("saveButton")) {

			DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
			List<String> list1 = (List<String>) model1.getDataVector();
			
			DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
			List<String> list2 = (List<String>)model2.getDataVector();
			
			listener.drawLines(textRelationshipName.getText(), (String) comboPrimaryTable.getSelectedItem(), (String) comboForeignTable.getSelectedItem(), (String)cardinalidadeCombo.getSelectedItem(), list1, list2);
			this.dispose();
			
		} else if (command.equalsIgnoreCase("cancelButton")) {
			
			this.dispose();
			
		} else if (command.equalsIgnoreCase("comboPrimaryTable")) {
			
			criarLinhasTable(jTable1, comboPrimaryTable, primaryTableCombo, getTipos1());
			
		} else if (command.equalsIgnoreCase("comboForeignTable")) {
			
			criarLinhasTable(jTable2, comboForeignTable, foreignTableCombo, getTipos2());
			
		}
		
	}
	
	public void criarLinhasTable(JTable jTable, JComboBox comboTables, JComboBox comboAtributos, String[] tipos) {
				
		comboAtributos = new JComboBox(tipos);
		comboAtributos.setEditable(false);
		
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        Object[] o = {comboAtributos.getItemAt(0)};

        model.addRow(o);
		
		TableColumn includeColumn1 = jTable.getColumnModel().getColumn(0);
        includeColumn1.setCellEditor(new DefaultCellEditor(comboAtributos));
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        jTable.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		
	}
	
	public String[] getTipos1() {
		String tipos[] = {};
		for (int i = 0 ; i < listaTabelas.size() ; i++) {
			if (((String)comboPrimaryTable.getSelectedItem()).equalsIgnoreCase(listaTabelas.get(i).getTabela().getTitulo()) && listaTabelas.get(i).getTabela().getAtributos() != null) {
				tipos = new String[listaTabelas.get(i).getTabela().getAtributos().size()];
				for (int j = 0 ; j < tipos.length ; j++) {
					tipos[j] = listaTabelas.get(i).getTabela().getAtributos().get(j).getNome();
				}
			}
		}
		return tipos;
	}
	
	public String[] getTipos2() {
		String tipos[] = {};
		for (int i = 0 ; i < listaTabelas.size() ; i++) {
			if (((String)comboForeignTable.getSelectedItem()).equalsIgnoreCase(listaTabelas.get(i).getTabela().getTitulo()) && listaTabelas.get(i).getTabela().getAtributos() != null) {
				tipos = new String[listaTabelas.get(i).getTabela().getAtributos().size()+2];
				tipos[0] = "Inserir um atributo novo";
				tipos[1] = "----------------";
				for (int j = 2 ; j < tipos.length ; j++) {
					tipos[j] = listaTabelas.get(i).getTabela().getAtributos().get(j-2).getNome();
				}
			}
		}
		return tipos;
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		instance = null;
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		criarLinhasTable(jTable1, comboPrimaryTable, primaryTableCombo, getTipos1());
		criarLinhasTable(jTable2, comboForeignTable, foreignTableCombo, getTipos2());
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}

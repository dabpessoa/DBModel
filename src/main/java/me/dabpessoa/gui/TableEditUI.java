package me.dabpessoa.gui;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.RestricaoIntegridade;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.bean.enums.TipoAtributo;
import me.dabpessoa.business.listeners.EditListener;
import me.dabpessoa.exception.EmptyColumnException;
import me.dabpessoa.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableEditUI extends javax.swing.JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 1L;
	private static TableEditUI instance;
	private JLabel tableName;
	private JTextField jTextField1;
	private JButton save;
	private JButton delAtributo;
	private JButton addAtributo;
	private JTable tableAtributos;
	private JScrollPane scrollTable;
	private Tabela tabela = new Tabela();
	private EditListener editListener;
	
	public static TableEditUI getInstance(Tabela tabela) {
		if (instance == null) {
			instance = new TableEditUI();
			instance.setTabelaSingleton(tabela);
			instance.init();
			instance.setLocationRelativeTo(null);
			instance.addWindowListener(instance);
			instance.setResizable(false);
			instance.setTitle("Editar Tabela");
			try {
				instance.setIconImage(instance.getToolkit().getImage(ResourceUtils.getURL("/resources/images/logo.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		instance.setVisible(true);
		return instance;
	}
	
	public void init() {
		jTextField1.setText(tabela.getTitulo());
		
		if (tabela.getAtributos() != null)
		for (int i = 0 ; i < tabela.getAtributos().size() ; i++) {
			DefaultTableModel model = (DefaultTableModel) tableAtributos.getModel();
			
			Atributo atrib = tabela.getAtributos().get(i);
			
			Object[] o = {atrib.getNome(), atrib.getTipo().getDescricao(), atrib.isChavePrimaria(), atrib.isRestrictNull(), atrib.isUniqueKey(), atrib.getIntegritRestriction() != null};
			model.addRow(o);
		}
		
	}
	
	private void setTabelaSingleton(Tabela tabela) {
		this.tabela = tabela;
	}
	
	public void setListener(EditListener editListener) {
		this.editListener = editListener;
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TableEditUI.getInstance(new Tabela());
			}
		});
	}
	
	private TableEditUI() {
		super();
		initGUI();
	}
	
	public static Image redimensiona(String caminho , int w, int h){
        BufferedImage fundo = null;  
        try { fundo = ImageIO.read(new File(caminho)); }   
        catch (IOException e1) { fundo = new BufferedImage(1,1,BufferedImage.BITMASK ); }  
        return fundo.getScaledInstance(w, h, 10000);  
    }  
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				tableName = new JLabel();
				tableName.setText("Nome da Tabela: ");
			}
			{
				String titulos[] = {"Nome do Atributo", "Tipo", "Chave Prim�ria", "Permitir Nulo", "Unique", "Foreign Key"};
				String tipos[] = TipoAtributo.asStringArray();
				DefaultTableModel tableAtributosModel = new DefaultTableModel(new Object[][] {}, titulos);
				tableAtributos = new JTable();
				tableAtributos.setModel(tableAtributosModel);
				tableAtributos.setRowHeight(20);
				tableAtributos.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
				tableAtributos.addKeyListener(new KeyAdapter() {  
				      public void keyPressed(KeyEvent e) {  
				          if (e.getKeyCode() == KeyEvent.VK_ENTER &&       
				        		  tableAtributos.getSelectedRow() != -1) {  
				        	  
				        	  DefaultTableModel model = (DefaultTableModel) tableAtributos.getModel();
								
								Object[] o = {new String(""), new String(""), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)};
								
								model.addRow(o);
				        	  
				          }  
				       }  
				 });  
				this.personalizarTable();
				{
					scrollTable = new JScrollPane(tableAtributos);
				}
			}
			{
				jTextField1 = new JTextField();
				System.out.println("TABELA T�TULO: "+tabela.getTitulo());
				jTextField1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						super.keyPressed(e);
						
						if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
//							ActionEvent ae = new ActionEvent(jTextField1, 1, "save");
//							actionPerformed(ae);
							
							DefaultTableModel model = (DefaultTableModel) tableAtributos.getModel();
							
							Object[] o = {new String(""), new String(""), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)};
							
							model.addRow(o);
							
							tableAtributos.grabFocus();
							tableAtributos.editCellAt(0, 0);
							
							
						}
						
					}
				});
			}
			{
				addAtributo = new JButton();
				Image im = redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"add.png", 18, 18);
				ImageIcon imgAddAttrib = new ImageIcon(im);
				addAtributo.setIcon(imgAddAttrib);
				addAtributo.setText("Novo Atributo");
				addAtributo.setActionCommand("addAtributo");
				addAtributo.addActionListener(this);
			}
			{
				delAtributo = new JButton();
				Image im = redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"remove.png", 18, 18);
				ImageIcon imgDelAttrib = new ImageIcon(im);
				delAtributo.setIcon(imgDelAttrib);
				delAtributo.setText("Remover Atributo");
				delAtributo.setActionCommand("delAtributo");
				delAtributo.addActionListener(this);
			}
			{
				save = new JButton();
				Image im = redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"save.png", 18, 18);
				ImageIcon imgSave = new ImageIcon(im);
				save.setIcon(imgSave);
				save.setText("Salvar Altera��es");
				save.setActionCommand("save");
				save.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jTextField1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(tableName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
				.addGap(17)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(addAtributo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(delAtributo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(save, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(addAtributo, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
				                .addGap(22)
				                .addComponent(delAtributo, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(tableName, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
				                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				                .addGap(152)))
				        .addGap(19)
				        .addComponent(save, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(12, Short.MAX_VALUE));
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void personalizarTable() {
		
		JComboBox combo = new JComboBox(TipoAtributo.asStringArray());
		combo.setEditable(true);
		combo.setSelectedIndex(0);
		combo.setFocusable(true);
		
		TableColumn includeColumn1 = tableAtributos.getColumnModel().getColumn(1);
        includeColumn1.setCellEditor(new DefaultCellEditor(combo));
        
		TableColumn includeColumn = tableAtributos.getColumnModel().getColumn(2);
        includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        
        TableColumn includeColumn2 = tableAtributos.getColumnModel().getColumn(3);
        includeColumn2.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        
        TableColumn includeColumn4 = tableAtributos.getColumnModel().getColumn(4);
        includeColumn4.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        
        TableColumn includeColumn5 = tableAtributos.getColumnModel().getColumn(5);
        includeColumn5.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        tableAtributos.getColumnModel().getColumn(1).setCellRenderer(dtcr);

        tableAtributos.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {
	          // the method gives the component  like whome the cell must be rendered
	          public Component getTableCellRendererComponent(
	    		  JTable table, Object value, boolean isSelected,
	    		  boolean isFocused, int row, int col) {
	    	  	  boolean marked = (Boolean) value;
	    	  	  JCheckBox rendererComponent = new JCheckBox();
	    	  	  
	    	  	  if (marked) {
	    	  		  rendererComponent.setSelected(true);
	    	  	  }
	    	  	  return rendererComponent;
	          }
		});
        
        
        tableAtributos.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
	          // the method gives the component  like whome the cell must be rendered
	          public Component getTableCellRendererComponent(
	    		  JTable table, Object value, boolean isSelected,
	    		  boolean isFocused, int row, int col) {
	    	  	  boolean marked = (Boolean) value;
	    	  	  JCheckBox rendererComponent = new JCheckBox();
	    	  	  if (marked) {
	    	  		  rendererComponent.setSelected(true);
	    	  	  }
	    	  	  return rendererComponent;
	          }
		});
        
        tableAtributos.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer() {
	          // the method gives the component  like whome the cell must be rendered
	          public Component getTableCellRendererComponent(
	    		  JTable table, Object value, boolean isSelected,
	    		  boolean isFocused, int row, int col) {
	    	  	  boolean marked = (Boolean) value;
	    	  	  JCheckBox rendererComponent = new JCheckBox();
	    	  	  if (marked) {
	    	  		  rendererComponent.setSelected(true);
	    	  	  }
	    	  	  return rendererComponent;
	          }
		});
        
        tableAtributos.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
	          // the method gives the component  like whome the cell must be rendered
	          public Component getTableCellRendererComponent(
	    		  JTable table, Object value, boolean isSelected,
	    		  boolean isFocused, int row, int col) {
	    	  	  boolean marked = (Boolean) value;
	    	  	  JCheckBox rendererComponent = new JCheckBox();
	    	  	  if (marked) {
	    	  		  rendererComponent.setSelected(true);
	    	  	  }
	    	  	  return rendererComponent;
	          }
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equalsIgnoreCase("addAtributo")) {
			
			DefaultTableModel model = (DefaultTableModel) tableAtributos.getModel();
			
			Object[] o = {new String(""), new String(""), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)};
			
			model.addRow(o);
			
		} else if (command.equalsIgnoreCase("delAtributo")) {
			
			if (tableAtributos.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela!");
			} else {
				DefaultTableModel model = (DefaultTableModel) tableAtributos.getModel();
				model.removeRow(tableAtributos.getSelectedRow());
			}
			
			
		} else if (command.equalsIgnoreCase("save")) {
			
			/*
			 * Aqui se intancia um novo objeto Tabela
			 * seta seus atributos e manda um est�mulo
			 * para seus ouvintes.
			 */
			
			try {

				List<Atributo> atributos = new ArrayList<Atributo>();
				int rows = tableAtributos.getRowCount();
				DefaultTableModel dtm = (DefaultTableModel) tableAtributos.getModel();
				for ( int i = 0 ; i < rows ; i++) {
					Atributo at = new Atributo();
					for (int j = 0; j < Tabela.QUANTIDADE_COLUNAS; j++) {
						Object obj = dtm.getValueAt(i, j);

						verificaCampos(obj);

						switch (j) {
						case 0: at.setNome(obj+"");break;
						case 1: {
							at.setTipo(TipoAtributo.findType(obj+""));
							break;
						}
						case 2: at.setChavePrimaria((Boolean) obj);break;
						case 3: at.setRestrictNull((Boolean) obj); break;
						case 4: at.setUniqueKey((Boolean) obj); break;
						case 5: {
							
							RestricaoIntegridade fk = null;
							if ((Boolean) obj) fk = new RestricaoIntegridade();
							else fk = null;
							
							at.setIntegritRestriction(fk); 
							
							
							break;
						}
						default: System.out.println("DEFAULT ERROR...");break;
						}

					}
					atributos.add(at);
				}

				if (!jTextField1.getText().isEmpty())
					tabela.setTitulo(jTextField1.getText());
				tabela.setAtributos(atributos);

				editListener.updateTable(tabela);

				instance = null;
				this.dispose();


			} catch (EmptyColumnException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	public boolean verificaCampos(Object obj) throws EmptyColumnException {
		
		boolean result = true;
		
		if (obj == null || obj.equals("")) {
			throw new EmptyColumnException("Preencha Todos os Campos!");
		}
		
		return result;
		
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Tabela getTabela() {
		return tabela;
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

}

package me.dabpessoa.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import me.dabpessoa.core.Controller;
import me.dabpessoa.persistence.TablesListener;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SqlUI extends javax.swing.JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static SqlUI instance;
	private JLabel sqlGeradoTx;
	private JTextArea textAreaSql;
	private JButton exportarButton;
	private JButton cancelButton;
	private JScrollPane scroll;
	private StringBuilder sql;
	
	private List<TablesListener> listeners;
	private JButton criarBanco;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SqlUI inst = new SqlUI(new StringBuilder());
				inst.setResizable(true);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(500, 500);
				inst.init(new StringBuilder());
			}
		});
	}
	
	public static SqlUI getInstance(StringBuilder sql) {
		if (instance == null) {
			instance = new SqlUI(sql);
			instance.initGUI();
			instance.setLocationRelativeTo(null);
			instance.setResizable(false);
			instance.setTitle("Gerador de SQL (Banco PostGres)");
		}
		instance.init(sql);
		instance.setVisible(true);
		return instance;
	}
	
	private void init(StringBuilder sql) {
		this.setSql(sql);
		this.textAreaSql.setText(sql.toString());
	}
	
 	public void addListener(TablesListener listener) {
    	listeners.add(listener);
    }
    
    public void removeListener(TablesListener listener) {
    	listeners.remove(listener);
    }
    
    public void updateListeners(Object obj, int acao) {
    	for (int i = 0 ; i < listeners.size() ; i++) {
    		listeners.get(i).updateTables(obj, acao);
    	}
    }
	
	private SqlUI(StringBuilder sql) {
		super();
		this.listeners = new ArrayList<TablesListener>();
		this.setSql(sql);
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				sqlGeradoTx = new JLabel();
				sqlGeradoTx.setText("SQL Gerado: ");
			}
			{
				textAreaSql = new JTextArea(this.getSql().toString());
				{
					scroll = new JScrollPane(textAreaSql);
				}
				{
					criarBanco = new JButton();
					criarBanco.setText("Criar Banco de Dados");
					criarBanco.addActionListener(this);
					criarBanco.setActionCommand("criarBanco");
				}
			}
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancelar");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("cancel");
			}
			{
				exportarButton = new JButton();
				exportarButton.setText("Exportar");
				exportarButton.addActionListener(this);
				exportarButton.setActionCommand("export");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(sqlGeradoTx, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(exportarButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(criarBanco, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(18, 18)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(sqlGeradoTx, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
				        .addGap(63)
				        .addComponent(criarBanco, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
				        .addGap(19)
				        .addComponent(exportarButton, 0, 172, Short.MAX_VALUE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 697, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap(18, 18));
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equalsIgnoreCase("cancel")) {
			
			System.out.println("CANCELAR...!");
			this.dispose();
			
		} else if (command.equalsIgnoreCase("export")) {
			
			System.out.println("EXPORTAR");
			
			// Avisar ao seu ouvinte (Controller) que vai esportar.
			this.updateListeners(textAreaSql.getText(), Controller.EXPORT_SQL);
			
		} else if (command.equalsIgnoreCase("criarBanco")) {
			
			this.updateListeners(textAreaSql.getText(), Controller.CRIAR_BANCO);
			
		}
		
	}

	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	public StringBuilder getSql() {
		return sql;
	}

	public void setListeners(List<TablesListener> listeners) {
		this.listeners = listeners;
	}

	public List<TablesListener> getListeners() {
		return listeners;
	}

}

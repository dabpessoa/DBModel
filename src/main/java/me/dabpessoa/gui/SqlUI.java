package me.dabpessoa.gui;

import me.dabpessoa.business.DBModelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;


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
public class SqlUI extends javax.swing.JFrame implements java.awt.event.ActionListener {
	private static final long serialVersionUID = 1L;
	private static SqlUI instance;
	private JLabel sqlGeradoTx;
	private JTextArea textAreaSql;
	private JButton exportarButton;
	private JButton cancelButton;
	private JScrollPane scroll;
	private String sql;
	private DBModelManager manager;

	private JButton criarBanco;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SqlUI inst = new SqlUI();
				inst.setResizable(true);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(500, 500);
				inst.init(null);
			}
		});
	}
	
	public static SqlUI getInstance(DBModelManager manager, String sql) {
		if (instance == null) {
			instance = new SqlUI(manager, sql);
			instance.initGUI();
			instance.setLocationRelativeTo(null);
			instance.setResizable(false);
			instance.setTitle("Gerador de SQL (Banco PostGres)");
		}
		instance.init(sql);
		instance.setVisible(true);
		return instance;
	}
	
	private void init(String sql) {
		this.setSql(sql);
		this.textAreaSql.setText(sql);
	}

    private SqlUI() {
		this(null, null);
	}

	private SqlUI(DBModelManager manager, String sql) {
		super();
		this.setManager(manager);
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

			this.dispose();
			
		} else if (command.equalsIgnoreCase("export")) {

			manager.exportarSQL(textAreaSql.getText());
			
		} else if (command.equalsIgnoreCase("criarBanco")) {

			manager.criarBanco(textAreaSql.getText());
			
		}
		
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public DBModelManager getManager() {
		return manager;
	}

	public void setManager(DBModelManager manager) {
		this.manager = manager;
	}

}

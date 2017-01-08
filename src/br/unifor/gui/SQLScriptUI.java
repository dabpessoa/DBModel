package br.unifor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class SQLScriptUI extends javax.swing.JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 1L;
	private static SQLScriptUI instance;
	private JTextArea scriptSQL;
	private JPanel fundo;
	private JButton export, cancel;
	
	private SQLScriptUI() {
		super();
	}
	
	public static SQLScriptUI getInstance(List<TabelaUI> listaTabelas) {
		if (instance == null) {
			instance = new SQLScriptUI();
			instance.init();
			instance.addWindowListener(instance);
			instance.setLocationRelativeTo(null);
			instance.setVisible(true);
		}
		instance.setVisible(true);
		return instance;
	}
	
	private void init() {
		initGUI();
	}
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setSize(583, 349);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setScriptSQL(JTextArea scriptSQL) {
		this.scriptSQL = scriptSQL;
	}

	public JTextArea getScriptSQL() {
		return scriptSQL;
	}

	public void setFundo(JPanel fundo) {
		this.fundo = fundo;
	}

	public JPanel getFundo() {
		return fundo;
	}

	public void setExport(JButton export) {
		this.export = export;
	}

	public JButton getExport() {
		return export;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JButton getCancel() {
		return cancel;
	}
}

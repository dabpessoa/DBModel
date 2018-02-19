package me.dabpessoa.gui.components;

import me.dabpessoa.util.ResourceUtils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

public class About extends JDialog implements WindowListener {
	private static final long serialVersionUID = 1L;
	private JLabel dbModelTitle;
	private JLabel descriptionDBModel;
	private JLabel thiagoMartins;
	private JLabel logo;
	private JLabel diegoAugusto;
	private JLabel producers;
	private JLabel versionDBModel;
	private static About instance;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				About inst = new About(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public static About getInstance(JFrame dad) {
//		JDialog.setDefaultLookAndFeelDecorated(true);
		if (instance == null) {
			instance = new About(dad);
			instance.setLocationRelativeTo(null);
			instance.setResizable(false);
			instance.setTitle("Sobre DBModel");
			instance.addWindowListener(instance);
			try {
				instance.setIconImage(instance.getToolkit().getImage(ResourceUtils.getURL(("/resources/images/logo.png"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		instance.setVisible(true);
		return instance;
	}
	
	
	
	private About(JFrame dad) {
		super(dad, true);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().add(getDbModelTitle());
			getContentPane().add(getVersionDBModel());
			getContentPane().add(getDescriptionDBModel());
			getContentPane().add(getProducers());
			getContentPane().add(getDiegoAugusto());
			getContentPane().add(getThiagoMartins());
			getContentPane().add(getLogo());
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JLabel getDbModelTitle() {
		if(dbModelTitle == null) {
			dbModelTitle = new JLabel();
			dbModelTitle.setText("DBModel");
			dbModelTitle.setBounds(177, 23, 66, 19);
		}
		return dbModelTitle;
	}
	
	private JLabel getVersionDBModel() {
		if(versionDBModel == null) {
			versionDBModel = new JLabel();
			versionDBModel.setText("Vers�o: 1.0");
			versionDBModel.setBounds(169, 42, 74, 14);
		}
		return versionDBModel;
	}
	
	private JLabel getDescriptionDBModel() {
		if(descriptionDBModel == null) {
			descriptionDBModel = new JLabel();
			descriptionDBModel.setText("Software desenvolvido com o objetivo de modelagem de dados");
			descriptionDBModel.setBounds(7, 85, 373, 14);
			descriptionDBModel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return descriptionDBModel;
	}
	
	private JLabel getProducers() {
		if(producers == null) {
			producers = new JLabel();
			producers.setText("Developed by:");
			producers.setBounds(223, 144, 100, 14);
		}
		return producers;
	}
	
	private JLabel getDiegoAugusto() {
		if(diegoAugusto == null) {
			diegoAugusto = new JLabel();
			diegoAugusto.setText("Diego Augusto Bezerra Pessoa");
			diegoAugusto.setBounds(181, 164, 199, 14);
		}
		return diegoAugusto;
	}
	
	private JLabel getThiagoMartins() {
		if(thiagoMartins == null) {
			thiagoMartins = new JLabel();
			thiagoMartins.setText("Thiago Martins");
			thiagoMartins.setBounds(223, 184, 169, 14);
		}
		return thiagoMartins;
	}
	
	private JLabel getLogo() {
		if(logo == null) {
			logo = new JLabel();
			try {
				logo.setIcon(new ImageIcon(ResourceUtils.getURL("/resources/images/logo.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			logo.setBounds(33, 111, 128, 129);
		}
		return logo;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		instance = null;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}

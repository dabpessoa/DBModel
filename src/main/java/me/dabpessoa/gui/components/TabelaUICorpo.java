package main.java.me.dabpessoa.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class TabelaUICorpo extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TabelaUICorpo() {
		// TODO Auto-generated constructor stub
		GridBagLayout gbLayout = new GridBagLayout();
		this.setLayout(gbLayout);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	
}

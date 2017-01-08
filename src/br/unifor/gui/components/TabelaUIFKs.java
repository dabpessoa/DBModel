package br.unifor.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TabelaUIFKs extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TabelaUIFKs() {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridLayout(0,1));
		this.setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	
}

package br.unifor.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.unifor.util.ImageUtils;

public class TabelaUITitulo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image img;

	public TabelaUITitulo() {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridLayout(0,1));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setBackground(Color.WHITE);
	}
	
	public TabelaUITitulo(String img) {
		this(new ImageIcon(img).getImage());
	}

	public TabelaUITitulo(Image img) {
		this();
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if (this.getParent().getPreferredSize().width > img.getWidth(null)) pintar(g);
		else g.drawImage(img, 0, 0, null);
		
	}

	public void pintar(Graphics g) {
		chamarRedimensionar();
		g.drawImage(img, 0, 0, null);
	}
	
	public void chamarRedimensionar() {
		img = ImageUtils.redimensiona("src"+File.separator+"br"+File.separator+"unifor"+File.separator+"resources"+File.separator+"titleBack.png", this.getParent().getPreferredSize().width, this.getPreferredSize().height+5);
	}

}

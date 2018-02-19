package me.dabpessoa.gui.components;

import me.dabpessoa.util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TabelaUITitulo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image img;

	public TabelaUITitulo() {
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
		super.paintComponent(g);
		
		if (this.getParent().getPreferredSize().width > img.getWidth(null)) pintar(g);
		else g.drawImage(img, 0, 0, null);
		
	}

	public void pintar(Graphics g) {
		chamarRedimensionar();
		g.drawImage(img, 0, 0, null);
	}
	
	public void chamarRedimensionar() {
		img = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"titleBack.png", this.getParent().getPreferredSize().width, this.getPreferredSize().height+5);
	}

}

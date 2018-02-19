package me.dabpessoa.gui.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TabelaUICorpo extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TabelaUICorpo() {
		GridBagLayout gbLayout = new GridBagLayout();
		this.setLayout(gbLayout);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}

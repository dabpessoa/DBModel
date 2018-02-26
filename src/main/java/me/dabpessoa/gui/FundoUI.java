package me.dabpessoa.gui;

import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Relacionamento;

import javax.swing.*;
import java.awt.*;

public class FundoUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private static FundoUI instance;
	private Modelo modelo;
	
	public static FundoUI getInstance() {
		if (instance == null) {
			instance = new FundoUI(null);
		}
		return instance;
	}
	
	private FundoUI(Modelo modelo) {}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaintAll(g);
	}
	
	public void repaintAll(Graphics g) {

		if (modelo != null) {
			for (Relacionamento relacionamento : modelo.getRelacionamentos()) {
				int intT1x = relacionamento.getTabela1().getModelo().getPosicaoX().intValue();
				int intT1y = relacionamento.getTabela1().getModelo().getPosicaoY().intValue();
				int intT2x = relacionamento.getTabela2().getModelo().getPosicaoX().intValue();
				int intT2y = relacionamento.getTabela2().getModelo().getPosicaoY().intValue();
				g.drawLine(intT1x, intT1y, intT2x, intT2y);
				g.drawString(relacionamento.getCardinalidade().split(":")[0], intT1x - 15, intT1y);
				g.drawString(relacionamento.getCardinalidade().split(":")[1], intT2x - 15, intT2y);
			}
		}

	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}

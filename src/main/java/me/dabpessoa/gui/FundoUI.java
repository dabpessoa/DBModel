package me.dabpessoa.gui;

import me.dabpessoa.bean.Relacionamento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FundoUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private static FundoUI instance;
	private List<TabelaUI> t1s = new ArrayList<TabelaUI>();
	private List<TabelaUI> t2s = new ArrayList<TabelaUI>();
	private List<String> cardinalidades = new ArrayList<String>();
	
	private List<Relacionamento> relationships = new ArrayList<Relacionamento>();
	
	public static FundoUI getInstance() {
		if (instance == null) {
			instance = new FundoUI();
		}
		return instance;
	}
	
	private FundoUI() {}
	
	public void addCardinalidade(String cardinalidade) {
		this.cardinalidades.add(cardinalidade);
	}
	
	public void setT1(TabelaUI t1) {
		this.t1s.add(t1);
	}
	
	public void setT2(TabelaUI t2) {
		this.t2s.add(t2);
	}
	
	public List<TabelaUI> getT1s() {
		return t1s;
	}
	
	public List<TabelaUI> getT2s() {
		return t2s;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaintAll(g);
	}
	
	public void repaintAll(Graphics g) {
		
		for (int i = 0 ; i < t1s.size() ; i++) {
			int intT1x = t1s.get(i).getParent().getLocation().x;
			int intT1y = t1s.get(i).getParent().getLocation().y;
			int intT2x = t2s.get(i).getParent().getLocation().x;
			int intT2y = t2s.get(i).getParent().getLocation().y;
			g.drawLine(intT1x, intT1y, intT2x, intT2y);
			g.drawString(cardinalidades.get(i).split(":")[0], intT1x - 15, intT1y);
			g.drawString(cardinalidades.get(i).split(":")[1], intT2x - 15, intT2y);
		}

	}

	public void setRelationships(List<Relacionamento> relationships) {
		this.relationships = relationships;
	}

	public List<Relacionamento> getRelationships() {
		return relationships;
	}

	public void setCardinalidades(List<String> cardinalidades) {
		this.cardinalidades = cardinalidades;
	}

	public List<String> getCardinalidades() {
		return cardinalidades;
	}
	
}

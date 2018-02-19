package me.dabpessoa.business.xml;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.Relationship;
import me.dabpessoa.gui.TabelaUI;
import me.dabpessoa.util.Tag;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DBModelXMLParser {
	
	private List<TabelaUI> tabelasUI;
	private List<Relationship> relationships;
	
	public DBModelXMLParser() {
		this.setTabelasUI(new ArrayList<TabelaUI>());
		this.relationships = new ArrayList<Relationship>();
	}
	
	public DBModelXMLParser(List<TabelaUI> tabelasUI, List<Relationship> relationships) {
		this.setTabelasUI(tabelasUI);
		this.relationships = relationships;
	}
	
	public StringBuilder generateXML() {
		
		/*
		 * Gera XML padronizado com base na lista de tabelasUI.
		 */

		StringBuilder xml = new StringBuilder();
		
		xml.append("<DBModel>\n");
		xml.append("\t<backGround>\n");
		
		if (tabelasUI.size() != 0) xml.append("\t\t<tableList>\n");
		for (int i = 0 ; i < tabelasUI.size() ; i++) {
			xml.append("\t\t\t<tableUI>\n");
			
			TabelaUI tabelaUI = tabelasUI.get(i);
			xml.append("\t\t\t\t<title>");
			xml.append(tabelaUI.getTabela().getTitulo());
			xml.append("</title>\n");
			xml.append("\t\t\t\t<position>\n");
			xml.append("\t\t\t\t\t<prefered-width>");
			xml.append(tabelaUI.getPreferredSize().getWidth()+"");
			xml.append("</prefered-width>\n");
			xml.append("\t\t\t\t\t<prefered-height>");
			xml.append(tabelaUI.getPreferredSize().getHeight()+"");
			xml.append("</prefered-height>\n");
			xml.append("\t\t\t\t\t<backGround-relative-width>");
			xml.append(tabelaUI.getLocation().getX()+"");
			xml.append("</backGround-relative-width>\n");
			xml.append("\t\t\t\t\t<backGround-relative-height>");
			xml.append(tabelaUI.getLocation().getY()+"");
			xml.append("</backGround-relative-height>\n");
			xml.append("\t\t\t\t</position>\n");
			
			if (tabelaUI.getTabela().getAtributos() != null) {
				if (tabelaUI.getTabela().getAtributos().size() != 0) xml.append("\t\t\t\t<attributeList>\n");
				for (int j = 0 ; j < tabelaUI.getTabela().getAtributos().size() ; j++) {
					Atributo atrib = tabelaUI.getTabela().getAtributos().get(j);
					
					xml.append("\t\t\t\t\t<attribute>\n");
					xml.append("\t\t\t\t\t\t<name>");
					xml.append(atrib.getNome());
					xml.append("<name>\n");
					xml.append("\t\t\t\t\t\t<type>");
					xml.append(atrib.getTipo().getDescricao());
					xml.append("</type>\n");
					xml.append("\t\t\t\t\t\t<primary-key>");
					xml.append(atrib.isChavePrimaria()+"");
					xml.append("</primary-key>\n");
					xml.append("\t\t\t\t\t\t<nullable>");
					xml.append(atrib.isRestrictNull()+"");
					xml.append("</nullable>\n");
					xml.append("\t\t\t\t\t</attribute>\n");
					
					
				}
				if (tabelaUI.getTabela().getAtributos().size() != 0) xml.append("\t\t\t\t</attributeList>\n");
			}
			
			xml.append("\t\t\t</tableUI>\n");
		}
		if (tabelasUI.size() != 0) xml.append("\t\t</tableList>\n");
		
		if (relationships.size() > 0) xml.append("\t\t<relationshipList>\n");
		for (int i = 0 ; i < relationships.size() ; i++) {
			Relationship r = relationships.get(i);
			
			xml.append("\t\t\t<relationship>\n");
			xml.append("\t\t\t\t<name>");
			xml.append(r.getNome());
			xml.append("</name>\n");
			xml.append("\t\t\t\t<primary-table-name>");
			xml.append(r.getLeftTable().getTitulo());
			xml.append("</primary-table-name>\n");
			xml.append("\t\t\t\t<foreing-table-name>");
			xml.append(r.getRightTable().getTitulo());
			xml.append("</foreing-table-name>\n");
		}
		if (relationships.size() > 0) xml.append("\t\t</relationshipList>\n");
		
		xml.append("\t</backGround>\n");
		xml.append("</DBModel>\n");
		
		return xml;
		
		
	}
	
	public List<TabelaUI> loadXML(StringBuilder xml) {
		
		System.out.println("LOAD XML: "+xml.toString());
		
		List<TabelaUI> tablesUI = new ArrayList<TabelaUI>();
		
		/*
		 * Cria Lista de tabelasUI baseado no XML padronizado.
		 */
		
		TabelaUI teste = new TabelaUI();
		teste.setBackground(Color.WHITE);
		teste.initGUI();
		tablesUI.add(teste);
		
		
		return tablesUI;
		
	}

	public void setTabelasUI(List<TabelaUI> tabelasUI) {
		this.tabelasUI = tabelasUI;
	}

	public List<TabelaUI> getTabelasUI() {
		return tabelasUI;
	}
	
	public StringBuilder teste() {
		
		Tag tagRaiz = new Tag("DBModel", null, null);
		tagRaiz.addChildTag(new Tag("backGround", null, tagRaiz));
		tagRaiz.addChildTag(new Tag("tableList", null, tagRaiz.getChildTagByName("backGround")));
		
		return tagRaiz.genarateStringByThis();
		
	}
	
}

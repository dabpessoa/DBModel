package me.dabpessoa.business.xml;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.gui.TabelaUI;
import me.dabpessoa.util.Tag;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DBModelXMLParser {
	
	public static StringBuilder generateXML(Modelo modelo) {

		List<Tabela> tabelas = modelo.getTabelas();
		List<Relacionamento> relacionamentos = modelo.getRelacionamentos();

		/*
		 * Gera XML padronizado com base na lista de tabelas.
		 */

		StringBuilder xml = new StringBuilder();
		
		xml.append("<DBModel>\n");
		xml.append("\t<backGround>\n");
		
		if (tabelas.size() != 0) xml.append("\t\t<tableList>\n");
		for (int i = 0; i < tabelas.size() ; i++) {
			xml.append("\t\t\t<tableUI>\n");
			
			Tabela tabela = tabelas.get(i);
			xml.append("\t\t\t\t<title>");
			xml.append(tabela.getTitulo());
			xml.append("</title>\n");
			xml.append("\t\t\t\t<position>\n");
			xml.append("\t\t\t\t\t<prefered-width>");
			xml.append(tabela.getModelo().getLargura()+"");
			xml.append("</prefered-width>\n");
			xml.append("\t\t\t\t\t<prefered-height>");
			xml.append(tabela.getModelo().getAltura()+"");
			xml.append("</prefered-height>\n");
			xml.append("\t\t\t\t\t<backGround-relative-width>");
			xml.append(tabela.getModelo().getPosicaoX()+"");
			xml.append("</backGround-relative-width>\n");
			xml.append("\t\t\t\t\t<backGround-relative-height>");
			xml.append(tabela.getModelo().getPosicaoY()+"");
			xml.append("</backGround-relative-height>\n");
			xml.append("\t\t\t\t</position>\n");
			
			if (tabela.getAtributos() != null) {
				if (tabela.getAtributos().size() != 0) xml.append("\t\t\t\t<attributeList>\n");
				for (int j = 0 ; j < tabela.getAtributos().size() ; j++) {
					Atributo atrib = tabela.getAtributos().get(j);
					
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
				if (tabela.getAtributos().size() != 0) xml.append("\t\t\t\t</attributeList>\n");
			}
			
			xml.append("\t\t\t</tableUI>\n");
		}
		if (tabelas.size() != 0) xml.append("\t\t</tableList>\n");
		
		if (relacionamentos.size() > 0) xml.append("\t\t<relationshipList>\n");
		for (int i = 0; i < relacionamentos.size() ; i++) {
			Relacionamento r = relacionamentos.get(i);
			
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
		if (relacionamentos.size() > 0) xml.append("\t\t</relationshipList>\n");
		
		xml.append("\t</backGround>\n");
		xml.append("</DBModel>\n");
		
		return xml;
		
		
	}

	// TODO FIXME implementar e testar
	public static Modelo loadXML(String xml) {
		
		List<TabelaUI> tablesUI = new ArrayList<TabelaUI>();
		
		/*
		 * Cria Lista de tabelas baseado no XML padronizado.
		 */
		
		TabelaUI teste = new TabelaUI();
		teste.setBackground(Color.WHITE);
		teste.initGUI();
		tablesUI.add(teste);
		
		
		return null;
		
	}
	
	public StringBuilder teste() {
		
		Tag tagRaiz = new Tag("DBModel", null, null);
		tagRaiz.addChildTag(new Tag("backGround", null, tagRaiz));
		tagRaiz.addChildTag(new Tag("tableList", null, tagRaiz.getChildTagByName("backGround")));
		
		return tagRaiz.genarateStringByThis();
		
	}
	
}

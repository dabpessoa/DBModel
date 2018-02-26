package me.dabpessoa.business.xml;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.util.xml.XMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class DBModelXMLParser {

	/*
		 * Gera XML padronizado com base no modelo desenhado no programa.
		 */
	public static String generateXML(Modelo modelo) {

		if (modelo == null) return null;

		List<Tabela> tabelas = modelo.getTabelas();
		List<Relacionamento> relacionamentos = modelo.getRelacionamentos();

		StringBuilder xml = new StringBuilder();
		
		xml.append("<DBModel>\n");
		
		if (modelo.quantidadeTabelas() != 0) xml.append("\t<tabelas>\n");
		for (int i = 0; i < modelo.quantidadeTabelas() ; i++) {
			xml.append("\t\t<tabela>\n");
			
			Tabela tabela = tabelas.get(i);
			xml.append("\t\t\t<titulo>");
			xml.append(tabela.getTitulo());
			xml.append("</titulo>\n");
			xml.append("\t\t\t<largura>");
			xml.append(tabela.getModelo().getLargura()+"");
			xml.append("</largura>\n");
			xml.append("\t\t\t<altura>");
			xml.append(tabela.getModelo().getAltura()+"");
			xml.append("</altura>\n");
			xml.append("\t\t\t<posicaoX>");
			xml.append(tabela.getModelo().getPosicaoX()+"");
			xml.append("</posicaoX>\n");
			xml.append("\t\t\t<posicaoY>");
			xml.append(tabela.getModelo().getPosicaoY()+"");
			xml.append("</posicaoY>\n");
			
			if (tabela.getAtributos() != null) {
				if (tabela.getAtributos().size() != 0) xml.append("\t\t\t\t<atributos>\n");
				for (int j = 0 ; j < tabela.getAtributos().size() ; j++) {
					Atributo atrib = tabela.getAtributos().get(j);
					
					xml.append("\t\t\t\t\t<atributo>\n");
					xml.append("\t\t\t\t\t\t<nome>");
					xml.append(atrib.getNome());
					xml.append("</nome>\n");
					xml.append("\t\t\t\t\t\t<tipo>");
					xml.append(atrib.getTipo().getDescricao());
					xml.append("</tipo>\n");
					xml.append("\t\t\t\t\t\t<chave-primaria>");
					xml.append(atrib.isChavePrimaria()+"");
					xml.append("</chave-primaria>\n");
					xml.append("\t\t\t\t\t\t<podeSerNulo>");
					xml.append(atrib.isRestrictNull()+"");
					xml.append("</podeSerNulo>\n");
					xml.append("\t\t\t\t\t</atributo>\n");
					
					
				}
				if (tabela.getAtributos().size() != 0) xml.append("\t\t\t\t</atributos>\n");
			}
			
			xml.append("\t\t</tabela>\n");
		}
		if (modelo.quantidadeTabelas() != 0) xml.append("\t</tabelas>\n");
		
		if (modelo.quantidadeRelacionamentos() > 0) xml.append("\t\t<relacionamentos>\n");
		for (int i = 0; i < modelo.quantidadeRelacionamentos() ; i++) {
			Relacionamento r = relacionamentos.get(i);
			
			xml.append("\t\t\t<relationship>\n");
			xml.append("\t\t\t\t<name>");
			xml.append(r.getNome());
			xml.append("</name>\n");
			xml.append("\t\t\t\t<primary-table-name>");
			xml.append(r.getTabela1().getTitulo());
			xml.append("</primary-table-name>\n");
			xml.append("\t\t\t\t<foreing-table-name>");
			xml.append(r.getTabela2().getTitulo());
			xml.append("</foreing-table-name>\n");
		}
		if (modelo.quantidadeRelacionamentos() > 0) xml.append("\t\t</relacionamentos>\n");

		xml.append("</DBModel>\n");
		
		return xml.toString();
		
		
	}

	public static Modelo loadXML(String xml) throws ParserConfigurationException, IOException, SAXException {
		return XMLReader.read(xml);
	}
	
}

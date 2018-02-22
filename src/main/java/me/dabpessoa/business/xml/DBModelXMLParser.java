package me.dabpessoa.business.xml;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.util.Tag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class DBModelXMLParser {

	/*
		 * Gera XML padronizado com base no modelo desenhado no programa.
		 */
	public static StringBuilder generateXML(Modelo modelo) {

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
			xml.append(r.getLeftTable().getTitulo());
			xml.append("</primary-table-name>\n");
			xml.append("\t\t\t\t<foreing-table-name>");
			xml.append(r.getRightTable().getTitulo());
			xml.append("</foreing-table-name>\n");
		}
		if (modelo.quantidadeRelacionamentos() > 0) xml.append("\t\t</relacionamentos>\n");

		xml.append("</DBModel>\n");
		
		return xml;
		
		
	}

	// TODO FIXME implementar e testar
	public static Modelo loadXML(String xml) throws ParserConfigurationException, IOException, SAXException {


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new ByteArrayInputStream(xml.getBytes()));

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

//		NodeList nList = doc.getElementsByTagName("DBModel");
		NodeList nList = doc.getDocumentElement().getChildNodes();

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//					Element eElement = (Element) nNode;
//
//					System.out.println("Staff id : " + eElement.getAttribute("id"));
//					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//				}
//			}

		}

		return null;
		
	}
	
	public StringBuilder teste() {
		
		Tag tagRaiz = new Tag("DBModel", null, null);
		tagRaiz.addChildTag(new Tag("backGround", null, tagRaiz));
		tagRaiz.addChildTag(new Tag("tableList", null, tagRaiz.getChildTagByName("backGround")));
		
		return tagRaiz.genarateStringByThis();
		
	}
	
}

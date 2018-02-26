package me.dabpessoa.util.xml;

import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.bean.TabelaModelo;
import me.dabpessoa.business.xml.DBModelXMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {

    public static Modelo read(String xml) throws IOException, SAXException, ParserConfigurationException {

        Modelo modelo = new Modelo(null, new ArrayList<>(), new ArrayList<>());

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        //Build Document
        Document document = dBuilder.parse(new ByteArrayInputStream(xml.getBytes()));

        //Here comes the root node
        Element root = document.getDocumentElement();

        NodeList tabelasList = document.getElementsByTagName("tabelas");
        if (tabelasList != null && tabelasList.getLength() > 0) tabelasList = tabelasList.item(0).getChildNodes();

        NodeList relacionamentosList = document.getElementsByTagName("relacionamentos");
        if (relacionamentosList != null && relacionamentosList.getLength() > 0) relacionamentosList = relacionamentosList.item(0).getChildNodes();

        if (tabelasList != null) {
            for (int i = 0 ; i < tabelasList.getLength() ; i++) {

                Node node = tabelasList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String tituloTabela = element.getElementsByTagName("titulo").item(0).getTextContent();
                    String larguraTabela = element.getElementsByTagName("largura").item(0).getTextContent();
                    String alturaTabela = element.getElementsByTagName("altura").item(0).getTextContent();
                    String posicaoXTabela = element.getElementsByTagName("posicaoX").item(0).getTextContent();
                    String posicaoYTabela = element.getElementsByTagName("posicaoY").item(0).getTextContent();

                    Double larguraTabelaDouble = tituloTabela != null ? Double.parseDouble(larguraTabela) : null;
                    Double alturaTabelaDouble = tituloTabela != null ? Double.parseDouble(alturaTabela) : null;
                    Double posicaoXTabelaDouble = tituloTabela != null ? Double.parseDouble(posicaoXTabela) : null;
                    Double posicaoYTabelaDouble = tituloTabela != null ? Double.parseDouble(posicaoYTabela) : null;

                    modelo.adicionarTabela(new Tabela(tituloTabela, new TabelaModelo(larguraTabelaDouble, alturaTabelaDouble, posicaoXTabelaDouble, posicaoYTabelaDouble)));

                }

            }
        }

        if (relacionamentosList != null) {
            // implementar...
        }

        return modelo;

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        Modelo m = new Modelo();
        m.adicionarTabela(new Tabela("Tabela 1", new TabelaModelo(100d, 100d, 201d, 233d)));
        m.adicionarTabela(new Tabela("Tabela 2", new TabelaModelo(100d, 100d, 500d, 555d)));
        m.adicionarTabela(new Tabela("Tabela 3", new TabelaModelo(100d, 100d, 600d, 425d)));

        String xml = DBModelXMLParser.generateXML(m);

        Modelo modelo = XMLReader.read(xml);
        System.out.println(modelo);

    }

}

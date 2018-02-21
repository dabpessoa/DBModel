package me.dabpessoa.business;

import me.dabpessoa.bean.Modelo;
import me.dabpessoa.business.sql.SQLFactory;
import me.dabpessoa.business.xml.DBModelXMLParser;

public class DBModelService {

    public String lerModeloDoDisco(String path) {
        StringBuilder modeloBuilder = ResourceManager.readLinesFromTextFile(path);
        if (modeloBuilder == null) return null;
        return modeloBuilder.toString();
    }

    public String criarArquivoModelo(Modelo modelo) {
        return DBModelXMLParser.generateXML(modelo).toString();
    }

    public String gerarSQL(Modelo modelo) {

        for (int i = 0 ; i < modelo.quantidadeTabelas() ; i++) {
            System.out.println("TITULO: "+modelo.getTabelas().get(i).getTitulo());
        }


        SQLFactory sql = new SQLFactory();
        System.out.println(""+sql.GenerateCreateTable(modelo.getTabelas()).toString());
        System.out.println();
        System.out.println();
        System.out.println(""+sql.GenerateAlterTable(modelo.getRelacionamentos()).toString());


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(sql.GenerateCreateTable(modelo.getTabelas()).toString());
        sqlBuilder.append(sql.GenerateAlterTable(modelo.getRelacionamentos()).toString());

        return sqlBuilder.toString();

    }

}

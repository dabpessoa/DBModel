package me.dabpessoa.business;

import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.business.sql.SQLFactory;
import me.dabpessoa.business.xml.DBModelXMLParser;

import java.util.List;

public class DBModelService {

    public String lerModeloDoDisco(String path) {
        StringBuilder modeloBuilder = ResourceManager.readLinesFromTextFile(path);
        if (modeloBuilder == null) return null;
        return modeloBuilder.toString();
    }

    public String criarArquivoModelo(List<Tabela> tabelas, List<Relacionamento> relacionamentos) {
        DBModelXMLParser parser = new DBModelXMLParser(tabelas, relacionamentos);
        return parser.generateXML().toString();
    }

    public void atualizarTabela(List<Tabela> tabelas, Tabela tabela) {
        for (int i = 0 ; i < tabelas.size() ; i++) {
            if (tabelas.get(i).getId().equalsIgnoreCase(tabela.getId())) {
                tabelas.remove(i);
                tabelas.add(tabela);
            }
        }
    }

    public String gerarSQL(List<Tabela> tabelas, List<Relacionamento> relacionamentos) {

        for (int i = 0 ; i < tabelas.size() ; i++) {
            System.out.println("TITULO: "+tabelas.get(i).getTitulo());
        }


        SQLFactory sql = new SQLFactory();
        System.out.println(""+sql.GenerateCreateTable(tabelas).toString());
        System.out.println();
        System.out.println();
        System.out.println(""+sql.GenerateAlterTable(relacionamentos).toString());


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(sql.GenerateCreateTable(tabelas).toString());
        sqlBuilder.append(sql.GenerateAlterTable(relacionamentos).toString());

        return sqlBuilder.toString();

    }

}

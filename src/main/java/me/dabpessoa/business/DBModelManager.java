package main.java.me.dabpessoa.business;

import main.java.me.dabpessoa.bean.Relationship;
import main.java.me.dabpessoa.bean.Tabela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
// FIXME classe em obras* ainda implementando métodos para substituir a classe controller.
public class DBModelManager {

    private List<Tabela> tabelas;
    private List<Relationship> relationships;

    public DBModelManager() {
        this.tabelas = new ArrayList<Tabela>();
        this.relationships = new ArrayList<Relationship>();
    }

    public void addTable(Tabela tabela) {
        tabelas.add(tabela);
    }

    public void removeTable(Tabela tabela) {
        tabelas.remove(tabela);
    }

    public boolean existsTable(Tabela tabela) {
        return tabelas.contains(tabela);
    }

    public List<Tabela> getTabelas() {
        return Collections.unmodifiableList(tabelas);
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }

}

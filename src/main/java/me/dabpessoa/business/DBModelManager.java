package main.java.me.dabpessoa.business;

import main.java.me.dabpessoa.bean.Relationship;
import main.java.me.dabpessoa.bean.Tabela;
import main.java.me.dabpessoa.dao.DataBaseManager;

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
    private DataBaseManager dataBaseManager;

    public DBModelManager() {
        this.tabelas = new ArrayList<Tabela>();
        this.relationships = new ArrayList<Relationship>();
    }

    public void addTabela(Tabela tabela) {
        tabelas.add(tabela);
    }

    public void removerTabela(Tabela tabela) {
        tabelas.remove(tabela);
    }

    public boolean existeTabela(Tabela tabela) {
        return tabelas.contains(tabela);
    }

    public List<Tabela> getTabelas() {
        return Collections.unmodifiableList(tabelas);
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public void setDataBaseManager(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

}

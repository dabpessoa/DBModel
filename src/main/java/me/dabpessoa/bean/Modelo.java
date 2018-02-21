package me.dabpessoa.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modelo {

    private String nome;
    private List<Tabela> tabelas;
    private List<Relacionamento> relacionamentos;

    public Modelo() {
    }

    public Modelo(String nome, List<Tabela> tabelas, List<Relacionamento> relacionamentos) {
        this.nome = nome;
        this.tabelas = tabelas;
        this.relacionamentos = relacionamentos;
    }

    public int quantidadeTabelas() {
        return getTabelas() != null ? getTabelas().size() : 0;
    }

    public int quantidadeRelacionamentos() {
        return getRelacionamentos() != null ? getRelacionamentos().size() : 0;
    }

    public void adicionarTabela(Tabela tabela) {
        if (tabelas == null) tabelas = new ArrayList<>();
        this.tabelas.add(tabela);
    }

    public void removerTabela(Tabela tabela) {
        if (tabelas == null) return;
        this.tabelas.remove(tabela);
    }

    public void atualizarTabela(Tabela tabela) {
        for (int i = 0 ; i < tabelas.size() ; i++) {
            if (tabelas.get(i).getId().equalsIgnoreCase(tabela.getId())) {
                tabelas.remove(i);
                tabelas.add(tabela);
            }
        }
    }

    public void adicionarRelacionamento(Relacionamento relacionamento) {
        if (relacionamentos == null) relacionamentos = new ArrayList<>();
        this.relacionamentos.add(relacionamento);
    }

    public void removerRelacionamento(Relacionamento relacionamento) {
        if (relacionamentos == null) return;
        relacionamentos.remove(relacionamento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tabela> getTabelas() {
        if (tabelas == null) return null;
        return Collections.unmodifiableList(tabelas);
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }

    public List<Relacionamento> getRelacionamentos() {
        if (relacionamentos == null) return null;
        return Collections.unmodifiableList(relacionamentos);
    }

    public void setRelacionamentos(List<Relacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modelo)) return false;

        Modelo modelo = (Modelo) o;

        if (tabelas != null ? !tabelas.equals(modelo.tabelas) : modelo.tabelas != null) return false;
        return relacionamentos != null ? relacionamentos.equals(modelo.relacionamentos) : modelo.relacionamentos == null;
    }

    @Override
    public int hashCode() {
        int result = tabelas != null ? tabelas.hashCode() : 0;
        result = 31 * result + (relacionamentos != null ? relacionamentos.hashCode() : 0);
        return result;
    }

}

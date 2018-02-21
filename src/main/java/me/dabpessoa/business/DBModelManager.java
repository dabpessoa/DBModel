package me.dabpessoa.business;

import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.business.xml.XMLFilter;
import me.dabpessoa.dao.DataBase;
import me.dabpessoa.dao.DataBaseManager;
import me.dabpessoa.gui.PrincipalUI;
import me.dabpessoa.gui.RelationshipUI;
import me.dabpessoa.gui.SqlUI;
import me.dabpessoa.util.FileUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
public class DBModelManager {

    private List<Tabela> tabelas;
    private List<Relacionamento> relacionamentos;

    private PrincipalUI principalUI;
    private DataBaseManager dataBaseManager;
    private DBModelService dbModelService;

    public DBModelManager() {
        this.tabelas = new ArrayList<Tabela>();
        this.relacionamentos = new ArrayList<Relacionamento>();
        this.dataBaseManager = new DataBaseManager();
        this.dbModelService = new DBModelService();
    }

    public void run() {
        principalUI = new PrincipalUI(this);
        principalUI.createAndShowGUI();
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
        dbModelService.atualizarTabela(tabelas, tabela);
    }

    public void criarRelacionamento() {
        RelationshipUI relationShip = RelationshipUI.getInstance(principalUI.getListaTabelas());
        relationShip.setListener(principalUI);
    }

    public void gerarEMostrarSQL() {
        String sql = dbModelService.gerarSQL(tabelas, relacionamentos);
        SqlUI.getInstance(this, sql);
    }

    public void adicionarRelacionamento(Relacionamento relacionamento) {
        if (relacionamentos == null) relacionamentos = new ArrayList<>();
        this.relacionamentos.add(relacionamento);
    }

    public void removerRelacionamento(Relacionamento relacionamento) {
        if (relacionamentos == null) return;
        relacionamentos.remove(relacionamento);
    }

    public void salvarArquivoModelo() {
        String modeloString = dbModelService.criarArquivoModelo(tabelas, relacionamentos);
        String extensions[] = {".xml"};
        FileUtils.createFileChooser(this.principalUI.getFrame(), "Salvar Modelo", extensions, modeloString);
    }

    public void carregarModeloXML() {

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new XMLFilter());
        fc.setDialogTitle("Abrir Modelo");

        int returnVal = fc.showOpenDialog(this.principalUI.getFrame());

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            String modeloXMLString = dbModelService.lerModeloDoDisco(fc.getSelectedFile().getPath());
            this.principalUI.readModel(modeloXMLString);

        }

    }

    public void exportarSQL(String sql) {
        /*
         * Método para abrir caixa de diálogo
         * para ser escolhido o local onde será
         * salvo o arquivo ".sql"
         */

        String[] extensions = {".sql"};
        FileUtils.createFileChooser(this.principalUI.getFrame(), "Gerar SQL", extensions, sql);
    }

    public void criarBanco(String sql) {

        try {
            dataBaseManager.setBanco(DataBase.POSTGRE);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        boolean status = dataBaseManager.createBanco(new StringBuilder(sql));
        if (!status) JOptionPane.showMessageDialog(this.principalUI.getFrame(), "Comandos DDL executados com sucesso no SGBD configurado!");
        else JOptionPane.showMessageDialog(this.principalUI.getFrame(), "Falha ao executar comandos!");

    }

    public List<Tabela> getTabelas() {
        return Collections.unmodifiableList(tabelas);
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }

    public List<Relacionamento> getRelacionamentos() {
        return Collections.unmodifiableList(relacionamentos);
    }

    public void setRelacionamentos(List<Relacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    public PrincipalUI getPrincipalUI() {
        return principalUI;
    }

    public void setPrincipalUI(PrincipalUI principalUI) {
        this.principalUI = principalUI;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public void setDataBaseManager(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

}

package me.dabpessoa.business;

import me.dabpessoa.bean.Modelo;
import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.business.xml.DBModelXMLParser;
import me.dabpessoa.business.xml.XMLFilter;
import me.dabpessoa.dao.DataBase;
import me.dabpessoa.dao.DataBaseManager;
import me.dabpessoa.gui.PrincipalUI;
import me.dabpessoa.gui.RelationshipUI;
import me.dabpessoa.gui.SqlUI;
import me.dabpessoa.util.FileUtils;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
public class DBModelManager {

    private Modelo modelo;

    private PrincipalUI principalUI;
    private DataBaseManager dataBaseManager;
    private DBModelService dbModelService;

    public DBModelManager() {
        this.modelo = new Modelo();
        this.dataBaseManager = new DataBaseManager();
        this.dbModelService = new DBModelService();
    }

    public void run() {
        principalUI = new PrincipalUI(this);
        principalUI.createAndShowGUI();
    }

    public void adicionarTabela(Tabela tabela) {
       modelo.adicionarTabela(tabela);
    }

    public void removerTabela(Tabela tabela) {
        modelo.removerTabela(tabela);
    }

    public void atualizarTabela(Tabela tabela) {
        modelo.atualizarTabela(tabela);
    }

    public void mostrarJanelaRelacionamento() {
        RelationshipUI relationShip = RelationshipUI.show(principalUI.getListaTabelas());
        relationShip.setRelacionamentoListener(principalUI);
    }

    public void gerarEMostrarSQL() {
        String sql = dbModelService.gerarSQL(modelo);
        SqlUI.getInstance(this, sql);
    }

    public void adicionarRelacionamento(Relacionamento relacionamento) {
        modelo.adicionarRelacionamento(relacionamento);
    }

    public void removerRelacionamento(Relacionamento relacionamento) {
        modelo.removerRelacionamento(relacionamento);
    }

    public void salvarArquivoModelo() {
        String modeloString = dbModelService.criarArquivoModelo(modelo);
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
            try {
                this.modelo = DBModelXMLParser.loadXML(modeloXMLString);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            this.principalUI.carregarModelo(modelo);

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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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

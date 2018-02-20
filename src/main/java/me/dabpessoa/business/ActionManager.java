package me.dabpessoa.business;

import me.dabpessoa.bean.Relacionamento;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.bean.enums.DBModelAction;
import me.dabpessoa.business.listeners.DBModelActionListener;

public class ActionManager implements DBModelActionListener {

    private DBModelManager dbModelManager;

    public ActionManager(DBModelManager dbModelManager) {
        this.dbModelManager = dbModelManager;
    }

    @Override
    public void doAction(Object obj, DBModelAction acao) {

        switch (acao) {
            case ADICIONAR: {

                Tabela tabela = (Tabela) obj;
                dbModelManager.adicionarTabela(tabela);

            } break;
            case REMOVER: {

                Tabela tabela = (Tabela) obj;
                dbModelManager.removerTabela(tabela);

            } break;
            case EDITAR: {

                Tabela tabela = (Tabela) obj;
                dbModelManager.atualizarTabela(tabela);

            } break;
            case RELACAO_TABELAS: {

                dbModelManager.criarRelacionamento();

            } break;
            case GERAR_SQL: {

                dbModelManager.gerarEMostrarSQL();

            } break;
            case ADD_RELATIONSHIP: {

                Relacionamento relacionamento = (Relacionamento) obj;
                dbModelManager.adicionarRelacionamento(relacionamento);

            } break;
            case SALVAR_MODELO: {

                dbModelManager.salvarArquivoModelo();

            } break;
            case CARREGAR_MODELO: {

                dbModelManager.carregarModeloXML();

            } break;
            case EXPORT_SQL: {

                dbModelManager.exportarSQL();

            } break;
            case CRIAR_BANCO: {

                String sql = (String) obj;
                dbModelManager.criarBanco(sql);

            } break;
            default: {
                throw new RuntimeException("Ação inválida.");
            }
        }

    }

    public DBModelManager getDbModelManager() {
        return dbModelManager;
    }

    public void setDbModelManager(DBModelManager dbModelManager) {
        this.dbModelManager = dbModelManager;
    }

}

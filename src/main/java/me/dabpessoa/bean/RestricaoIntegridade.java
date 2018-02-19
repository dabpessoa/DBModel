package me.dabpessoa.bean;

public class RestricaoIntegridade {
	
	private String nomeTabela;
	private String nomeAtributo;
	
	public RestricaoIntegridade() {}

	public void setTabelaNomeFK(String tabelaNomeFK) {
		this.nomeTabela = tabelaNomeFK;
	}

	public String getTabelaNomeFK() {
		return nomeTabela;
	}

	public void setAtributoFK(String atributoFK) {
		this.nomeAtributo = atributoFK;
	}

	public String getAtributoFK() {
		return nomeAtributo;
	}

}

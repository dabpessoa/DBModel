package me.dabpessoa.bean;

import me.dabpessoa.bean.enums.TipoAtributo;

public class Atributo {
	
	private String nome;
	private TipoAtributo tipo;
	private boolean primaryKey;
	private boolean importedKey;
	private boolean exportedKey;
	private boolean restrictNull;
	private boolean uniqueKey;
	private RestricaoIntegridade integritRestriction;
	
	public Atributo() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAtributo getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtributo tipo) {
		this.tipo = tipo;
	}

	public boolean isChavePrimaria() {
		return primaryKey;
	}

	public void setChavePrimaria(boolean chavePrimaria) {
		this.primaryKey = chavePrimaria;
	}

	public void setRestrictNull(boolean restrictNull) {
		this.restrictNull = restrictNull;
	}

	public boolean isRestrictNull() {
		return restrictNull;
	}
	
	public RestricaoIntegridade getIntegritRestriction() {
		return integritRestriction;
	}
	
	public void setIntegritRestriction(RestricaoIntegridade integritRestriction) {
		this.integritRestriction = integritRestriction;
	}

	public void setImportedKey(boolean importedKey) {
		this.importedKey = importedKey;
	}

	public boolean isImportedKey() {
		return importedKey;
	}

	public void setExportedKey(boolean exportedKey) {
		this.exportedKey = exportedKey;
	}

	public boolean isExportedKey() {
		return exportedKey;
	}

	public void setUniqueKey(boolean uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public boolean isUniqueKey() {
		return uniqueKey;
	}
	
}

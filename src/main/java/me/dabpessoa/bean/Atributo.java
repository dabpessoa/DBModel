package main.java.me.dabpessoa.bean;

import main.java.me.dabpessoa.bean.enums.AttributeDataType;

public class Atributo {
	
	private String nome;
	private AttributeDataType type;
	private boolean primaryKey;
	private boolean importedKey;
	private boolean exportedKey;
	private boolean restrictNull;
	private boolean uniqueKey;
	private IntegritRestriction integritRestriction;
	
	public Atributo() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AttributeDataType getType() {
		return type;
	}

	public void setType(AttributeDataType type) {
		this.type = type;
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
	
	public IntegritRestriction getIntegritRestriction() {
		return integritRestriction;
	}
	
	public void setIntegritRestriction(IntegritRestriction integritRestriction) {
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

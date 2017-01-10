package me.dabpessoa.bean;

public class Atributo {

	public static final String VARCHAR = "varchar";
	public static final String INT = "int";
	public static final String DOUBLE = "double";
	public static final String BIT = "bit";
	
	private String nome;
	private Tipo tipo;
	private boolean primaryKey;
	private boolean importedKey;
	private boolean exportedKey;
	private boolean restrictNull;
	private boolean uniqueKey;
	private IntegritRestriction integritRestriction;
	/*
	 * tem que ser um objeto para poder guardar a informa��o de qual coluna esta referenciando
	 */
	
	public Atributo() {
		// TODO Auto-generated constructor stub
		this.integritRestriction = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
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

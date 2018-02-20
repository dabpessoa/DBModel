package me.dabpessoa.bean;

public class Relacionamento {

	private String nome;
	private Tabela leftTable;
	private Tabela rightTable;
	private String cardinalidade;
	
	public Relacionamento() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCardinalidade(String cardinalidade) {
		this.cardinalidade = cardinalidade;
	}

	public String getCardinalidade() {
		return cardinalidade;
	}

	public void setLeftTable(Tabela leftTable) {
		this.leftTable = leftTable;
	}

	public Tabela getLeftTable() {
		return leftTable;
	}

	public void setRightTable(Tabela rightTable) {
		this.rightTable = rightTable;
	}

	public Tabela getRightTable() {
		return rightTable;
	}
	
	
	
}

package me.dabpessoa.bean;

public class Relacionamento {

	private String nome;
	private Tabela tabela1;
	private Tabela tabela2;
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

	public Tabela getTabela1() {
		return tabela1;
	}

	public void setTabela1(Tabela tabela1) {
		this.tabela1 = tabela1;
	}

	public Tabela getTabela2() {
		return tabela2;
	}

	public void setTabela2(Tabela tabela2) {
		this.tabela2 = tabela2;
	}

}

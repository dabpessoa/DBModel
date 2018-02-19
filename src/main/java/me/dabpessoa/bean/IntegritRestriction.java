package me.dabpessoa.bean;

public class IntegritRestriction {
	
	private String tableName;
	private String atributoName;
	
	public IntegritRestriction() {
		// TODO Auto-generated constructor stub
	}

	public void setTabelaNomeFK(String tabelaNomeFK) {
		this.tableName = tabelaNomeFK;
	}

	public String getTabelaNomeFK() {
		return tableName;
	}

	public void setAtributoFK(String atributoFK) {
		this.atributoName = atributoFK;
	}

	public String getAtributoFK() {
		return atributoName;
	}

}

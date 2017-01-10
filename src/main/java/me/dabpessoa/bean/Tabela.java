package main.java.me.dabpessoa.bean;

import java.util.ArrayList;
import java.util.List;

public class Tabela {

	private String id;
	private String titulo;
	private List<Atributo> atributos;
	
	public Tabela() {
		this.atributos = new ArrayList<Atributo>();
	}

	public Tabela(String titulo, List<Atributo> atributos) {
		super();
		this.titulo = titulo;
		this.atributos = atributos;
	}
	
	public List<Atributo> getChavesPrimaria() {
		List<Atributo> chaves = new ArrayList<Atributo>();
		for (int i = 0 ; i < this.atributos.size() ; i++) {
			if (atributos.get(i).isChavePrimaria()) {
				chaves.add(atributos.get(i));
			}
		} return chaves;
	}
	
	public List<Atributo> getExportedKeys() {
		List<Atributo> exportedKeys = new ArrayList<Atributo>();
		for (int i = 0 ; i < this.atributos.size() ; i++) {
			if (atributos.get(i).isExportedKey()) {
				exportedKeys.add(atributos.get(i));
			}
		} return exportedKeys;
	}
	
	public List<Atributo> getImportedKeys() {
		List<Atributo> importedKeys = new ArrayList<Atributo>();
		for (int i = 0 ; i < this.atributos.size() ; i++) {
			if (atributos.get(i).isImportedKey()) {
				importedKeys.add(atributos.get(i));
			}
		} return importedKeys;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}

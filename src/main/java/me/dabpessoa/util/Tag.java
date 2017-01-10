package main.java.me.dabpessoa.util;

import java.util.ArrayList;
import java.util.List;

public class Tag {

	private String value;
	private String tagAbertura;
	private String tagFechamento;
	private List<Tag> childs;
	private Tag dad;
	
	public Tag(String name, String value, Tag dad) {
		this.tagAbertura = "<"+name+">";
		this.tagFechamento = "</"+name+">";
		this.childs = new ArrayList<Tag>();
		this.dad = dad;
		this.value = value;
	}
	
	public void addChildTag(Tag child) {
		this.childs.add(child);
	}
	
	public Tag getChildTagByName(String nome) {
		
		for (int i = 0 ; i < childs.size() ; i++) {
			if (childs.get(i).getTagAbertura().equals("<"+nome+">")) {
				return childs.get(i);
			}
		}
		return null;
		
	}

	public String getTagAbertura() {
		return tagAbertura;
	}

	public void setTagAbertura(String tagAbertura) {
		this.tagAbertura = tagAbertura;
	}

	public String getTagFechamento() {
		return tagFechamento;
	}

	public void setTagFechamento(String tagFechamento) {
		this.tagFechamento = tagFechamento;
	}

	public List<Tag> getChilds() {
		return childs;
	}

	public void setChilds(List<Tag> childs) {
		this.childs = childs;
	}

	public Tag getDad() {
		return dad;
	}

	public void setDad(Tag dad) {
		this.dad = dad;
	}
	
	public StringBuilder genarateStringByThis() {
		
		
		StringBuilder xml = new StringBuilder();
		
		List<Tag> tagsTemp = new ArrayList<Tag>();
		
		xml.append(this.tagAbertura);
		
		tagsTemp = childs;
		Tag tagTemp = null;
		if (tagsTemp != null)
		for (int i = 0 ; i < childs.size() ; i++) {
			while(!tagsTemp.isEmpty() || tagsTemp != null) {
				System.out.println("teste");
				xml.append(tagsTemp.get(i).getTagAbertura());
				if (tagsTemp.get(i).getChilds() == null || tagsTemp.get(i).getChilds().isEmpty())
				tagTemp = tagsTemp.get(i);
				else tagsTemp = tagsTemp.get(i).getChilds();
			}
			while(tagTemp.getDad() != null) {
				xml.append(tagTemp.getTagFechamento());
				tagTemp = tagTemp.getDad();
			}
		}
		xml.append(this.tagFechamento);

		return xml;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}

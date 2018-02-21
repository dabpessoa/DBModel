package me.dabpessoa.business.listeners;

import java.util.List;

public interface RelacionamentoListener {

	void drawLines(String objectName, String t1Name, String t2Name, String cardinalidade, List<String> primaryAtributoNames, List<String> foreignAtributoNames);
	
}

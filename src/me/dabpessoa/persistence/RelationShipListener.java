package me.dabpessoa.persistence;

import java.util.List;

public interface RelationShipListener {

	void drawLines(String objectName, String t1Name, String t2Name, String cardinalidade, List<String> primaryAtributoNames, List<String> foreignAtributoNames);
	
}

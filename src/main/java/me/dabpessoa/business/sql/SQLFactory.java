package me.dabpessoa.business.sql;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.RestricaoIntegridade;
import me.dabpessoa.bean.Relationship;
import me.dabpessoa.bean.Tabela;

import java.util.ArrayList;
import java.util.List;

public class SQLFactory {

	private List<Tabela> listaTabelas;
	private List<Relationship> listaRelacionamentos;
	private StringBuilder codigoSQLGerado;
	
	
	public SQLFactory (){
		this.listaTabelas = new ArrayList<Tabela>();
		this.listaRelacionamentos = new ArrayList<Relationship>();
		this.codigoSQLGerado = new StringBuilder();
	}
	
	public SQLFactory(List<Tabela> tabelas){
		this.listaTabelas = tabelas;
		this.codigoSQLGerado = new StringBuilder();
		
	}
	
	public SQLFactory(List<Tabela> tabelas, List<Relationship> relationships) {
		this.listaTabelas = tabelas;
		this.listaRelacionamentos = relationships;
		this.codigoSQLGerado = new StringBuilder();
	}
	
	public StringBuilder GenerateCreateTable (List<Tabela> tabelas){
		listaTabelas = tabelas;
		
		codigoSQLGerado = new StringBuilder();
		
		for (Tabela t: listaTabelas){
			codigoSQLGerado.append("CREATE TABLE "+ t.getTitulo() +"(\n");
			
			if (!t.getAtributos().isEmpty()) {
				List<Atributo> temp = new ArrayList<Atributo>();
				
				for (int i = 0; i < t.getAtributos().size(); i++){
					if(i+1 != t.getAtributos().size()){
						codigoSQLGerado.append("\t"+t.getAtributos().get(i).getNome()+" "+t.getAtributos().get(i).getTipo().getDescricao()+",\n");
					}	
					else{
						codigoSQLGerado.append("\t"+t.getAtributos().get(i).getNome()+" "+t.getAtributos().get(i).getTipo().getDescricao()+",\n");
					}
					
					if(t.getAtributos().get(i).isChavePrimaria()){
						temp.add(t.getAtributos().get(i));
					}
				}
				
				if (!temp.isEmpty()) {
					codigoSQLGerado.append("\tCONSTRAINT PK_"+temp.get(0).getNome()+" PRIMARY KEY (");
				}
				for (int i = 0 ; i < temp.size() ; i ++) {
					if (i+1 != temp.size()) codigoSQLGerado.append(temp.get(i).getNome()+", ");
					else codigoSQLGerado.append(temp.get(i).getNome()+")");
				}
				
			}
			codigoSQLGerado.append("\n);\n\n");
		}
		return codigoSQLGerado;
	}	
	
	public StringBuilder GenerateAlterTable (List<Relationship> relacionamentos){
		
		listaRelacionamentos = relacionamentos;
		codigoSQLGerado = new StringBuilder();
		
		for (int i = 0; i < listaRelacionamentos.size(); i++){
				
			Relationship r = listaRelacionamentos.get(i);
			
			List<Atributo> atribs1 = r.getLeftTable().getAtributos();
			List<Atributo> atribs2 = r.getRightTable().getAtributos();
			
			for (int j = 0 ; j < atribs1.size() ; j++) {
				RestricaoIntegridade fk = atribs1.get(j).getIntegritRestriction();
				if ( fk != null ) {
					codigoSQLGerado.append("ALTER TABLE "+r.getLeftTable().getTitulo()+" ADD CONSTRAINT FK_"+atribs1.get(j).getNome()+" FOREIGN KEY ("+atribs1.get(j).getNome()+") REFERENCES "+fk.getTabelaNomeFK()+" ("+fk.getAtributoFK()+");\n ");
				}
				
			}
			
			for (int j = 0 ; j < atribs2.size() ; j++) {
				RestricaoIntegridade fk = atribs2.get(j).getIntegritRestriction();
				if ( fk != null ) {
					codigoSQLGerado.append("ALTER TABLE "+r.getRightTable().getTitulo()+" ADD CONSTRAINT FK_"+atribs2.get(j).getNome()+" FOREIGN KEY ("+atribs2.get(j).getNome()+") REFERENCES "+fk.getTabelaNomeFK()+" ("+fk.getAtributoFK()+");\n ");
				}
			}
			
			
			
		}
		return codigoSQLGerado;
	}
	
}

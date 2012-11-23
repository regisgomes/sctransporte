package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Entity
public class Tipo extends GenericModel{

	private String nome;
	
	@Id
    @GeneratedValue
    public Long id;

    public Long getId() {
        return id;
    }

    @Override
    public Object _key() {
        return getId();
    }
    
    public Tipo(){
    	
    }

	public Tipo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

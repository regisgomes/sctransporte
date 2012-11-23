package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Entity
public class Cargo extends GenericModel{
	
	public static final Cargo MOTORISTA = new Cargo(new Long(1));

	private String nome;
	private Double salario;
	
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
	
	public Cargo(Long id) {
		this.id = id;
	}
	
	public Cargo(String nome, Double salario) {
		super();
		this.nome = nome;
		this.salario = salario;
	}
	
	public Cargo(){
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
}

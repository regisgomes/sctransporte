package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Cargo extends Model{
	
	public static final Cargo MOTORISTA = new Cargo(new Long(1));

	private String nome;
	private Double salario;
	
	public Cargo(Long id) {
		this.id = id;
	}
	
	public Cargo(String nome, Double salario) {
		super();
		this.nome = nome;
		this.salario = salario;
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

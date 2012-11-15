package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.GenericModel;

@Entity
public class Funcionario extends GenericModel{

	 @Id
	 @GeneratedValue
	 private Long id;
	 
	 @ManyToOne
	 @JoinColumn (name = "id_usuario")
	 private Usuario usuario;
	 
	 @ManyToOne
	 @JoinColumn (name = "id_cargo")
	 private Cargo cargo;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	 
	 
}

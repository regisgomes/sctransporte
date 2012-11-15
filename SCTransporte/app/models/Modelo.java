/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

/**
 * @author rafaelholanda90
 *
 */
@Entity
public class Modelo extends GenericModel {
	
	@Id
    @GeneratedValue
    public Long id;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_marca")
	private Marca marca;
	
	private String nome;
	
	public Long getId() {
		return id;
	}
	
	@Override
	public Object _key() {
		return getId();
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

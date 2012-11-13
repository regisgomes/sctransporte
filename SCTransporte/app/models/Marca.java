/**
 * 
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * @author rafaelholanda90
 *
 */
@Entity
public class Marca extends Model {

	private String nome;

	public Marca(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

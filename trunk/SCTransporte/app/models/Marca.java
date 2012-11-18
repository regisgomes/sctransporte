/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.GenericModel;

/**
 * @author rafaelholanda90
 *
 */
@Entity
public class Marca extends GenericModel {

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

	public Marca(String nome) {
		this.nome = nome;
	}
	
	public Marca() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

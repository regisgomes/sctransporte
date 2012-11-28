/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

/**
 * @author rafaelholanda90
 *
 */
@Entity
public class Usuario extends GenericModel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String login;
	
	private String pass;
	
	private String nome;
	
	private String cpf;
	
	private String endereco;
	
	private String telefone;
	
	public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	public Usuario(String login, String pass, String nome, String cpf,
			String endereco, String telefone) {
		this.login = login;
		this.pass = pass;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Usuario(){
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}

package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Cliente extends Model{

	private String empresa;
	private String cnpj;
	private String telefone;
	private String endereco;
	private String email;
	
	public Cliente(String empresa, String cnpj, String telefone,
			String endereco, String email) {
		this.empresa = empresa;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}

package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.GenericModel;

@Entity
public class Entrega extends GenericModel{

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn (name = "id_cliente_destino")
	private Cliente idClienteDestino;

	@ManyToOne
	@JoinColumn (name = "id_cliente_origem")
	private Cliente idClienteOrigem;

	@ManyToOne
	@JoinColumn (name = "id_funcionario")
	private Funcionario idFuncionario;

	//Atributo de referencia para a viagem

	@Temporal (TemporalType.TIMESTAMP)
	private Date data;

	@Column (name="cod_rastreio")
	private String codRastreio;

	private String descricao;

	private Integer volumes;

	private Double valor;

	@ManyToOne
	@JoinColumn (name = "id_viagem")
	private Viagem viagem;
	
	public Entrega(String codRastreio, String descricao, Integer volumes,
			Double valor) {
		this.codRastreio = codRastreio;
		this.descricao = descricao;
		this.volumes = volumes;
		this.valor = valor;
	}

	public Entrega(Long id, Cliente idClienteDestino, Cliente idClienteOrigem,
			Funcionario idFuncionario, Date data, String codRastreio,
			String descricao, Integer volumes, Double valor) {
		super();
		this.id = id;
		this.idClienteDestino = idClienteDestino;
		this.idClienteOrigem = idClienteOrigem;
		this.idFuncionario = idFuncionario;
		this.data = data;
		this.codRastreio = codRastreio;
		this.descricao = descricao;
		this.volumes = volumes;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getIdClienteDestino() {
		return idClienteDestino;
	}

	public void setIdClienteDestino(Cliente idClienteDestino) {
		this.idClienteDestino = idClienteDestino;
	}

	public Cliente getIdClienteOrigem() {
		return idClienteOrigem;
	}

	public void setIdClienteOrigem(Cliente idClienteOrigem) {
		this.idClienteOrigem = idClienteOrigem;
	}

	public Funcionario getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Funcionario idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCodRastreio() {
		return codRastreio;
	}

	public void setCodRastreio(String codRastreio) {
		this.codRastreio = codRastreio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getVolumes() {
		return volumes;
	}

	public void setVolumes(Integer volumes) {
		this.volumes = volumes;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
	
}

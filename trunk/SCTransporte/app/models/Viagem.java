/**
 * 
 */
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

/**
 * @author Rafael Holanda
 *
 */
@Entity
public class Viagem extends GenericModel {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne
	@JoinColumn (name = "id_carro")
	private Carro carro;
	
	@ManyToOne
	@JoinColumn (name = "id_motorista")
	private Funcionario motorista;
	
	@Temporal (TemporalType.TIMESTAMP)
	@Column (name = "data_saida")
	private Date dataSaida;
	
	@Column (name = "quilometragem_inicial")
	private Integer quilometragemInicial;
	
	@Column (name = "quilometragem_final")
	private Integer quilometragemFinal;
	
	@Temporal (TemporalType.TIMESTAMP)
	@Column (name = "data_chegada")
	private Date dataChegada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public Funcionario getMotorista() {
		return motorista;
	}

	public void setMotorista(Funcionario motorista) {
		this.motorista = motorista;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Integer getQuilometragemInicial() {
		return quilometragemInicial;
	}

	public void setQuilometragemInicial(Integer quilometragemInicial) {
		this.quilometragemInicial = quilometragemInicial;
	}

	public Integer getQuilometragemFinal() {
		return quilometragemFinal;
	}

	public void setQuilometragemFinal(Integer quilometragemFinal) {
		this.quilometragemFinal = quilometragemFinal;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	
}

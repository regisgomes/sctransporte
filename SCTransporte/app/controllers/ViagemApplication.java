/**
 * 
 */
package controllers;

import java.util.List;

import javax.persistence.Query;

import models.Cargo;
import models.Carro;
import models.Entrega;
import models.Funcionario;

import play.db.jpa.JPA;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class ViagemApplication extends Controller {

	public static void cadastroViagem() {
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		
		Query q = JPA.em().createQuery("FROM Entrega WHERE viagem is null");
		List<Entrega> entregas = q.getResultList();
		
		render(carros, motoristas, entregas);
	}
	
	public static void cadastroViagem(List<String> erros) {
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo.id", Cargo.MOTORISTA).fetch();
		
		Query q = JPA.em().createQuery("FROM Entrega WHERE viagem is null");
		List<Entrega> entregas = q.getResultList();
		
		render(carros, motoristas, entregas);
	}
	
	public static void cadastrar(Long idCarro, Long idMotorista, String dataSaida, String dataChegada, 
							Integer quilometragemInicial, Integer quilometragemFinal, Integer[] ents) {
			System.out.println("TESTE");
	}
	
}

/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Cargo;
import models.Carro;
import models.Funcionario;
import models.Viagem;
import play.mvc.Controller;

/**
 * @author Rafael Holanda
 *
 */
public class RelatorioViagemApplication extends Controller {

	public static void relatorioViagem(List<Viagem> viagens) {
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		
		render(viagens, carros, motoristas);
	}
	
	public static void buscar(String idCarro, String idMotorista) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("FROM Viagem WHERE 1=1 ");
	
		if (idCarro != null && !idCarro.isEmpty()) {
			Long  idCar = Long.parseLong(idCarro);
			if (idCar != 0)
				hql.append(" AND carro.id = " + idCar);
		}
		
		if (idMotorista != null && !idMotorista.isEmpty()) {
			Long  idMot = Long.parseLong(idMotorista);
			if (idMot != 0)
				hql.append(" AND motorista.id = " + idMot);
		}
			
		List<Viagem> viagens = Viagem.em().createQuery(hql.toString()).getResultList();
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		
		renderTemplate("/app/views/RelatorioViagemApplication/relatorioViagem.html", viagens, carros, motoristas);
	}
	
}

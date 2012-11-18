/**
 * 
 */
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.Cargo;
import models.Carro;
import models.Entrega;
import models.Funcionario;
import models.Viagem;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class ViagemApplication extends Controller {
	
	public static void cadastroViagem(List<String> erros) {
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		
		Query q = Entrega.em().createQuery("FROM Entrega WHERE viagem is null");
		List<Entrega> entregas = q.getResultList();
		
		render(carros, motoristas, entregas, erros);
	}
	
	public static void listaViagem() {
		List<Viagem> viagens = Viagem.all().fetch();
		render(viagens);
	}
	
	public static void cadastrar(Long idCarro, Long idMotorista, String dataSaida, String dataChegada, 
							Integer quilometragemInicial, Integer quilometragemFinal, Long[] ents) {
			
		List<String> erros = validarCamposObg(idCarro, idMotorista, dataSaida, dataChegada,
				quilometragemInicial, quilometragemFinal, ents);
		
		if (erros.isEmpty()) {
			
			Carro veiculo = Carro.findById(idCarro);
			Funcionario motorista = Funcionario.findById(idMotorista);
			
			Viagem viagem = new Viagem();
			viagem.setCarro(veiculo);
			viagem.setMotorista(motorista);
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				viagem.setDataChegada(dateFormat.parse(dataChegada));
				viagem.setDataSaida(dateFormat.parse(dataSaida));
			} catch (ParseException e) {
				erros.add("A data deve estar no formato DD/MM/AAAA!");
				ViagemApplication.cadastroViagem(erros);
				return;
			}
			
			viagem.setQuilometragemInicial(quilometragemInicial);
			viagem.setQuilometragemFinal(quilometragemFinal);
			
			viagem.save();
			
			for (Long idEntrega : ents) {
				Entrega entrega = Entrega.findById(idEntrega);
				entrega.setViagem(viagem);
				entrega.save();
			}
			
			String msgInformation = "Viagem Cadastrada com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		} else {
			ViagemApplication.cadastroViagem(erros);
		}
		
	}

	private static List<String> validarCamposObg(Long idCarro, Long idMotorista,
			String dataSaida, String dataChegada, Integer quilometragemInicial,
			Integer quilometragemFinal, Long[] ents) {
		
		List<String> erros = new ArrayList<String>();
		
		if (idCarro == null || idCarro == 0)
			erros.add("Informe o Veículo!");
		
		if (idMotorista == null || idMotorista == 0)
			erros.add("Informe o Motorista!");
		
		if (dataSaida == null || dataSaida.isEmpty())
			erros.add("Informe a Data de Saída!");
		
		if (dataChegada == null || dataChegada.isEmpty())
			erros.add("Informe a Data de Chegada!");
		
		if (quilometragemInicial == null)
			erros.add("Informe a Quilometragem Inicial!");
		
		if (quilometragemFinal == null)
			erros.add("Informe a Quilometragem Final!");
		
		if (ents == null || ents.length == 0)
			erros.add("Selecione pelo menos uma entrega!");
		
		return erros;
	}
	
}

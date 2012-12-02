/**
 * 
 */
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.collection.PersistentBag;

import models.Cargo;
import models.Carro;
import models.Entrega;
import models.Funcionario;
import models.Viagem;
import play.db.jpa.JPA;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class ViagemApplication extends Controller {
	
	public static void cadastroViagem(Viagem viagem, List<String> erros) {
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		List<Entrega> entregas = null;
		
		viagem = new Viagem();
		Query q = Entrega.em().createQuery("FROM Entrega WHERE viagem is null");
		entregas = q.getResultList();
		
		render(viagem, carros,  motoristas, entregas, erros);
	}
	
	public static void listaViagem() {
		List<Viagem> viagens = Viagem.all().fetch();
		render(viagens);
	}
	
	public static void alterarViagem(String idViagem){
		Long id = Long.parseLong(idViagem);
		Viagem viagem = Viagem.findById(id);
		List<Carro> carros = Carro.all().fetch();
		List<Funcionario> motoristas = Funcionario.find("cargo", Cargo.MOTORISTA).fetch();
		List<Entrega> entregas = null;
		
		Query q = Entrega.em().createQuery("FROM Entrega WHERE viagem is null OR viagem ="+viagem.getId());
		entregas = q.getResultList();
		
		renderTemplate("/app/views/ViagemApplication/cadastroViagem.html", viagem, carros,  motoristas, entregas);
	}
	
	public static void cadastrar(String idViagem, Long idCarro, Long idMotorista, String dataSaida, String dataChegada, 
							Integer quilometragemInicial, Integer quilometragemFinal, Long[] ents) {
			
		List<String> erros = validarCamposObg(idCarro, idMotorista, dataSaida, dataChegada,
				quilometragemInicial, quilometragemFinal, ents);
		Viagem viagem = new Viagem();
		boolean edicao = false;
		
		if (erros.isEmpty()) {
			
			//Editar
			if(idViagem != null && !idViagem.isEmpty()){
				edicao = true;
				Long id = Long.parseLong(idViagem);
				viagem = Viagem.findById(id);
				
				Carro veiculo = Carro.findById(idCarro);
				Funcionario motorista = Funcionario.findById(idMotorista);
				viagem.setCarro(veiculo);
				viagem.setMotorista(motorista);
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					viagem.setDataChegada(dateFormat.parse(dataChegada));
					viagem.setDataSaida(dateFormat.parse(dataSaida));
				} catch (ParseException e) {
					erros.add("A data deve estar no formato DD/MM/AAAA!");
					ViagemApplication.cadastroViagem(viagem, erros);
					return;
				}
				
				viagem.setQuilometragemInicial(quilometragemInicial);
				viagem.setQuilometragemFinal(quilometragemFinal);
				
				viagem.setEntregas(new ArrayList<Entrega>());
				for (Long idEnt : ents) {
					Entrega entrega = Entrega.findById(idEnt);
					entrega.setViagem(viagem);
					viagem.getEntregas().add(entrega);
				}
				
				JPA.em().detach(viagem);
				viagem = viagem.merge();
				viagem.save();
			}
			
			//Inserir
			else{
				Carro veiculo = Carro.findById(idCarro);
				Funcionario motorista = Funcionario.findById(idMotorista);
				
				viagem = new Viagem();
				viagem.setCarro(veiculo);
				viagem.setMotorista(motorista);
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					viagem.setDataChegada(dateFormat.parse(dataChegada));
					viagem.setDataSaida(dateFormat.parse(dataSaida));
				} catch (ParseException e) {
					erros.add("A data deve estar no formato DD/MM/AAAA!");
					ViagemApplication.cadastroViagem(null, erros);
					return;
				}
				
				viagem.setQuilometragemInicial(quilometragemInicial);
				viagem.setQuilometragemFinal(quilometragemFinal);
				
				viagem.setEntregas(new ArrayList<Entrega>());
				for (Long idEnt : ents) {
					Entrega entrega = Entrega.findById(idEnt);
					entrega.setViagem(viagem);
					viagem.getEntregas().add(entrega);
				}
				
				viagem.save();
				
			}
			
			
			String msgInformation = "Viagem " + (edicao ? "Editada" : "Cadastrada") +" com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		} else {
			ViagemApplication.cadastroViagem(null, erros);
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

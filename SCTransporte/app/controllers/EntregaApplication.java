package controllers;

import java.util.Date;
import java.util.List;

import models.Cargo;
import models.Cliente;
import models.Entrega;
import models.Funcionario;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class EntregaApplication extends Controller{
	
	public static void listaEntrega(String msgInformation) {
		List<Entrega> entregas = Entrega.all().fetch();
		render(entregas, msgInformation);
	}
	
	public static void alterarEntrega(String idEntrega){
		Long id = Long.parseLong(idEntrega);
		Entrega entrega = Entrega.findById(id);
		cadastroEntrega(entrega, null);
	}
	
	public static void excluirEntrega(String idEntrega){
		Long id = Long.parseLong(idEntrega);
		Entrega entrega = Entrega.findById(id);
		entrega.delete();
		String msgInformation = "Entrega excluida com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
	public static void cadastroEntrega(Entrega entrega, String msgErro){
		List<Cliente> clientes = Cliente.all().fetch();
		List<Funcionario> funcionarios = Funcionario.all().fetch();
		if(entrega == null){
			entrega = new Entrega();
		}
		render(entrega, clientes, funcionarios, msgErro);
	}
	
	public static void cadastrarEntrega(String idEntrega, Long idClienteDestino, Long idClienteOrigem,
			Long idFuncionario, String codRastreio, String descricao, Integer volumes, Double valor){
		
		Entrega entrega = new Entrega();
		
		//EDITAR
		if(idEntrega != null && !idEntrega.isEmpty()){
			Long id = Long.parseLong(idEntrega);
			entrega = Entrega.findById(id);
			
			//Cliente destino
			if(idClienteDestino != null){
				Cliente cliente = Cliente.findById(idClienteDestino);
				entrega.setIdClienteDestino(cliente);
			}
			else{
				entrega.setIdClienteDestino(null);
			}
			
			//Cliente origem
			if(idClienteOrigem != null){
				Cliente cliente = Cliente.findById(idClienteOrigem);
				entrega.setIdClienteOrigem(cliente);
			}
			else{
				entrega.setIdClienteOrigem(null);
			}
			
			//Funcionario
			if(idFuncionario != null){
				Funcionario funcionario = Funcionario.findById(idFuncionario);
				entrega.setIdFuncionario(funcionario);
			}
			else{
				entrega.setIdFuncionario(null);
			}
			
			//descricao
			entrega.setDescricao(descricao);
			//volumes
			entrega.setVolumes(volumes);
			//valor
			entrega.setValor(valor);
			
			entrega.save();
			String msgInformation = "Entrega editada com sucesso!";
			listaEntrega(msgInformation);
		}
		
		
		//INSERIR
		else{
			if(idClienteDestino != null && idClienteOrigem != null && idFuncionario != null && descricao != null && !descricao.isEmpty() && volumes != null && valor != null){
				
				entrega = new Entrega(descricao, volumes, valor);
				
				Cliente origem = Cliente.findById(idClienteOrigem);
				Cliente destino = Cliente.findById(idClienteDestino);
				
				Funcionario func = Funcionario.findById(idFuncionario);
				
				entrega.setIdClienteDestino(destino);
				entrega.setIdClienteOrigem(origem);
				entrega.setIdFuncionario(func);
				
				entrega.setDescricao(descricao);
				entrega.setVolumes(volumes);
				entrega.setValor(valor);
				
				entrega.setData(new Date());
			
				entrega.save();
			}
			else{
				String msgErro = "Informe todos os dados do formulario";
				cadastroEntrega(entrega, msgErro);
			}
			String msgInformation = "Entrega Cadastrada com sucesso!";
			listaEntrega(msgInformation);
		}
	}
}

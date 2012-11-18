package controllers;

import java.util.Date;
import java.util.List;

import models.Cliente;
import models.Entrega;
import models.Funcionario;
import play.mvc.Controller;

public class EntregaApplication extends Controller{
	
	public static void listaEntrega(String msgInformation) {
		List<Entrega> entregas = Entrega.all().fetch();
		render(entregas, msgInformation);
	}
	
	public static void cadastroEntrega(){
		List<Cliente> clientes = Cliente.all().fetch();
		List<Funcionario> funcionarios = Funcionario.all().fetch();
		render(clientes, funcionarios);
	}
	
	public static void cadastroEntrega(String msgErro){
		List<Cliente> clientes = Cliente.all().fetch();
		List<Funcionario> funcionarios = Funcionario.all().fetch();
		render(clientes, funcionarios, msgErro);
	}

	public static void cadastrarEntrega(Long idClienteDestino, Long idClienteOrigem,
			Long idFuncionario, String codRastreio, String descricao, Integer volumes, Double valor){
		
		if(idClienteDestino != null && idClienteOrigem != null && idFuncionario != null && codRastreio != null 
				&& !codRastreio.isEmpty() && descricao != null && !descricao.isEmpty() && volumes != null && valor != null){
			
			Entrega entrega = new Entrega(codRastreio, descricao, volumes, valor);
			
			Cliente origem = Cliente.findById(idClienteOrigem);
			Cliente destino = Cliente.findById(idClienteDestino);
			
			Funcionario func = Funcionario.findById(idFuncionario);
			
			entrega.setIdClienteDestino(destino);
			entrega.setIdClienteOrigem(origem);
			entrega.setIdFuncionario(func);
			
			entrega.setCodRastreio(codRastreio);
			entrega.setDescricao(descricao);
			entrega.setVolumes(volumes);
			entrega.setValor(valor);
			
			entrega.setData(new Date());
		
			entrega.save();
		}
		else{
			String msgErro = "Informe todos os dados do formulario";
			cadastroEntrega(msgErro);
		}
		String msgInformation = "Entrega Cadastrada com sucesso!";
		listaEntrega(msgInformation);
		
	}
}

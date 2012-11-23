package controllers;

import java.util.List;

import models.Marca;
import models.Tipo;
import play.mvc.Controller;

public class TipoVeiculoApplication extends Controller{
	
	public static void listaTipoVeiculo(){
		List<Tipo> tipos = Tipo.all().fetch();
		render(tipos);
	}

	public static void cadastroTipoVeiculo(){
		render();
	}
	
	public static void alterarTipo(String idTipo){
		Long id = Long.parseLong(idTipo);
		Tipo tipo = Tipo.findById(id);
		cadastroTipoVeiculo(tipo, null);
	}
	
	public static void cadastroTipoVeiculo(Tipo tipo, String msgErro){
		if(tipo == null){
			tipo = new Tipo();
		}
		render(tipo, msgErro);
	}
	
	public static void cadastrarTipo(String nomeTipo, String idTipo){
		//Condiçao para edicao
		if(idTipo != null && !idTipo.isEmpty()){
			Long id = Long.parseLong(idTipo);
			Tipo tipo = Tipo.findById(id);
			tipo.setNome(nomeTipo);
			tipo.save();
		}
		//Condicao para cadastro
		else if (nomeTipo != null && !nomeTipo.isEmpty()) {
			Tipo tipo = new Tipo(nomeTipo);
			tipo.save();
		}
		//Condiçao de erro
		else {
			String msgErro = "Informe o nome para o Tipo!";
			cadastroTipoVeiculo(null, msgErro);
		}
		
		String msgInformation = "Tipo de veiculo cadastrado com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

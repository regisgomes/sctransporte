package controllers;

import java.util.List;

import models.Cargo;
import models.Marca;
import models.Modelo;
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
	
	public static void excluirTipo(String idTipo){
		Long id = Long.parseLong(idTipo);
		Tipo tipo = Tipo.findById(id);
		Modelo modelo = Modelo.find("tipo", tipo).first();
		String msgInformation;
		if(modelo == null){
			tipo.delete();
			msgInformation = "Tipo de Veiculo excluido com sucesso!";
		}else{
			msgInformation = "Este tipo nao pode ser apagado pois ja esta vinculado a um modelo";
		}
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
	public static void cadastroTipoVeiculo(Tipo tipo, String msgErro){
		if(tipo == null){
			tipo = new Tipo();
		}
		render(tipo, msgErro);
	}
	
	public static void cadastrarTipo(String nomeTipo, String idTipo){
		Tipo tipo = new Tipo();
		boolean edicao = false;
		//Condiçao para edicao
		if(idTipo != null && !idTipo.isEmpty()){
			edicao = true;
			Long id = Long.parseLong(idTipo);
			tipo = Tipo.findById(id);
			tipo.setNome(nomeTipo);
			tipo.save();
		}
		//Condicao para cadastro
		else if (nomeTipo != null && !nomeTipo.isEmpty()) {
			tipo = new Tipo(nomeTipo);
			tipo.save();
		}
		//Condiçao de erro
		else {
			String msgErro = "Informe o nome para o Tipo!";
			cadastroTipoVeiculo(tipo, msgErro);
		}
		
		String msgInformation = "Tipo de veiculo " + (edicao ? "editado" : "cadastrado") + " com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

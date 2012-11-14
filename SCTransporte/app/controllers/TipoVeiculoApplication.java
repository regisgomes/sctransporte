package controllers;

import models.Marca;
import models.Tipo;
import play.mvc.Controller;

public class TipoVeiculoApplication extends Controller{

	public static void cadastroTipoVeiculo(){
		render();
	}
	
	public static void cadastroTipoVeiculo(String msgErro){
		render(msgErro);
	}
	
	public static void cadastrarTipo(String nomeTipo){
		if (nomeTipo != null && !nomeTipo.isEmpty()) {
			Tipo tipo = new Tipo(nomeTipo);
			tipo.save();
		} else {
			String msgErro = "Informe o nome para a Marca!";
			cadastroTipoVeiculo(msgErro);
		}
		
		String msgInformation = "Marca Cadastrada com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

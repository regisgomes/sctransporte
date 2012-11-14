package controllers;
import java.util.List;

import models.Cargo;
import play.mvc.Controller;
import sun.applet.resources.MsgAppletViewer;

public class CargoApplication extends Controller{

	public static void cadastroCargo(){
		render();
	}
	
	public static void cadastroCargo(String msgErro){
		render(msgErro);
	}
	
	public static void cadastrarCargo(String nomeCargo, Double salario){
		if (nomeCargo != null && !nomeCargo.isEmpty() && salario != null) {
			Cargo cargo = new Cargo(nomeCargo, salario);
			cargo.save();
		} else {
			String msgErro = "Informe o nome e o salario para o Cargo!";
			cadastroCargo(msgErro);
		}
		
		String msgInformation = "Cargo cadastrado com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

package controllers;
import java.util.List;

import models.Cargo;
import play.mvc.Controller;
import sun.applet.resources.MsgAppletViewer;

public class CargoApplication extends Controller{

	public static void listaCargo(){
		List<Cargo> cargos = Cargo.all().fetch();
		render(cargos);
	}

	public static void alterarCargo(String idCargo){
		Long id = Long.parseLong(idCargo);
		Cargo cargo = Cargo.findById(id);
		cadastroCargo(cargo, null);
	}
	
	public static void excluirCargo(String idCargo){
		Long id = Long.parseLong(idCargo);
		Cargo cargo = Cargo.findById(id);
		cargo.delete();
		String msgInformation = "Cargo excluido com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
	public static void cadastroCargo(Cargo cargo, String msgErro){
		if(cargo == null){
			cargo = new Cargo();
		}
		render(cargo, msgErro);
	}
	
	public static void cadastrarCargo(String idCargo, String nomeCargo, Double salario){
		Cargo cargoAux = new Cargo();
		boolean edicao = false;
		//Editar
		if(idCargo != null && !idCargo.isEmpty()){
			edicao = true;
			Long id = Long.parseLong(idCargo);
			Cargo cargo = Cargo.findById(id);
			cargoAux = cargo;
			
			cargo.setNome(nomeCargo);
			cargo.setSalario(salario);
			cargo.save();
		}
		//Inserir
		else if (nomeCargo != null && !nomeCargo.isEmpty() && salario != null) {
			Cargo cargo = new Cargo(nomeCargo, salario);
			cargo.save();
		} else {
			String msgErro = "Informe o nome e o salario para o Cargo!";
			cadastroCargo(cargoAux, msgErro);
		}
		
		String msgInformation = "Cargo " + (edicao ? "editado" : "cadastrado") + " com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

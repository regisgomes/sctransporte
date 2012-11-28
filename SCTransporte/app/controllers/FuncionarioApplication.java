package controllers;

import java.util.List;

import play.mvc.Controller;
import models.Cargo;
import models.Funcionario;
import models.Usuario;

public class FuncionarioApplication extends Controller{
	
	public static void listaFuncionario(){
		List<Funcionario> func = Funcionario.all().fetch();
		render(func);
	}
	
	public static void alterarFuncionario(String idFuncionario){
		Long id = Long.parseLong(idFuncionario);
		Funcionario funcionario = Funcionario.findById(id);
		cadastroFuncionario(funcionario, null);
	}
	
	public static void cadastroFuncionario(Funcionario funcionario, String msgErro){
		List<Cargo> cargos = Cargo.all().fetch();
		List<Usuario> users = Usuario.all().fetch();
		if(funcionario == null){
			funcionario = new Funcionario();
		}
		render(cargos, users, funcionario, msgErro);
	}
	
	public static void cadastrarFuncionario(String idFuncionario, String idUsuario, String idCargo){
		String msgInformation;
		Usuario user = new Usuario();
		Cargo cargo = new Cargo();
		
		//Editar
		if(!idFuncionario.isEmpty() && idFuncionario != null){	
			//Testar usuario
			if(!idUsuario.isEmpty() && idUsuario != null){
				Long idUser = Long.parseLong(idUsuario);
				user = Usuario.findById(idUser);
			}
			//Testar cargo
			if(!idCargo.isEmpty() && idCargo != null){
				Long idCarg = Long.parseLong(idCargo);
				cargo = Cargo.findById(idCarg);
			}
			Long id = Long.parseLong(idFuncionario);
			Funcionario funcionario = Funcionario.findById(id);
			funcionario.setCargo(cargo);
			funcionario.setUsuario(user);
			funcionario.save();
			msgInformation = "Usuario editado com sucesso!";
		}
		
		//Inserir
		else{
			cargo = Cargo.findById(idCargo);
			user = Usuario.findById(idUsuario);
			
			Funcionario funcionario = new Funcionario();
			funcionario.setCargo(cargo);
			funcionario.setUsuario(user);
			
			funcionario.save();
			msgInformation = "Usuario cadastrado com sucesso!";
		}
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

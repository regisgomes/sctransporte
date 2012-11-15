package controllers;

import java.util.List;

import play.mvc.Controller;
import models.Cargo;
import models.Funcionario;
import models.Usuario;

public class FuncionarioApplication extends Controller{
	
	public static void cadastroFuncionario(){
		List<Cargo> cargos = Cargo.all().fetch();
		List<Usuario> users = Usuario.all().fetch();
		render(cargos, users);
	}
	
	public static void cadastroFuncionario(String msgErro){
		List<Cargo> cargos = Cargo.all().fetch();
		List<Usuario> users = Usuario.all().fetch();
		render(cargos, users, msgErro);
	}
	
	public static void cadastrarFuncionario(long idUsuario, long idCargo){
		Cargo cargo = Cargo.findById(idCargo);
		Usuario user = Usuario.findById(idUsuario);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCargo(cargo);
		funcionario.setUsuario(user);
		
		funcionario.save();
		String msgInformation = "Usuario cadastrado com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
	

}

package controllers;


import java.util.List;
import play.mvc.Controller;
import models.Usuario;
import sun.applet.resources.MsgAppletViewer;

public class UsuarioApplication extends Controller{
	
	public static void cadastroUsuario(){
		render();
	}
	
	public static void cadastroUsuario(String msgErro){
		render(msgErro);
	}
	
	public static void cadastrarUsuario(String login, String pass, String nome, String cpf,
			String endereco, String telefone){
		if(login != null && !login.isEmpty() && pass != null && !pass.isEmpty()){
			Usuario user = new Usuario(login, pass, nome, cpf, endereco, telefone);
			user.save();
		}
		else{
			String msgErro = "Informe um login e uma senha para o usuario";
			cadastroUsuario(msgErro);
		}
		String msgInformation = "Usuario cadastrado com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}

}

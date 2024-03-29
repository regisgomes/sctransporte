package controllers;


import java.util.List;
import play.mvc.Controller;
import models.Cargo;
import models.Funcionario;
import models.Usuario;
import sun.applet.resources.MsgAppletViewer;

public class UsuarioApplication extends Controller{
	
	public static void listaUsuario(){
		List<Usuario> users = Usuario.all().fetch();
		render(users);
	}
	
	public static void alterarUsuario(String idUsuario){
		Long id = Long.parseLong(idUsuario);
		Usuario usuario = Usuario.findById(id);
		cadastroUsuario(usuario, null);
	}
	
	public static void excluirUsuario(String idUsuario){
		Long id = Long.parseLong(idUsuario);
		Usuario usuario = Usuario.findById(id);
		Funcionario funcionario = Funcionario.find("usuario", usuario).first();
		String msgInformation;
		if(funcionario == null){
			usuario.delete();
			msgInformation = "Usuario excluido com sucesso!";
		}else{
			msgInformation = "Este usuario nao pode ser apagado pois ja esta vinculado a um funcionario";
		}
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
	public static void cadastroUsuario(Usuario usuario, String msgErro){
		if(usuario == null){
			usuario = new Usuario();
		}
		render(usuario, msgErro);
	}
	
	public static void cadastrarUsuario(String idUsuario, String login, String pass, String nome, String cpf,
			String endereco, String telefone){
		
		Usuario usuario = new Usuario();
		//Editar
		if(idUsuario != "" && !idUsuario.isEmpty()){
			Long id = Long.parseLong(idUsuario);
			usuario = Usuario.findById(id);
			
			usuario.setLogin(login);
			usuario.setPass(pass);
			usuario.setNome(nome);
			usuario.setCpf(cpf);
			usuario.setEndereco(endereco);
			usuario.setTelefone(telefone);
			usuario.save();
			String msgInformation = "Usuario editado com sucesso";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		}
		
		//Inserir
		else{
			if(login != null && !login.isEmpty() && pass != null && !pass.isEmpty()){
				usuario = new Usuario(login, pass, nome, cpf, endereco, telefone);
				usuario.save();
			}
			else{
				String msgErro = "Informe um login e uma senha para o usuario";
				cadastroUsuario(usuario, msgErro);
			}
			String msgInformation = "Usuario cadastrado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		}
	}

}

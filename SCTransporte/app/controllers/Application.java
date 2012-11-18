package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	private static Usuario usuarioLogado = null;
	
    public static void index(String msgErro) {
        render(msgErro);
    }
    
    public static void logar(String login, String pwd) {
    	Usuario user = Usuario.find("login", login).first();
    	if(user != null && user.getPass().equals(pwd)){
    		usuarioLogado = user;
    		session.put("user", usuarioLogado);
    		
    		menu(user, null);
    	}
    	else{
    		String msgErro = "Login/Senha inválidos!";
    		index(msgErro);
    	}
    }
    
    public static void menu(Usuario user, String msgInformation) {
   		render(user, msgInformation);
    }

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void cancelar() {
		menu(getUsuarioLogado(), null);
	}
	
}
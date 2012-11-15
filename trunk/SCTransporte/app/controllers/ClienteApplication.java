package controllers;

import models.Cliente;
import play.mvc.Controller;

public class ClienteApplication extends Controller{
	
	public static void cadastroCliente(){
		render();
	}
	
	public static void cadastroCliente(String msgErro){
		render(msgErro);
	}
	
	public static void cadastrarCliente(String empresa, String cnpj, String telefone,
			String endereco, String email){
		if(empresa != null && !empresa.isEmpty() && cnpj != null && !cnpj.isEmpty()){
			Cliente cliente = new Cliente(empresa, cnpj, telefone, endereco, email);
			cliente.save();
		}
		else{
			String msgErro = "Informe o nome da empresa e o cnpj";
			cadastroCliente(msgErro);
		}
		String msgInformation = "Cliente cadastrado com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
}

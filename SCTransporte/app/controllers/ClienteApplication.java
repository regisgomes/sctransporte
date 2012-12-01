package controllers;

import java.util.List;

import models.Cliente;
import play.mvc.Controller;

public class ClienteApplication extends Controller{
	
	public static void alterarCliente(String idCliente){
		Long id = Long.parseLong(idCliente);
		Cliente cliente = Cliente.findById(id);
		cadastroCliente(cliente, null);
	}
	
	public static void cadastroCliente(Cliente cliente,String msgErro){
		if(cliente == null){
			cliente = new Cliente();
		}
		render(cliente, msgErro);
	}
	
	public static void listaCliente() {
		List<Cliente> clientes = Cliente.all().fetch();
		render(clientes);
		
	}
	
	public static void cadastrarCliente(String idCliente, String empresa, String cnpj, String telefone,
			String endereco, String email){
		Cliente cliente = new Cliente();
		//Editar
		if(idCliente != null && !idCliente.isEmpty()){
			Long id = Long.parseLong(idCliente);
			cliente = Cliente.findById(id);
			
			cliente.setEmpresa(empresa);
			cliente.setCnpj(cnpj);
			cliente.setTelefone(telefone);
			cliente.setEndereco(endereco);
			cliente.setEmail(email);
			cliente.save();
			
			String msgInformation = "Cliente editado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		}
		//Inserir
		else{
			if(empresa != null && !empresa.isEmpty() && cnpj != null && !cnpj.isEmpty()){
				cliente = new Cliente(empresa, cnpj, telefone, endereco, email);
				cliente.save();
			}
			else{
				String msgErro = "Informe o nome da empresa e o cnpj";
				cadastroCliente(null, msgErro);
			}
			String msgInformation = "Cliente cadastrado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		}
	}
}

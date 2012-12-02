package controllers;

import java.util.List;

import javax.persistence.Query;

import models.Cargo;
import models.Cliente;
import models.Entrega;
import play.mvc.Controller;

public class ClienteApplication extends Controller{
	
	public static void alterarCliente(String idCliente){
		Long id = Long.parseLong(idCliente);
		Cliente cliente = Cliente.findById(id);
		cadastroCliente(cliente, null);
	}
	public static void excluirCliente(String idCliente){
		Long id = Long.parseLong(idCliente);
		Cliente cliente = Cliente.findById(id);
		cliente.delete();
		String msgInformation = "Cliente excluido com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
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
	
	public static void relatorioCliente(String idCliente, String Tipo, String Status){
		List<Cliente> clientes = Cliente.all().fetch();

		if(idCliente != null && !idCliente.isEmpty()){
			Long id = Long.parseLong(idCliente);
			String clienteBusca;
			String statusEntrega;
			
			StringBuilder query = new StringBuilder();
			
			query.append("FROM Entrega WHERE 1=1");
			
			if(Tipo != null && !Tipo.isEmpty()){
				if(Tipo.equals("Origem")){
					clienteBusca = "idClienteOrigem.id";
				}else{
					clienteBusca = "idClienteDestino.id";
				}
				query.append(" AND "+clienteBusca+"= "+id);
			}
			
			if(Status != null && !Status.isEmpty()){
				if(Status.equals("Definida")){
					statusEntrega = " IS NOT NULL";
				}else{
					statusEntrega = " IS NULL";
				}
				query.append(" AND viagem"+statusEntrega);
			}
			System.out.println(query.toString());
			Query q = Entrega.em().createQuery(query.toString());
			List<Entrega> entregas = q.getResultList();
			render(entregas,clientes);
		}
		else{
			List<Entrega> entregas = null;
			render(entregas, clientes);
		}
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
				cadastroCliente(cliente, msgErro);
			}
			String msgInformation = "Cliente cadastrado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		}
	}
}

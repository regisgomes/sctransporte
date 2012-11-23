/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Marca;
import models.Modelo;
import models.Tipo;
import play.mvc.Controller;

/**
 * @author Rafael Holanda
 *
 */
public class ModeloApplication extends Controller {
	
	public static void listaModelo(){
		List<Modelo> modelos = Modelo.all().fetch();
		render(modelos);
	}
	
	public static void alterarCadastro(String idModelo){
		Long id = Long.parseLong(idModelo);
		Modelo modelo = Modelo.findById(id);
		cadastroModelo(modelo, null);
	}
	
	public static void cadastroModelo(Modelo modelo, List<String> erros) {
		List<Marca> marcas =  Marca.all().fetch();
		List<Tipo> tipos = Tipo.all().fetch();
		if(modelo == null){
			modelo = new Modelo();
		}
		render(modelo, marcas, tipos, erros);
	}
	
	
	public static void cadastrarModelo(String idModelo, Long idMarca, Long idTipo, String nome) {
		List<String> erros = new ArrayList<String>();
		if (idMarca == null || idMarca == 0)
			erros.add("Informe a Marca!");
		
		if (idTipo == null || idTipo == 0)
			erros.add("Informe o Tipo!");
		
		if (nome == null || nome.isEmpty())
			erros.add("Informe a Descrição!");
		
		if (erros.isEmpty()) {
			//Ediçao
			if(idModelo != null && !idModelo.isEmpty()){
				Long id = Long.parseLong(idModelo);
				
				Modelo modelo = Modelo.findById(id);
				Marca marca = Marca.findById(idMarca);
				Tipo tipo = Tipo.findById(idTipo);
				
				modelo.setMarca(marca);
				modelo.setTipo(tipo);
				modelo.setNome(nome);
				modelo.save();
				
				String msgInformation = "Modelo " + modelo.getNome() + " Cadastrado com sucesso!";
				Application.menu(Application.getUsuarioLogado(), msgInformation);
			}
			//Cadastro
			else{
				Marca marca = Marca.findById(idMarca);
				Tipo tipo = Tipo.findById(idTipo);
				
				Modelo modelo = new Modelo();
				modelo.setMarca(marca);
				modelo.setTipo(tipo);
				modelo.setNome(nome);
				modelo.save();
				
				String msgInformation = "Modelo " + modelo.getNome() + " Cadastrado com sucesso!";
				Application.menu(Application.getUsuarioLogado(), msgInformation);
			}
		} 
		else {
			cadastroModelo(null,erros);
		}
	}
	
}

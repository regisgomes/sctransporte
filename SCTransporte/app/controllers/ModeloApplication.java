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

	public static void cadastroModelo() {
		List<Marca> marcas =  Marca.all().fetch();
		render(marcas);
	}
	
	public static void cadastroModelo(List<String> erros) {
		List<Marca> marcas =  Marca.all().fetch();
		render(marcas, erros);
	}
	
	
	public static void cadastrarModelo(Long idMarca, String nome) {
		List<String> erros = new ArrayList<String>();
		
		if (idMarca == null || idMarca == 0)
			erros.add("Informe a Marca!");
		
		if (nome == null || nome.isEmpty())
			erros.add("Informe a Descrição!");
		
		if (erros.isEmpty()) {
			Marca marca = Marca.findById(idMarca);
			Modelo modelo = new Modelo();
			modelo.setMarca(marca);
			modelo.setNome(nome);
			modelo.save();
			
			String msgInformation = "Modelo " + modelo.getNome() + " Cadastrado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		} else {
			cadastroModelo(erros);
		}
	}
	
}

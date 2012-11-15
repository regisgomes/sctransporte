/**
 * 
 */
package controllers;

import models.Marca;
import models.Modelo;
import play.mvc.Controller;

/**
 * @author Rafael Holanda
 *
 */
public class ModeloApplication extends Controller {

	public static void cadastroModelo() {
		render();
	}
	
	public static void cadastrarModelo(Long idMarca, String nome) {
		boolean hasError = false;
		StringBuilder erros = new StringBuilder();
		
		if (idMarca == null || idMarca == 0) {
			hasError = true;
			erros.append("Informe a Marca! <br/>");
		}
		
		if (nome == null || nome.isEmpty()) {
			hasError = true;
			erros.append("Informe a Descrição! <br/>");
		}
		
		if (!hasError) {
			Marca marca = Marca.findById(idMarca);
			Modelo modelo = new Modelo();
			modelo.setMarca(marca);
			modelo.setNome(nome);
			modelo.save();
		} else {
			Application.menu(Application.getUsuarioLogado(), erros.toString());
		}
	}
	
}

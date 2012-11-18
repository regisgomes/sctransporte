/**
 * 
 */
package controllers;

import java.util.List;

import models.Marca;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class MarcaApplication extends Controller {
	
	public static void listaMarca(){
		List<Marca> marcas = Marca.all().fetch();
		render(marcas);
	}

	public static void cadastroMarca() {
		render();
	}
	
	public static void cadastroMarca(String msgErro) {
		render(msgErro);
	}
	
	public static void cadastrarMarca(String nomeMarca) {
		if (nomeMarca != null && !nomeMarca.isEmpty()) {
			Marca marca = new Marca(nomeMarca);
			marca.save();
		} else {
			String msgErro = "Informe o nome para a Marca!";
			cadastroMarca(msgErro);
		}
		
		String msgInformation = "Marca "+ nomeMarca +" Cadastrada com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
}

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
	
	public static void alterarMarca(String idMarca){
		Long id = Long.parseLong(idMarca);
		Marca marca = Marca.findById(id);
		cadastroMarca(marca, null);
	}

	public static void cadastroMarca(Marca marca, String msgErro) {
		if(marca == null)
			marca = new Marca();
		
		render(marca, msgErro);
	}
	
	public static void cadastrarMarca(String nomeMarca, String idMarca) {
		if(idMarca != null && !idMarca.isEmpty()){
			Long id = Long.parseLong(idMarca);
			System.out.println(idMarca + " : " + id);
			Marca marca = Marca.findById(id);
			marca.setNome(nomeMarca);
			marca.save();
		}
		else if (nomeMarca != null && !nomeMarca.isEmpty()) {
			Marca marca = new Marca(nomeMarca);
			marca.save();
		} else {
			String msgErro = "Informe o nome para a Marca!";
			cadastroMarca(null, msgErro);
		}
		
		String msgInformation = "Marca "+ nomeMarca +" Cadastrada com sucesso!";
		Application.menu(Application.getUsuarioLogado(), msgInformation);
	}
	
}

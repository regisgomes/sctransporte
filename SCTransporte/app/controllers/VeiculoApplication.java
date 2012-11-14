/**
 * 
 */
package controllers;

import java.util.List;

import models.Marca;
import models.Tipo;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class VeiculoApplication extends Controller {

	public static void cadastroVeiculo() {
		List<Marca> marcas = Marca.all().fetch();
		List<Tipo> tipos = Tipo.all().fetch();
		render(marcas, tipos);
	}
	
}
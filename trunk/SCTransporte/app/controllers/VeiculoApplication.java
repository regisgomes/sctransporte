/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Carro;
import models.Marca;
import models.Modelo;
import models.Tipo;
import play.mvc.Controller;

/**
 * @author rafaelholanda90
 *
 */
public class VeiculoApplication extends Controller {

	public static void cadastroVeiculo() {
		List<Marca> modelos = Modelo.all().fetch();
		List<Tipo> tipos = Tipo.all().fetch();
		render(modelos, tipos);
	}
	
	public static void cadastroVeiculo(List<String> erros) {
		List<Marca> modelos = Modelo.all().fetch();
		List<Tipo> tipos = Tipo.all().fetch();
		render(modelos, tipos, erros);
	}
	
	public static void cadastrar(Long idModelo, Long idTipo, String placa, Integer quilometragem, String cor, Integer ano) {
		List<String> erros = validarCamposObg(idModelo, idTipo, placa, quilometragem, cor, ano);
		
		if (erros.isEmpty()) {
			Modelo modelo = Modelo.findById(idModelo);
			Tipo tipo = Tipo.findById(idTipo);
			
			Carro veiculo = new Carro();
			veiculo.setModelo(modelo);
			veiculo.setTipo(tipo);
			veiculo.setPlaca(placa);
			veiculo.setQuilometragem(quilometragem);
			veiculo.setCor(cor);
			veiculo.setAno(ano);
			veiculo.save();
			
			String msgInformation = "Veículo Cadastrado com sucesso!";
			Application.menu(Application.getUsuarioLogado(), msgInformation);
		} else {
			cadastroVeiculo(erros);
		}
		
	}

	private static List<String> validarCamposObg(Long idModelo, Long idTipo,
			String placa, Integer quilometragem, String cor, Integer ano) {
		List<String> erros = new ArrayList<String>();
		
		if (idModelo == null || idModelo == 0)
			erros.add("Informe o Modelo!");
		
		if (idTipo == null || idTipo == 0)
			erros.add("Informe o Tipo!");

		if (placa == null || placa.isEmpty())
			erros.add("Informe a Placa!");
		
		if (quilometragem == null)
			erros.add("Informe a Quilometragem!");
		
		if (cor == null || cor.isEmpty())
			erros.add("Informe a Cor!");
		
		if (ano == null || ano == 0)
			erros.add("Informe o Ano!");
		
		return erros;
	}
	
}

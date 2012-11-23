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
	
	public static void listaVeiculo(){
		List<Carro> veiculos = Carro.all().fetch();
		render(veiculos);
	}
	
	public static void alterarVeiculo(String idVeiculo){
		Long id = Long.parseLong(idVeiculo);
		Carro carro = Carro.findById(id);
		cadastroVeiculo(carro, null);
	}
	
	public static void cadastroVeiculo(Carro carro, List<String> erros) {
		List<Marca> modelos = Modelo.all().fetch();
		List<Tipo> tipos = Tipo.all().fetch();
		if(carro == null){
			carro = new Carro();
		}
		render(carro, modelos, tipos, erros);
	}
	
	public static void cadastrar(String idCarro, Long idModelo, String placa, Integer quilometragem, String cor, Integer ano) {
		List<String> erros = validarCamposObg(idModelo, placa, quilometragem, cor, ano);
		
		if (erros.isEmpty()) {
			//Ediçao
			if(idCarro != null && !idCarro.isEmpty()){
				Long id = Long.parseLong(idCarro);
				
				Carro veiculo = Carro.findById(id);
				Modelo modelo = Modelo.findById(idModelo);
				
				veiculo.setModelo(modelo);
				veiculo.setPlaca(placa);
				veiculo.setQuilometragem(quilometragem);
				veiculo.setCor(cor);
				veiculo.setAno(ano);
				veiculo.save();
				
				String msgInformation = "Veículo Editado com sucesso!";
				Application.menu(Application.getUsuarioLogado(), msgInformation);
			}
			//Cadastro
			else{
				Modelo modelo = Modelo.findById(idModelo);
				
				Carro veiculo = new Carro();
				veiculo.setModelo(modelo);
				veiculo.setPlaca(placa);
				veiculo.setQuilometragem(quilometragem);
				veiculo.setCor(cor);
				veiculo.setAno(ano);
				veiculo.save();
				
				String msgInformation = "Veículo Cadastrado com sucesso!";
				Application.menu(Application.getUsuarioLogado(), msgInformation);
			}
			
		} 
		else {
			cadastroVeiculo(null, erros);
		}
		
	}

	private static List<String> validarCamposObg(Long idModelo, String placa, Integer quilometragem, String cor, Integer ano) {
		List<String> erros = new ArrayList<String>();
		
		if (idModelo == null || idModelo == 0)
			erros.add("Informe o Modelo!");
		
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

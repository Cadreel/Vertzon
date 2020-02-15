package br.com.vertzon.loja.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vertzon.loja.dao.FabricanteDAO;
import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.util.jpa.Transactional;

public class CadastroFabricanteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException{
		
		if(fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new NegocioException("O nome do fabricante é obrigatório.");
		}
		
		this.fabricanteDAO.salvar(fabricante);
	}
}

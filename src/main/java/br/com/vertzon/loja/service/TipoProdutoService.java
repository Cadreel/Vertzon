package br.com.vertzon.loja.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vertzon.loja.dao.TipoProdutoDAO;
import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.util.jpa.Transactional;

public class TipoProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	TipoProdutoDAO tipoProdutoDAO;
	
	@Transactional
	public void salvar(TipoProduto tipoProduto) throws NegocioException {
		if(tipoProduto.getDescricao() == null || tipoProduto.getDescricao().trim().equals("")) {
			throw new NegocioException("O tipo de produto é obrigatório");
		}
		if(tipoProduto.getFabricante() == null) {
			throw new NegocioException("O fabricante é obrigatório");
		}
		this.tipoProdutoDAO.salvar(tipoProduto);
	}
}
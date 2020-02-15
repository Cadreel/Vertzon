package br.com.vertzon.loja.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vertzon.loja.dao.ProdutoDAO;
import br.com.vertzon.loja.modelo.Produto;
import br.com.vertzon.loja.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Transactional
	public void salvar(Produto produto) throws NegocioException {
		
		if(produto.getCodigoDeBarras() == null || produto.getCodigoDeBarras().trim().equals("")) {
			throw new NegocioException("O código de barras é obrigatório");
		}
		
		this.produtoDAO.salvar(produto);
	}

}
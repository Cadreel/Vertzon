package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.ProdutoDAO;
import br.com.vertzon.loja.modelo.Produto;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ProdutoDAO produtoDAO;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	private Produto produtoSelecionado;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void excluir() {
		try {
			produtoDAO.excluir(produtoSelecionado);
			this.produtos.remove(produtoSelecionado);
			FacesUtil.addSuccessMessage("Produto com o código de barras " + produtoSelecionado.getCodigoDeBarras() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}
	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	

	@PostConstruct
	public void inicializar() {
		produtos = produtoDAO.buscarTodos();
	}
	
	
	
}

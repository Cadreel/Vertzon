package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.TipoProdutoDAO;
import br.com.vertzon.loja.modelo.Produto;
import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.service.CadastroProdutoService;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	private List<TipoProduto> tiposProduto;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	@Inject
	private TipoProdutoDAO tipoProdutoDAO;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.tiposProduto = this.tipoProdutoDAO.buscarTodos();
	}
	
	public void salvar() {
		try {
			this.cadastroProdutoService.salvar(produto);
			FacesUtil.addSuccessMessage("Produto salvo com sucesso");
			this.limpar();
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contate o administrador do sistema");
		}
		
		
	}
	
	public void limpar() {
		this.produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<TipoProduto> getTiposProduto() {
		return tiposProduto;
	}	
}


package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.TipoProdutoDAO;
import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaTipoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TipoProduto> tiposProduto;
	
	private TipoProduto tipoProdutoSelecionado;
	
	@Inject
	TipoProdutoDAO tipoProdutoDAO;
	
	@PostConstruct
	public void inicializar() {
		this.tiposProduto = this.tipoProdutoDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			tipoProdutoDAO.excluir(tipoProdutoSelecionado);
			this.tiposProduto.remove(tipoProdutoSelecionado);
			FacesUtil.addSuccessMessage("O tipo de produto " + tipoProdutoSelecionado.getDescricao() + " foi excluído com sucesso!");
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	//GETS AND SETTERS
	public TipoProduto getTipoProdutoSelecionado() {
		return tipoProdutoSelecionado;
	}

	public void setTipoProdutoSelecionado(TipoProduto tipoProdutoSelecionado) {
		this.tipoProdutoSelecionado = tipoProdutoSelecionado;
	}

	public List<TipoProduto> getTiposProduto() {
		return tiposProduto;
	}
	
	
}

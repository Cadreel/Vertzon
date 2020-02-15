package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.FabricanteDAO;
import br.com.vertzon.loja.modelo.Categoria;
import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.service.TipoProdutoService;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTipoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoProduto tipoProduto;
	
	private List<Fabricante> fabricantes;
	private List<Categoria> categorias;
	
	@Inject
	private TipoProdutoService tipoProdutoService;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	public void salvar() {
		try {
			this.tipoProdutoService.salvar(tipoProduto);
			FacesUtil.addSuccessMessage("Tipo de produto salvo com sucesso");
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.fabricantes = fabricanteDAO.buscarTodos();
		this.categorias = Arrays.asList(Categoria.values());
	}
	
	public void limpar() {
		this.tipoProduto = new TipoProduto();
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	
	
}

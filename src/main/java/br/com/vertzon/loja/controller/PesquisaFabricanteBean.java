package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.FabricanteDAO;
import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FabricanteDAO fabricanteDAO;
	
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	
	private Fabricante fabricanteSelecionado;
	
	public void excluir() {
		try {
			fabricanteDAO.excluir(fabricanteSelecionado);
			this.fabricantes.remove(fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante: " + fabricanteSelecionado.getNome() + " excluído com sucesso!");
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void inicializar() {
		fabricantes = fabricanteDAO.buscarTodos();
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
}

package br.com.vertzon.loja.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.service.CadastroFabricanteService;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(fabricante); 
			FacesUtil.addSuccessMessage("Cadastro do fabricante realizado com sucesso!");
			
			this.limpar();
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
	}
	
	public void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
}

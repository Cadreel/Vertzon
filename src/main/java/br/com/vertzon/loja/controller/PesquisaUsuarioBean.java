package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vertzon.loja.dao.UsuarioDAO;
import br.com.vertzon.loja.modelo.Usuario;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDAO usuarioDAO;
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	private Usuario usuarioSelecionado;
	
	@PostConstruct
	public void inicializar() {
		usuarios = usuarioDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			usuarioDAO.excluir(usuarioSelecionado);
			this.usuarios.remove(usuarioSelecionado);
			FacesUtil.addSuccessMessage("Usuário " + usuarioSelecionado.getLogin() + " excluído com sucesso!");
		}catch(Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}

package br.com.vertzon.loja.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;

import br.com.vertzon.loja.modelo.Role;
import br.com.vertzon.loja.modelo.Usuario;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.service.UsuarioService;
import br.com.vertzon.loja.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	@Inject
	private UsuarioService usuarioService;
	
	private List<Role> roles;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.roles = Arrays.asList(Role.values());
	}
	
	public void salvar() {
		try {
			this.usuarioService.salvar(usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso");
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}catch(PersistenceException e) {
			if(e.getMessage().contains("ConstraintViolationException")) {
				FacesUtil.addErrorMessage("Email ou Login Duplicado");
				if(e.getMessage().contains("uk_login")) {
					FacesUtil.addErrorMessage("Login igual");
				}
			}
		}
		this.limpar();
	}
	
	public void limpar() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	
}

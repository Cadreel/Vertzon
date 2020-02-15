package br.com.vertzon.loja.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vertzon.loja.dao.UsuarioDAO;
import br.com.vertzon.loja.modelo.Role;
import br.com.vertzon.loja.modelo.Usuario;
import br.com.vertzon.loja.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void salvar(Usuario usuario) throws NegocioException  {
		
		if(usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
			throw new NegocioException("O login é obrigatório");
		}
		if(usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
			throw new NegocioException("O e-mail é obrigatório");
		}
		if(usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			throw new NegocioException("A senha é obrigatório");
		}
		if(usuario.getRole() == null) {
			usuario.setRole(Role.ADMIN);
		}
		
		this.usuarioDAO.salvar(usuario);
	}
	
	
}

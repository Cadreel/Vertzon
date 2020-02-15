package br.com.vertzon.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.vertzon.loja.modelo.Usuario;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.SessionUtil;
import br.com.vertzon.loja.util.jpa.JPAUtil;
import br.com.vertzon.loja.util.jpa.Transactional;


public class UsuarioDAO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private EntityManager manager;

	public Usuario buscarPeloCodigo(Long codigo){
		return manager.find(Usuario.class, codigo);
	}
	
	
	public void salvar(Usuario usuario) {
		manager.merge(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		return manager.createQuery("from Usuario").getResultList();
	}
	
	//Autentica o login  para logar no sistema
	public Usuario findByLogin(String login) {
		return JPAUtil.getEntityManager().createNamedQuery("Usuario.findByLogin", Usuario.class).setParameter("login", login).getSingleResult();
	}
	
	//Autentica o e-mail  para logar no sistema
	public Usuario findByEmail(String email) {
		return JPAUtil.getEntityManager().createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", email).getSingleResult();
	}
	
	@Transactional
	public void excluir(Usuario usuario) throws NegocioException {
		usuario = buscarPeloCodigo(usuario.getCodigo());
		try {
			manager.remove(usuario);
			manager.flush();
		}catch(PersistenceException e) {
			throw new NegocioException("O usuário não pode ser excluído!");
		}
	}
	
}

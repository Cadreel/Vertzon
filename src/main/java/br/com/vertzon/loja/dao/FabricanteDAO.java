package br.com.vertzon.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import br.com.vertzon.loja.modelo.Fabricante;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jpa.Transactional;

public class FabricanteDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public void salvar(Fabricante fabricante) {
		manager.merge(fabricante);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos(){
		return manager.createQuery("from Fabricante").getResultList();
	}
	
	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		Fabricante fabricanteTemp = manager.find(Fabricante.class, fabricante.getCodigo());
		try {
			manager.remove(fabricanteTemp);
			manager.flush();
		}catch(Exception e) {
			throw new NegocioException("Existem produtos associados ao fabricante " + fabricanteTemp.getNome() + ". Fabricante não pode ser excluído.");
		}
	}
	
	public Fabricante buscarPeloCodigo(Long codigo) {
		return manager.find(Fabricante.class, codigo);
	}
}

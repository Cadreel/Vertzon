package br.com.vertzon.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.transaction.TransactionalException;

import br.com.vertzon.loja.modelo.TipoProduto;
import br.com.vertzon.loja.service.NegocioException;
import br.com.vertzon.loja.util.jpa.Transactional;
import br.com.vertzon.loja.util.jsf.FacesUtil;

public class TipoProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public TipoProduto buscarPeloCodigo(Long codigo) {
		return manager.find(TipoProduto.class, codigo);
	}
	
	public void salvar(TipoProduto tipoProduto) {
		manager.merge(tipoProduto);
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoProduto> buscarTodos(){
		return manager.createQuery("from TipoProduto").getResultList();
	}
	
	@Transactional
	public void excluir(TipoProduto tipoProduto) throws NegocioException {
		tipoProduto = buscarPeloCodigo(tipoProduto.getCodigo());
		try {
			manager.remove(tipoProduto);
			manager.flush();
		}catch(TransactionRequiredException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			throw new NegocioException("O tipo de produto não pode ser excluído.");
		}
	}
}

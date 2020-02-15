package br.com.vertzon.loja.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.vertzon.loja.util.jpa.Transactional;

@Transactional
public class JPAUtil {
    
	private static final EntityManagerFactory emf;
	private static EntityManager entityManager = null;
	
	static {
		emf = Persistence.createEntityManagerFactory("lojaWebPU");
	}
	
	public static EntityManager getEntityManager() {
		if(entityManager == null) {
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}
}
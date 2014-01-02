package de.milltree.liquibaseexample.service.entity;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.milltree.liquibaseexample.entity.Product;

@Component
public class ProductService {

	@Inject
	private EntityManager entityManager;

	@Transactional
	public void save(Product product) {
		if (product.getId() == null) {
			entityManager.persist(product);
		} else {
			entityManager.merge(product);
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteAll() {
		Collection<Product> products = entityManager.createNamedQuery(
				Product.FIND_ALL).getResultList();
		for (Product product : products) {
			entityManager.remove(product);
		}
	}

	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return entityManager.find(Product.class, id);
	}

	@Transactional(readOnly = true)
	public Product findByName(String name) {
		try {
			Query query = entityManager.createNamedQuery(Product.FIND_BY_NAME);
			query.setParameter("name", name);
			return (Product) query.getSingleResult();
		} catch (EntityNotFoundException enfe) {
			return null;
		}
	}
}

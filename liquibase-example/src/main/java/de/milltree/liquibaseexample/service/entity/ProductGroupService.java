package de.milltree.liquibaseexample.service.entity;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.milltree.liquibaseexample.entity.ProductGroup;

@Component
public class ProductGroupService {

	@Inject
	private EntityManager entityManager;

	@Transactional
	public void save(ProductGroup productGroup) {
		if (productGroup.getId() == null) {
			entityManager.persist(productGroup);
		} else {
			entityManager.merge(productGroup);
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteAll() {
		Collection<ProductGroup> groups = entityManager.createNamedQuery(
				ProductGroup.FIND_ALL).getResultList();
		for (ProductGroup group : groups) {
			entityManager.remove(group);
		}
	}

	@Transactional(readOnly = true)
	public ProductGroup findById(Long id) {
		return entityManager.find(ProductGroup.class, id);
	}

}

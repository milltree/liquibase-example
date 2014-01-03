package de.milltree.liquibaseexample.service.entity;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.milltree.liquibaseexample.entity.Product;
import de.milltree.liquibaseexample.entity.StockEntry;

/**
 * Management service for stock entries.
 */
@Component
public class StockEntryService {

	@Inject
	private EntityManager entityManager;

	@Transactional
	public void save(StockEntry stockEntry) {
		if (stockEntry.getId() == null) {
			entityManager.persist(stockEntry);
		} else {
			entityManager.merge(stockEntry);
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteAll() {
		Collection<StockEntry> entries = entityManager.createNamedQuery(
				StockEntry.FIND_ALL).getResultList();
		for (StockEntry entry : entries) {
			entityManager.remove(entry);
		}
	}

	@Transactional(readOnly = true)
	public StockEntry findById(Long id) {
		return entityManager.find(StockEntry.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Collection<StockEntry> findByProducts(Collection<Product> products) {
		if (products == null || products.isEmpty()) {
			return Collections.<StockEntry>emptyList();
		}
		Query query = entityManager.createNamedQuery(StockEntry.FIND_BY_PRODUCTS);
		query.setParameter("products", products);
		return query.getResultList();
	}

}

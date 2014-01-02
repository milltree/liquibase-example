package de.milltree.liquibaseexample.service.entity;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.milltree.liquibaseexample.entity.StockEntry;

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

}

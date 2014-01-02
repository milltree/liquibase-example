package de.milltree.liquibaseexample.service.entity;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.milltree.liquibaseexample.entity.Discount;

@Component
public class DiscountService {

	@Inject
	private EntityManager entityManager;

	@Transactional
	public void save(Discount discount) {
		if (discount.getId() == null) {
			entityManager.persist(discount);
		} else {
			entityManager.merge(discount);
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteAll() {
		Collection<Discount> discounts = entityManager.createNamedQuery(
				Discount.FIND_ALL).getResultList();
		for (Discount discount : discounts) {
			entityManager.remove(discount);
		}
	}

	@Transactional(readOnly = true)
	public Discount findById(Long id) {
		return entityManager.find(Discount.class, id);
	}

}

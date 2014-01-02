package de.milltree.liquibaseexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "reductionAmount"})})
@NamedQueries({ @NamedQuery(name = Discount.FIND_ALL, query = "SELECT d FROM Discount d") })
public class Discount {
	
	public static final String FIND_ALL = "findAllDiscounts";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Product product;
	
	private double reductionAmount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getReductionAmount() {
		return reductionAmount;
	}

	public void setReductionAmount(double reductionAmount) {
		this.reductionAmount = reductionAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reductionAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Double.doubleToLongBits(reductionAmount) != Double
				.doubleToLongBits(other.reductionAmount))
			return false;
		return true;
	}

}

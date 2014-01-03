package de.milltree.liquibaseexample.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import de.milltree.liquibaseexample.entity.Discount;
import de.milltree.liquibaseexample.entity.Product;
import de.milltree.liquibaseexample.entity.ProductGroup;
import de.milltree.liquibaseexample.entity.StockEntry;
import de.milltree.liquibaseexample.service.entity.DiscountService;
import de.milltree.liquibaseexample.service.entity.ProductGroupService;
import de.milltree.liquibaseexample.service.entity.ProductService;
import de.milltree.liquibaseexample.service.entity.StockEntryService;

/**
 * Service for clearing the stock and inserting the initial product data.
 */
@Component
public class InitService {
	
	@Inject
	private ProductGroupService productGroupService; 
	
	@Inject
	private ProductService productService; 
	
	@Inject
	private DiscountService discountService; 
	
	@Inject
	private StockEntryService stockEntryService; 
	
	public void initStock() {
		clean();
		
		ProductGroup colourGroup = createProductGroup("Colours");
		ProductGroup countryGroup = createProductGroup("Countries");
		
		Product yellow = createProduct("Yellow", 10.0, colourGroup);
		Product green = createProduct("Green", 12.0, colourGroup);
		Product blue = createProduct("Blue", 15.0, colourGroup);
		Product germany = createProduct("Germany", 123.0, countryGroup);
		Product france = createProduct("France", 60.5, countryGroup);
		Product spain = createProduct("Spain", 210.25, countryGroup);
		Product italy = createProduct("Italy", 99.0, countryGroup);
		
		createDiscount(green, 5.0);
		createDiscount(spain, 10.0);
		createDiscount(italy, 9.0);
		
		createStockEntry(yellow, 0);
		createStockEntry(green, 5);
		createStockEntry(blue, 4);
		createStockEntry(germany, 10);
		createStockEntry(france, 4);
		createStockEntry(spain, 7);
		createStockEntry(italy, 1);
	}
	
	private ProductGroup createProductGroup(String name) {
		ProductGroup group = new ProductGroup();
		group.setName(name);
		productGroupService.save(group);
		
		return group;
	}
	
	private Product createProduct(String name, double price, ProductGroup group) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setProductGroup(group);
		productService.save(product);
		
		return product;
	}
	
	private Discount createDiscount(Product product, double amount) {
		Discount discount = new Discount();
		discount.setProduct(product);
		discount.setReductionAmount(amount);
		discountService.save(discount);
		
		return discount;
	}
	
	private StockEntry createStockEntry(Product product, int amount) {
		StockEntry entry = new StockEntry();
		entry.setProduct(product);
		entry.setAmount(amount);
		stockEntryService.save(entry);
		
		return entry;
	}
	
	private void clean() {
		stockEntryService.deleteAll();
		discountService.deleteAll();
		productService.deleteAll();
		productGroupService.deleteAll();
	}

}

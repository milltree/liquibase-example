package de.milltree.liquibaseexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.milltree.liquibaseexample.service.InitService;

/**
 * Inserts the initial product data into the stock.
 */
public class InitMain {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("spring/spring-init.xml");

		InitService initService = context.getBean(InitService.class);
		initService.initStock();
	}

}

package de.milltree.liquibaseexample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Simply starts the spring context so that the Liquibase bean is initialized and executed.
 */
public class LiquibaseMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring/spring-liquibase.xml");
	}

}

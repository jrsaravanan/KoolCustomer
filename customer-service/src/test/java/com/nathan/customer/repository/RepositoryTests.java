package com.nathan.customer.repository;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nathan.customer.entity.Customer;


@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
 
	@Autowired
	private TestEntityManager entityMnager;
	
	@Autowired
	private CustomerRepository repository;
	
	
	 @Value("${test}")
	 private String value;

	@Test
	public void testSave() {
		System.out.println("Hello : " + value);
		Customer entity = new Customer();
		entity.setFirstname("SASA");
		entity.setLastname("SAAI");
		repository.save(entity);		
		assertThat(repository.findAll(), hasSize(1));
	}
}

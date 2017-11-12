package com.nathan.customer.repository;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nathan.customer.entity.Customer;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTests.class);

	@Autowired
	private CustomerRepository repository;

	@Test
	public void testSave() {
		Customer entity = new Customer();
		entity.setFirstname("SASA");
		entity.setLastname("SAAI");
		repository.save(entity);
		assertThat(repository.findAll(), hasSize(1));
	}

	@Test
	public void testFindAndDelete() {
		Customer entity = new Customer();
		entity.setFirstname("SASA");
		entity.setLastname("SAAI");
		repository.save(entity);
		assertThat(repository.findAll(), hasSize(1));

		LOGGER.info(">>>> id {} ", entity.getId());
		
		Customer retriveSameObject = repository.findById(entity.getId());
		assertThat(retriveSameObject, equalTo(entity));
		
		repository.delete(retriveSameObject);
		assertThat(repository.findAll(), hasSize(1));
		
	}
}

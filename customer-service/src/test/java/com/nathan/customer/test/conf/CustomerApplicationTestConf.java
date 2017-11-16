package com.nathan.customer.test.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.PlatformTransactionManager;

import com.nathan.customer.entity.Customer;
import com.nathan.customer.repository.CustomerRepository;
import com.nathan.customer.service.CustomerService;
import com.nathan.customer.service.CustomerServiceImpl;


//@SpringBootApplication ( scanBasePackages = {"com.nathan.customer.resource" , "com.nathan.customer.repository" , "com.nathan.customer.entity" ,
//		"com.nathan.customer.service"})
@EnableJpaRepositories(basePackages ={ "com.nathan.customer.repository" })
@ComponentScan( basePackages = { "com.nathan.customer.repository" , "com.nathan.customer.entity"  , "com.nathan.customer.service" , "com.nathan.customer.resource"})
@Configuration
public class CustomerApplicationTestConf {
  //@AliasFor(annotation = ActiveProfiles.class, attribute = "profiles") String[] activeProfiles() default {"test"};
	
	
	 @Bean
	  public DataSource dataSource() {

	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    return builder.setType(EmbeddedDatabaseType.H2).build();
	  }

	  @Bean
	  public EntityManagerFactory entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.nathan.customer.entity");
	    factory.setDataSource(dataSource());
	    factory.afterPropertiesSet();

	    return factory.getObject();
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager() {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	  }
	  
	  @Bean
	  public ModelMapper modelMapper() {
		  return new ModelMapper();
	  }
	  
//	@Bean
//	public CustomerRepository customerRepository() {
//		return (CustomerRepository) new SimpleJpaRepository(Customer.class, entityManagerFactory().createEntityManager());
//	}
	
//	@Bean 
//	public CustomerService customerService() {
//		return new CustomerServiceImpl(customerRepository());
//	}
}

 
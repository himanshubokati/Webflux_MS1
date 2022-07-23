package com.test.springwebflux.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseMigration {

	@Autowired
	Environment env;

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		return new Flyway(Flyway.configure()
				.baselineOnMigrate(true)
				.dataSource(
					env.getRequiredProperty("spring.flyway.url"), 
					env.getRequiredProperty("spring.flyway.user"),
					env.getRequiredProperty("spring.flyway.password"))
				.schemas(env.getRequiredProperty("spring.flyway.schemas"))
				.baselineVersion(env.getRequiredProperty("spring.flyway.baselineVersion"))
				.table(env.getRequiredProperty("spring.flyway.table")));

	}

}

package ru.integration.esb.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "ru.integration.esb.kafka.repository")
@Configuration
public class DataJpaConfig {

    @Value("${kafka.service.database.classname:org.postgresql.Driver}")
    private String driverClassName;
    @Value("${kafka.service.database.url:jdbc:postgresql://localhost:5432/kafka_message}")
    private String url;
    @Value("${kafka.service.database.username:kafka}")
    private String username;
    @Value("${kafka.service.database.password:kafka}")
    private String password;

    @Value("${lotus.database.properties.hibernate.dialect:org.hibernate.dialect.PostgreSQL95Dialect}")
    public String dialect;
    @Value("${lotus.database.hibernate.ddl-auto:validate}")
    public String ddl;

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(dialect);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("ru.integration.esb.kafka.model");

        return factory;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}


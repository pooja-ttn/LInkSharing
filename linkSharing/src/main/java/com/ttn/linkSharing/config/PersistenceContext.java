package com.ttn.linkSharing.config;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EntityScan("com.ttn.linkSharing.entities")
@EnableJpaRepositories("com.ttn.linkSharing.repositories")

    public class PersistenceContext {

        @Bean
        DataSource dataSource(){
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/link_Share");
            driverManagerDataSource.setUsername("ttn");
            driverManagerDataSource.setPassword("Poojattn");
            return driverManagerDataSource;
        }

        @Bean
        LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource);
            entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactoryBean.setPackagesToScan("com.ttn.linkSharing");

            Properties jpaProperties= new Properties();
            jpaProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
            jpaProperties.setProperty("hibernate.show_sql","true");
            jpaProperties.setProperty("hibernate.hbm2ddl.auto","create");

            entityManagerFactoryBean.setJpaProperties(jpaProperties);
            return entityManagerFactoryBean;
        }

        @Bean
        JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            return transactionManager;
        }



    }



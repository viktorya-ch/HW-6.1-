package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataSourseConfig {
    @Bean(name = " h2DataSourse ")
    @ConfigurationProperties(prefix = " spring.datasourse.h2 ")
    public DataSource h2DataSourse(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = " pgDataSourse ")
    @ConfigurationProperties(prefix = " spring.datasourse.postgres ")
    public DataSource pgDataSourse(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate h2JdbcTemplate(@Qualifier(" h2DataSource ")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(" pgDataSource ") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(" com.example.demo.dynamic ");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }
}

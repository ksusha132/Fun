package com.epam.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam")
@EnableAspectJAutoProxy
@PropertySources({
        @PropertySource("classpath:auditoriumRed.properties"),
        @PropertySource("classpath:auditoriumGreen.properties"),
        @PropertySource("classpath:auditoriumBlue.properties")
})
public class AppConfig {

    @Bean
    public DataSource myPostgresSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5469/cinema");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(myPostgresSqlDataSource());

        return jdbcTemplate;
    }
}

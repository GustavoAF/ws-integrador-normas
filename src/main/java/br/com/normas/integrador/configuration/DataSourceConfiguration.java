package br.com.normas.integrador.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean
    @Qualifier("dsDataSource")
    @ConfigurationProperties(prefix = "spring.integrador")
    public DataSource dataSource(DataSourceProperties properties) {
        return DataSourceBuilder.create(properties.getClassLoader())
                .driverClassName(properties.getDriverClassName())
                .url("jdbc:postgresql://bdnormas.c7yycir14rir.us-east-1.rds.amazonaws.com:5432/postgres")
                .username("postgres")
                .password("3f11cd32")
                .build();
    }

    @Primary
    @Bean
    @Qualifier("dsJdbcTemplate")
    public JdbcTemplate getConsignadoJdbcTemplate(@Qualifier("dsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean
    @Qualifier("dsNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate getConsignadoNamedParameterJdbcTemplate(
            @Qualifier("dsDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}

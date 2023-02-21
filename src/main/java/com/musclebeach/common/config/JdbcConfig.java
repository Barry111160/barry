package com.musclebeach.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class JdbcConfig {
    @Value("${url}")
    private String url;
    @Value("${_username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${driver-class-name}")
    private String driver;
    @Value("${maximum-pool-size}")
    private Integer maximumPoolSize;
    @Value("${minimum-idle}")
    private Integer minimumIdle;
    @Value("${idle-timeout}")
    private Integer idleTimeout;
    @Value("${connection-timeout}")
    private Integer connectionTimeout;
    @Value("${max-lifetime}")
    private Integer maxLifetime;
    @Value("${pool-name}")
    private String poolName;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setIdleTimeout(idleTimeout);
        config.setConnectionTimeout(connectionTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setPoolName(poolName);
        return new HikariDataSource(config);
    }

    @Bean("mybatis")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("hibernate")
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}

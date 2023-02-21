package com.musclebeach.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:jdbc.properties", "classpath:redis.properties"})
@ComponentScan(value = {"com.musclebeach.*.service", "com.musclebeach.*.mapper", "com.musclebeach.product.model", "com.musclebeach.*.model", "com.musclebeach.common.util"})
@Import({JdbcConfig.class, MybatisConfig.class, RedisConfig.class, HibernateConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}

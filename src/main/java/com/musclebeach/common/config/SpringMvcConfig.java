package com.musclebeach.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.musclebeach.*.controller"})
@Import({SpringMvcSupport.class})
//@EnableWebMvc
public class SpringMvcConfig {
}

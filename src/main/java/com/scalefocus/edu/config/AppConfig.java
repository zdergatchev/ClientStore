package com.scalefocus.edu.config;

//https://www.tutorialspoint.com/spring/spring_java_based_configuration.htm //

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.scalefocus")
public class AppConfig {

}

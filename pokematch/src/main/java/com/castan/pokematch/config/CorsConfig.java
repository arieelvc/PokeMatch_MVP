package com.castan.pokematch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**") // Aplica la regla a todas las rutas de la API
                        .allowedOrigins("http://localhost:4200") // Abre la puerta específicamente a tu app de Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*") // Permite cualquier cabecera (Content-Type, Authorization, etc.)
                        .allowCredentials(true); // Necesario para cuando implementemos JWT/Cookies en la Fase 3
            }
        };
    }
}

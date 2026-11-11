package com.example.BD_de_PsicoAppMPAZ.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Acceso Libre / Autenticación y Swagger
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 2. Acceso para el ALUMNO (Ingresa mediante el código temporal asignado sin token o libre)
                        .requestMatchers(HttpMethod.GET, "/api/aplicaciones/codigo/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/aplicaciones/responder/**").permitAll()

                        // 3. Permisos compartidos (PROFESOR y PSICOLOGO)
                        // - Crear y ver cuestionarios / evaluaciones
                        .requestMatchers(HttpMethod.GET, "/api/cuestionarios/**").hasAnyRole("PSICOLOGO", "PROFESOR")
                        .requestMatchers(HttpMethod.POST, "/api/cuestionarios/**").hasAnyRole("PSICOLOGO", "PROFESOR")
                        // - Ver y registrar Asistencia
                        .requestMatchers("/api/asistencias/**").hasAnyRole("PSICOLOGO", "PROFESOR")

                        // 4. Permisos EXCLUSIVOS del PSICOLOGO
                        // - Gestionar usuarios, ver detalle clínico completo, dejar observaciones técnicas
                        .requestMatchers("/api/usuarios/**").hasRole("PSICOLOGO")
                        .requestMatchers("/api/aplicaciones/feedback/**").hasRole("PSICOLOGO")
                        .requestMatchers("/api/estudiantes/**").hasRole("PSICOLOGO")

                        // Cualquier otra ruta requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .addFilterBefore(new JwtAuthFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
package com.example.demo.config;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define cómo buscará Spring Security a los usuarios
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> usuarioRepository.findByNombreUsuario(username)
                .map(usuario -> org.springframework.security.core.userdetails.User.builder()
                        .username(usuario.getNombreUsuario())
                        .password(usuario.getPassword())
                        // Los roles deben ser Strings
                        .roles(usuario.getRol().name().toUpperCase()) 
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    // 2. Define el codificador de contraseñas (BCrypt es el estándar)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. Define las reglas de acceso (Quién accede a qué)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Acceso público para todas las rutas estáticas y páginas de visualización
                .requestMatchers("/", "/inicio", "/catalogo", "/nosotros","/registro", "/img/**", "/css/**", "/js/**").permitAll() 
                // La lista de libros es solo para administradores
                .requestMatchers("/libros/lista", "/libros/nuevo").hasRole("ADMIN") 
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login") // Si no tienes un controller para /login, Spring lo hace por ti
                .defaultSuccessUrl("/inicio", true) // Redirección después del éxito
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/inicio")
                .permitAll()
            );
        return http.build();
    }
}
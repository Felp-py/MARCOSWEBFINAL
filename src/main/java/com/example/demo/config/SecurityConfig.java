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
    
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            System.out.println("🟡 === INTENTO DE LOGIN ===");
            System.out.println("Usuario ingresado: '" + username + "'");
            
            // Listar TODOS los usuarios para debugging
            var todos = usuarioRepository.findAll();
            System.out.println("Total usuarios en BD: " + todos.size());
            
            // Buscar por nombre EXACTO (case-sensitive)
            Optional<com.example.demo.model.Usuario> usuarioOpt = todos.stream()
                .filter(u -> {
                    boolean match = u.getNombre().equals(username);
                    if (match) {
                        System.out.println("✅ Encontrado por nombre exacto: " + u.getNombre());
                    }
                    return match;
                })
                .findFirst();
            
            // Si no encuentra, buscar ignorando mayúsculas
            if (usuarioOpt.isEmpty()) {
                usuarioOpt = todos.stream()
                    .filter(u -> u.getNombre().equalsIgnoreCase(username))
                    .findFirst();
                if (usuarioOpt.isPresent()) {
                    System.out.println("⚠️  Encontrado ignorando mayúsculas: " + usuarioOpt.get().getNombre());
                }
            }
            
            if (usuarioOpt.isEmpty()) {
                System.out.println("❌ NO ENCONTRADO. Usuarios disponibles:");
                todos.forEach(u -> {
                    System.out.println("   - Nombre: '" + u.getNombre() + 
                                     "', Email: '" + u.getEmail() + "'");
                });
                throw new UsernameNotFoundException("Usuario '" + username + "' no encontrado");
            }
            
            var usuario = usuarioOpt.get();
            String rol = (usuario.getIdRol() != null && usuario.getIdRol() == 1) ? "ADMIN" : "USER";
            
            System.out.println("🔑 Autenticando: " + usuario.getNombre());
            System.out.println("   Rol asignado: " + rol);
            System.out.println("   Password (hash): " + usuario.getPassword());
            
            return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .roles(rol)
                .build();
        };
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                // Rutas PÚBLICAS
                .requestMatchers(
                    "/", "/inicio", "/catalogo", "/nosotros", "/compras",
                    "/login", "/registro", "/error",
                    "/css/**", "/js/**", "/img/**", "/webjars/**"
                ).permitAll()
                // Rutas ADMIN
                .requestMatchers("/libros/**", "/admin/**").hasRole("ADMIN")
                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")          // Solo /login
                .loginProcessingUrl("/login") // Solo /login
                .defaultSuccessUrl("/inicio", true)
                .failureUrl("/login?error=true") // Solo /login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true") // Solo /login
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/registro/api/**",
                    "/api/consulta/**",
                    "/api/**"
                )
            )
            .build();
    }
}
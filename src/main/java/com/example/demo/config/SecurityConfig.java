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
            System.out.println("🔍 Intentando autenticar: " + username);
            
            // Buscar por nombre O correo
            return usuarioRepository.findByNombreOrCorreo(username)
                    .map(usuario -> {
                        System.out.println("✅ Usuario encontrado: " + usuario.getNombre());
                        System.out.println("   Correo: " + usuario.getCorreo());
                        System.out.println("   ID Rol: " + usuario.getIdRol());
                        
                        String rol = (usuario.getIdRol() != null && usuario.getIdRol() == 1) 
                                   ? "ADMIN" : "USER";
                        
                        return org.springframework.security.core.userdetails.User.builder()
                                .username(usuario.getNombre())
                                .password(usuario.getContrasena())  // CORRECCIÓN: getContrasena
                                .roles(rol)
                                .build();
                    })
                    .orElseThrow(() -> {
                        System.out.println("❌ Usuario no encontrado: " + username);
                        return new UsernameNotFoundException("Usuario no encontrado");
                    });
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
                    "/login", "/registro/**", "/error",
                    "/css/**", "/js/**", "/img/**", "/webjars/**",
                    "/registro/api/**", "/api/consulta/**", "/dni/**", "/api/**"
                ).permitAll()
                // Rutas ADMIN
                .requestMatchers("/libros/**", "/admin/**").hasRole("ADMIN")
                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/inicio", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
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
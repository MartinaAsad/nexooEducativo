package com.nexo.nexoeducativo.configuration;

import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Volvemos el proyecto al Commit a8d00e8


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

     @Value("${cors.origins}")
    private String[] corsOrigins;
    @Autowired
    private SussesHundler successHandler;
    @Autowired
    private LogoutHundler logoutHundler;
    @Autowired
    private FailureHandler failureHandler;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .httpBasic(withDefaults())
            .csrf(csrf -> csrf.disable());
            
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        String password = "password";
        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-256");
        String hashedPassword = encoder.encode(password);
        
        UserDetails user = User.builder()
            .username("user")
            .password(hashedPassword)
            .roles("USER")
            .passwordEncoder(pwd -> pwd) // Use the password as-is since it's already encoded
            .build();
            
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
   /* @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Inicio de sesión exitoso\"}");
        };
    }

    // And your failure handler
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Credenciales incorrectas\"}");
        };
    }
    
    @Bean
public UserDetailsService userDetailsService() {
    return username -> {
        Usuario usuario = usuarioRepository.findByMail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
        return User.builder()
            .username(usuario.getMail())
            .password(usuario.getClave())
            .roles(usuario.getRolidrol().getNombre())
            .build();
    };
}*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//aplicar politica de cors a todos los endpoint
                        .allowedOrigins(corsOrigins)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") //metodos permitidos
                        .allowedHeaders("*")
                        .allowCredentials(true);//pedir credenciales
            }
        };
    }

}

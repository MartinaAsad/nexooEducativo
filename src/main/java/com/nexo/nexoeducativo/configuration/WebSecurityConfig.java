package com.nexo.nexoeducativo.configuration;

import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableMethodSecurity(prePostEnabled = true) //para que no ignore el pre authorize
@Configuration
@EnableWebSecurity
//@CrossOrigin
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
                 .requestMatchers("/auth/**").authenticated()
                    .requestMatchers("/logout").authenticated()
                    .requestMatchers("/ms/**").authenticated()
                    .requestMatchers("/chatIndividual").authenticated()
                    .requestMatchers("/obtenerMensajes").authenticated()
                    .requestMatchers("/nuevoMensaje").authenticated()
                     .requestMatchers("/editarMensajePrivado/{idMensaje}").authenticated()
                    .requestMatchers("/borrarMensaje/{idMensaje}").authenticated()
                    .requestMatchers("/obtenerMensajesEntreUsuarios/{mailD}").authenticated()
                    //.requestMatchers()
  //             .requestMatchers("/api/usuario/**").authenticated() // Rutas protegidas
                   
                .requestMatchers("/login").permitAll() // Permitir acceso a login sin autenticación
            )
            .cors(withDefaults()) // Habilitar CORS usando la configuración de WebMvcConfigurer
            .formLogin(form -> form.loginProcessingUrl("/login")
                .successHandler(this.successHandler)
                .failureHandler(this.failureHandler)
                .permitAll())
            .authenticationProvider(this.authenticationProvider)
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Uusario inactivo o o la sesión ha expirado.\"}" + authException.getMessage());
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Acceso denegado.\"}");
                })
            )
            .logout(logout -> logout.logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(this.logoutHundler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .httpBasic(withDefaults()) // Habilitar autenticación HTTP básica
            .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins(corsOrigins)
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

package com.nexo.nexoeducativo.configuration;

import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
                .requestMatchers("/api/**").authenticated() //loguearse si o si
                //.requestMatchers("/login").permitAll() //entran todos
                .requestMatchers("/auth/**").permitAll()
                )
                .cors(withDefaults()) // Habilitar CORS             //opcional de customizar                  //opcional de customizar
                .formLogin(form -> form.loginProcessingUrl("/login")
                        .usernameParameter("mail")
                        .passwordParameter("clave")
                        .successHandler(this.successHandler).failureHandler(this.failureHandler).permitAll())
                .authenticationProvider(this.authenticationProvider)
                .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"No está autenticado o la sesión ha expirado.\"}");
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);//falta de permisos
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Acceso denegado.\"}");
                })
                )                                   //logout: invalida la sesion, borra las cookies de la sesion, mensaje de deslogueo exitoso (o no)
                .logout(logout -> logout.logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessHandler(this.logoutHundler))
                .sessionManagement(session -> session.maximumSessions(1).maxSessionsPreventsLogin(false)) //solo 1 sesion por usuario
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))//la autenticacion es requerida SIEMPRE
                .httpBasic(withDefaults()) // Habilitar autenticación HTTP básica
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF
//                .addFilterBefore(this.segundoFactorAutenticacionFilter, UsernamePasswordAuthenticationFilter.class);  // Añadir filtro custom antes del filtro de autenticación

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //configura pase de info a la sesion
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

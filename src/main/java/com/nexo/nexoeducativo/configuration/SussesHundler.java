package com.nexo.nexoeducativo.configuration;


import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioDTO;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author AL_I01971
 */
@Configuration
public class SussesHundler extends SimpleUrlAuthenticationSuccessHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SussesHundler.class);
    
    
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    InfoUsuarioDTO user = (InfoUsuarioDTO) authentication;

    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");  // Asegura que el contenido sea JSON
    response.setStatus(HttpServletResponse.SC_OK);

    // Enviar datos en JSON
    response.getWriter().write("{\"nombre\":\"" + user.getGetNombre() + "\", \"apellido\":\"" + user.getGetApellido() + "\"}");
    response.getWriter().flush();
    LOGGER.info("Usuario logueado correctamente");
}

}

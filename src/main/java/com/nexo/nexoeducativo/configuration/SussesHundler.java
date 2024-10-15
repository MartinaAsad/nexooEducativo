package com.nexo.nexoeducativo.configuration;


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
    
   /* @Autowired
    private UserAutenticatedService tokenService;
    @Autowired
    private UsuarioActividadService usuarioActividadService;
    @Autowired
    private SegundoFactorAutenticacionService segundoFactorAutenticacionService;*/
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       /* UserAuthenticationDTO user = (UserAuthenticationDTO) authentication;
        usuarioActividadService.saveActividad(ActividadEnum.LOGIN);
        response.getWriter().write("AUTENTICATED:" + tokenService.getAutoritys() );*/
       /* if (user.getUse2fa()) {
            segundoFactorAutenticacionService.sendOtpBancoCiudad(user);
            response.getWriter().write(":/2fa");
            LOGGER.info("Esperando autenticacion 2FA.");
        } else {
            response.getWriter().write(":/home");
        }
        response.getWriter().write(":entidaes: " + user.getAllEntidades());
        response.setContentType("application/json");*/
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
//        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
//        response.addHeader("Access-Control-Allow-Credentials","true");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_OK);
        LOGGER.info("Token encriptado y devuelto con exito.");
    }
}

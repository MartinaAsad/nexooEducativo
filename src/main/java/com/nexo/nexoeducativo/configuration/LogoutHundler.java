package com.nexo.nexoeducativo.configuration;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author AL_I01971
 */
@Configuration
public class LogoutHundler implements LogoutSuccessHandler {
    
   /* @Autowired
    private UsuarioActividadService usuarioActividadService;*/

    @Override
    public void onLogoutSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
       // usuarioActividadService.saveActividad(ActividadEnum.CERRAR_SESSION, a);
        hsr1.getWriter().write("/");
        hsr1.setContentType("application/json");
        hsr1.setCharacterEncoding("UTF-8");
        hsr1.getWriter().flush();
        hsr1.setStatus(HttpServletResponse.SC_OK);
    }

}

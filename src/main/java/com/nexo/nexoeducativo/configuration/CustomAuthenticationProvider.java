package com.nexo.nexoeducativo.configuration;


import static com.mysql.cj.conf.PropertyKey.logger;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j 
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class); 
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Usuario usuario = new Usuario();
        //usuario.setMail(usuarioRepository.findByMail(email));
        
        logger.debug("email que llega"+email);
        logger.debug("password que llega"+password);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
        if (usuario.getActivo() != (short) 1) { // Verificar que el usuario esté activo
            throw new UsernameNotFoundException(email + " no habilitado.");
        }
        // Verificar si la contraseña es correcta
        if (!usuario.getClave().equals(convertirSHA256(password))) {
            throw new BadCredentialsException("Contraseña incorrecta.");
        }
        
        // Asignar roles al usuario
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Rol rol = usuario.getRolidrol();
        if (rol != null) {
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        // Retornar el token de autenticación con el objeto usuario
        return new UsernamePasswordAuthenticationToken(usuario, null, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static final String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
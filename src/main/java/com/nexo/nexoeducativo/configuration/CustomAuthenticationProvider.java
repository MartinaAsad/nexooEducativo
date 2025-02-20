package com.nexo.nexoeducativo.configuration;


import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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
    //@Transactional //para que la sesion este abierta en todo el proceso
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        
       /* System.out.println("Mail recibido: " + email);
        System.out.println("Largo de la contraseña recibida: " + password.length());
        System.out.println("objeto authentication"+ authentication.toString());*/

        try {
            Usuario user = usuarioRepository.findByMail(email)
                .orElseThrow(() -> {
                    System.out.println("Usuario no enconteado desde authenticate: " + email);
                    return new UsernameNotFoundException("Usuario no encontrado: " + email);
                });

            String hashedPassword = convertirSHA256(password);
            /*System.out.println("⭐ COMPARANDO CONTRAS...");
            System.out.println("Largo de la contra hasheada: " + hashedPassword.length());
            System.out.println("Contrasena hasheada proveniente del objeto: " + user.getClave());
            System.out.println("contra hasheada segun lo recibido: " + hashedPassword);*/

            if (user.getClave().equals(hashedPassword) && user.getActivo()==1) {
                //System.out.println("siii todo bien: " + email);
                List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority(user.getRolidrol().getNombre())
                );
                return new InfoUsuarioDTO(email, password, authorities, user.getNombre(), user.getApellido());
            } else if (!user.getClave().equals(hashedPassword)){
                //System.out.println("contra incorrecta: " + email);
                throw new BadCredentialsException("Contraseña inválida");
            }else{
                throw new UsuarioNotFoundException("Usuario inactivo. Por favor, contacte al administrador de su escuela");
            }
        } catch (Exception e) {
            //System.out.println("error en la autenticacion desd eauthenticate: " + e.getMessage());
            throw e;
        }
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
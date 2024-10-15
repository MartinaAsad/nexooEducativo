package com.nexo.nexoeducativo.configuration;


import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();//
       /* Usuario usuario = usuarioRepository.findByEmail(email);//busca el usuario por mail
        if (usuario == null) {//si no lo encuentra
            throw new UsernameNotFoundException(email);//customizar la excepcion
        } else if (usuario.getActivo()==(short)1) {//chequea si el usuario que se loguea esta activo
            throw new UsernameNotFoundException(email + "  no habilitado.");
        } else {    //se hashea lo que viene desde el servidor
            if (usuario.getPass().equals(convertirSHA256(password) )) {//aplicar la funcion hash al dar de alta un usuario
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (Rol role : usuario.getRolidrol().getNombre()) {
                    authorities.add(new SimpleGrantedAuthority( role.getNombre() ));
                }
                return new UsernamePasswordAuthenticationToken(usuario.getEMail(), usuario.getPass(), authorities);
            }
        }*/
        return null;
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

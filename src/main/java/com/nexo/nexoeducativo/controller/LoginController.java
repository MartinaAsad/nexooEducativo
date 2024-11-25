package com.nexo.nexoeducativo.controller;


//import com.nexo.nexoeducativo.configuration.CustomAuthenticationProvider;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.Math.log;
import static java.rmi.server.LogStream.log;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:3000")
//EN TODOS LOS METODOS, SI HAY UN ERROR EN EL CASO DE QUE EXISTAN, 
//NO SALTA EL MENSAJE EN POSTMAN SINO EN CONSOLA DE JAVA, ARREGLAR ESO
public class LoginController {
    @Autowired
       private LoginService loginService;
    
    /*  public static final String convertirSHA256(String password) {
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

         @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO loginRequest) {
            // The actual authentication is handled by Spring Security
            // This method will only be reached if authentication was successful
            //return ResponseEntity.ok(loginRequest.getMail());//LLEGA LA CLAVE PERO NO EL EMAIL
            String contraEncriptada=convertirSHA256(loginRequest.getClave());
           Usuario u = usuarioRepository.findByMail(loginRequest.getMail());//chequear lo que recibo del email y contraseña despues
           //return ResponseEntity.ok(contraEncriptada);
            
            if (u != null && u.getClave().equals(contraEncriptada)) {
            // Si el usuario existe, retornamos el string en el cuerpo de la respuesta
            return ResponseEntity.ok(u.getRolidrol().getNombre());
        } else {
            // Si no se encuentra el usuario, retornamos un error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("contraseña incorrecta");
        }
                 //return ResponseEntity.ok("todo bien");
}*/
     @GetMapping("/info")
     public ResponseEntity<String>obtenerInfoUsuarioLogueado(Authentication authentication){
         String mailLogueado=authentication.getName();
         NombreCompletoDTO infoObtenida=loginService.infoLogin(mailLogueado);
         String infoARetornar=separar(infoObtenida.getNombre(), infoObtenida.getApellido());
          return new ResponseEntity<>(infoARetornar, HttpStatus.OK);
     }
     
     public String separar(String nombre, String apellido){
         return nombre+" "+apellido;
     }

}

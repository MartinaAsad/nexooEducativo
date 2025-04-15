package com.nexo.nexoeducativo.controller;


//import com.nexo.nexoeducativo.configuration.CustomAuthenticationProvider;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="https://nexoeducativo.up.railway.app")
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

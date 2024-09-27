package com.nexo.nexoeducativo;

import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexoeducativoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexoeducativoApplication.class, args);
	}
        @Autowired
        private UsuarioRepository  usuarioReposi;
        @Autowired
        private RolRepository rolRepo;
        
//        @PostConstruct
        public void crearUsuario(){
            
//              Rol r = new Rol();
//              r.setNombre("JefeColegio");
//              this.rolRepo.save(r);
        
            
//            Usuario u = new Usuario();
////            
////            
//            u.setNombre("Seba");
//            u.setApellido("Lopez");
//            u.setDni(3213);
//            u.setEMail("sreb@dgdfg.com");
//            u.setTelefono(23234324 );
//            u.setActivo((short) 1);
                       
//            this.usuarioReposi.save(u);
        }
        
}

package com.nexo.nexoeducativo;

import com.nexo.nexoeducativo.controller.LoginController;
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
                //System.out.println("com.nexo.nexoeducativo.NexoeducativoApplication.main()");
                
        
	
	}
        
        @Autowired
        private UsuarioRepository usuarioRepository;
        
        @Autowired
        private RolRepository rolRepository;
        
        
       /* @PostConstruct
        public void crearUsuario(){
            Usuario u=new Usuario();
            
            u.setNombre("Martina");
            u.setApellido("Asad");
            u.setDni(45874477);
            u.setEMail("mail@gmail.com");
            u.setActivo((short)1);
            
            this.usuarioRepository.save(u);
        }*/
        
       /*  @PostConstruct
        public void crearRol(){
            Rol r = new Rol();
            
            r.setNombre("Administrador");
            this.rolRepository.save(r);
        }*/
        
        
        

}

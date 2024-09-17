
package com.nexo.nexoeducativo.services;

import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuariorepository;
    
    public void crearJefeColegio(UsuarioDTO uDTO){
        
        Usuario u = new Usuario();
        u.setNombre(uDTO.getNombre());
        u.setApellido(uDTO.getApellido());
        u.setEMail(uDTO.geteMail());
        u.setDni(uDTO.getDni());
        u.setActivo((short)1);
        
        this.usuariorepository.save(u);
    }
}


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
    
    public void crearUsuario(UsuarioDTO uDTO){
        Usuario u = new Usuario();
        u.setNombre(uDTO.getNombre());
        u.setApellido(uDTO.getApellido());
        u.setEMail(uDTO.geteMail());
        u.setDni(uDTO.getDni());
        u.setActivo((uDTO.getActivo())? (short)1 : (short)0);  // Asegurarse de guardar el estado "activo"
        
        this.usuariorepository.save(u);
    }
}


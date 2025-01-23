package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioUsuarioRepository extends JpaRepository<UsuarioUsuario, Integer>{
    
     boolean existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(int usuarioIdUsuario, int UsuarioIdUsuario1);

    public boolean existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(Usuario alumno, Usuario padre);
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.UsuarioView (u.idUsuario, u.nombre, u.apellido) FROM Usuario u "
            + "INNER JOIN UsuarioUsuario uu ON u.idUsuario=uu.usuarioIdUsuario "
            + "WHERE uu.usuarioIdUsuario.rolidrol=7 AND uu.usuarioIdUsuario1= :usuarioIdUsuario1 AND uu.usuarioIdUsuario1.rolidrol=6")
    List<UsuarioView> obtenerHijos (Usuario usuarioIdUsuario1);
    
}

package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Martina
 */
public interface EscuelaUsuarioRepository extends JpaRepository<EscuelaUsuario, Integer>{
    
    /*@Modifying
    @Query(value="UPDATE escuela_usuario SET escuela_id_escuela = :escuela_id_escuela) WHERE usuario_id_usuario = :usuario_id_usuario", nativeQuery = true)
    public void updateEscuelaUsuario(@Param("escuela_id_escuela") String escuela_id_escuela, @Param("usuario_id_usuario") int usuario_id_usuario);*/
    
    @Query(value="SELECT * FROM escuela_usuario eu INNER JOIN usuario u ON u.id_usuario=eu.usuario_id_usuario WHERE u.Rol_id_rol=:rolidrol and eu.escuela_id_escuela=:escuelaIdEscuela", nativeQuery = true)
    Optional<EscuelaUsuario> siHayJefeAsignado(int escuelaIdEscuela, int rolidrol);
    
    EscuelaUsuario findEscuelaIdEscuelaByUsuarioIdUsuario(Usuario usuarioIdUsuario);

}

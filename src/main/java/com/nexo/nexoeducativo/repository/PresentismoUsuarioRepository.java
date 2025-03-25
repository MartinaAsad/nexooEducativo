package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.PresentismoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentismoUsuarioRepository extends JpaRepository<PresentismoUsuario, Integer> {
@Query("SELECT pu FROM PresentismoUsuario pu WHERE pu.usuario = :usuario AND pu.fecha = (SELECT MAX(fecha) "
        + "FROM PresentismoUsuario WHERE usuario = :usuario)")    
        List<PresentismoUsuario> findDistinctByUsuarioIdUsuario (Usuario usuarioIdUsuario);

    public List<PresentismoUsuario> findAllByUsuarioIdUsuario(Usuario usuario);

    //public List<PresentismoUsuario> findAllByUsuarioIdUsuario(Usuario usuarioIdUsuario);
    
    @Query("SELECT SUM(p.cantAsistencia) AS asistencias, SUM(p.cantInasistencia) AS inasistencias FROM PresentismoUsuario pu "
            + " JOIN Presentismo p ON pu.presentismoIdPresentismo=p.idPresentismo WHERE pu.usuarioIdUsuario.idUsuario= :idUsuario")
    List<Double> cantTotalPresentismo(Integer idUsuario);
    
    
}

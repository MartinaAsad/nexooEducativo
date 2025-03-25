package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.PresentismoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentismoUsuarioRepository extends JpaRepository<PresentismoUsuario, Integer> {
@Query("SELECT pu FROM PresentismoUsuario pu WHERE pu.usuarioIdUsuario = :usuarioIdUsuario  ORDER BY pu.idPresentismoUsuario DESC LIMIT 1")    
        Optional<PresentismoUsuario> findDistinctByUsuarioIdUsuario (Usuario usuarioIdUsuario);

    public List<PresentismoUsuario> findAllByUsuarioIdUsuario(Usuario usuario);

    //public List<PresentismoUsuario> findAllByUsuarioIdUsuario(Usuario usuarioIdUsuario);
    
    @Query("SELECT SUM(p.cantAsistencia) AS asistencias, SUM(p.cantInasistencia) AS inasistencias FROM PresentismoUsuario pu "
            + " JOIN Presentismo p ON pu.presentismoIdPresentismo=p.idPresentismo WHERE pu.usuarioIdUsuario.idUsuario= :idUsuario")
    List<Double> cantTotalPresentismo(Integer idUsuario);
    
    
}

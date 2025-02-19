package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Presentismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PresentismoRepository extends JpaRepository<Presentismo, Integer> {
    @Query("SELECT p.cantInasistencia FROM Presentismo p "
            + "INNER JOIN PresentismoUsuario pu ON "
            + "pu.presentismoIdPresentismo=p.idPresentismo"
            + " WHERE pu.usuarioIdUsuario.idUsuario= :idUsuario")
    public Integer cantInasistencias(Integer idUsuario);
    
}

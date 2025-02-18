package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Mensaje;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    @Modifying
    @Query("UPDATE Mensaje m SET m.contenido =:contenido WHERE m.idMensaje= :idMensaje")
    void updateContenido(@Param(value="idMensaje") Integer idMensaje,
            @Param(value="contenido") String contenido);
    
    Optional<Mensaje> findByIdMensaje(Integer idMensaje);
}


package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Calificacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer>{
    
    @Modifying
    @Query("UPDATE Calificacion c SET c.nota =:nota WHERE c.idCalificacion= :idCalificacion")
    public void updateNotaByIdCalificacion(@Param("nota") String nota, @Param("idCalificacion") Integer idCalificacion);
    
    Optional <Calificacion> findByNota(String nota);
}

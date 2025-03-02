package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.AsistenciaView;
import com.nexo.nexoeducativo.models.entities.Asistencia;
import com.nexo.nexoeducativo.models.entities.Curso;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer>{
    @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.AsistenciaView ( a.fecha AS fecha, a.idAsistencia AS idAsistencia) FROM Asistencia a " +
" JOIN CursoAsistencia ca ON " +
"ca.asistenciaIdAsistencia=a.idAsistencia " +
"WHERE ca.cursoIdCurso= :cursoIdCurso")
    List<AsistenciaView> fechasAsistencias (Curso cursoIdCurso);
    
   List<Asistencia> findAsistenciaByFecha (Date fecha);
   
    
}

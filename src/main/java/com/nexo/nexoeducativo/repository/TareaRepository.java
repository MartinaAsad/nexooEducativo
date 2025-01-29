package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.CalificacionesHijoView;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> { 
    
    @Query("SELECT DISTINCT(t.descripcion) FROM Tarea t " +
"LEFT JOIN UsuarioTarea ut ON " +
"t.idTarea=ut.tareaIdTarea " +
"WHERE ut.usuarioIdUsuario= :usuarioIdUsuario")
    List<String> descripcionTareas (Usuario usuarioIdUsuario);
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.CalificacionesHijoView "
            + "(t.descripcion as descripcion, c.nota as nota, m.nombre as nombre, u.nombre as nombreP, u.apellido as apellidoP) FROM Tarea t " +
"INNER JOIN UsuarioTarea ut ON " +
"ut.tareaIdTarea=t.idTarea " +
"INNER JOIN Calificacion c ON " +
"c.idCalificacion=t.calificacionIdCalificacion " +
 "INNER JOIN Materia m ON m.idMateria=ut.materiaIdMateria " +
            "INNER JOIN MateriaCurso mc ON "+
            "mc.materiaIdMateria=m.idMateria "+
            "INNER JOIN Usuario u ON "+
            "u.idUsuario=mc.profesor "+
"WHERE ut.usuarioIdUsuario= :usuarioIdUsuario")
    
    List<CalificacionesHijoView> obtenerCalificaciones (Usuario usuarioIdUsuario);
}

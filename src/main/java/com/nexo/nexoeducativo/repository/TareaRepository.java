package com.nexo.nexoeducativo.repository;

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
}

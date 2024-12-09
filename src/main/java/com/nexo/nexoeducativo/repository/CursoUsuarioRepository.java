/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Integer>{
    //parametros segun la entidad en Java
    
    boolean existsByCursoIdCursoAndUsuarioIdUsuario(int cursoIdCurso, int usuarioIdUsuario);

    public boolean existsByCursoIdCursoAndUsuarioIdUsuario(Curso curso, Usuario alumno);
    
    @Query(value="select * from curso_usuario cu INNER JOIN usuario u ON u.id_usuario= cu.usuario_id_usuario INNER JOIN rol r ON r.id_rol=u.Rol_id_rol WHERE u.Rol_id_rol=4 && cu.curso_id_curso= :cursoIdCurso", nativeQuery = true)
     Optional<CursoUsuario> siYaFueAsignado (int cursoIdCurso );
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Integer>{
    //parametros segun la entidad en Java
    
    boolean existsByCursoIdCursoAndUsuarioIdUsuario(int cursoIdCurso, int usuarioIdUsuario);

    public boolean existsByCursoIdCursoAndUsuarioIdUsuario(Curso curso, Usuario alumno);
    
    @Query(value="select * from curso_usuario cu INNER JOIN usuario u ON u.id_usuario= cu.usuario_id_usuario INNER JOIN rol r ON r.id_rol=u.Rol_id_rol WHERE u.Rol_id_rol=4 && cu.curso_id_curso= :cursoIdCurso", nativeQuery = true)
     Optional<CursoUsuario> siYaFueAsignado (int cursoIdCurso );
     
     CursoUsuario findUsuarioIdUsuarioBycursoIdCurso(Curso cursoIdCurso);
     
         @Query(value="""
                 select cu.* from curso_usuario cu INNER JOIN usuario u ON u.id_usuario=cu.usuario_id_usuario INNER JOIN rol r ON r.id_rol=u.Rol_id_rol 
                 INNER JOIN curso c ON c.id_curso=cu.curso_id_curso
                 WHERE r.nombre='preceptor' and cu.curso_id_curso= :idCurso""", nativeQuery=true)
    CursoUsuario findNumeroAndDivisionByIdCurso (@Param("idCurso") Integer idCurso);
    
    Optional<CursoUsuario> findByUsuarioIdUsuario (Usuario usuarioIdUsuario);
    Optional<CursoUsuario> findByCursoIdCursoAndUsuarioIdUsuario (Curso cursoIdCurso, Usuario usuarioIdUsuario);
    
   /* @Modifying
    @Query("UPDATE CursoUsuario cu SET cu.cursoIdCurso= :cursoIdCurso WHERE cu.usuarioIdUsuario= :usuarioIdUsuario")
    void asignarPreceptorDirecto(Curso cursoIdCurso, Usuario usuarioIdUsuario);*/
    
    Optional<CursoUsuario> findByCursoIdCurso(Curso cursoIdCurso);
    Integer deleteByCursoIdCurso(Curso cursoIdCurso );
    
    @Query("SELECT cu FROM CursoUsuario cu WHERE cu.cursoIdCurso = :cursoIdCurso")
    List<CursoUsuario> obtenerRegistros(Curso cursoIdCurso);
    
    @Query("SELECT DISTINCT cu.cursoIdCurso.idCurso FROM CursoUsuario cu WHERE cu.usuarioIdUsuario= :usuarioIdUsuario")
    Integer obtenerCurso (Usuario usuarioIdUsuario);
    
    //@Query(value = "SELECT * FROM curso_usuario WHERE curso_id_curso = :curso_id_curso LIMIT 1", nativeQuery = true)
    Optional<CursoUsuario> findTop1ByUsuarioIdUsuario(Usuario usuarioIdUsuario);
    
   
}

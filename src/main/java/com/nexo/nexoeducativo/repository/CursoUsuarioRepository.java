/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Integer>{
    
    boolean existsByCursoIdCursoAndUsuarioIdUsuario(int cursoIdCurso, int usuarioIdUsuario);

    public boolean existsByCursoIdCursoAndUsuarioIdUsuario(Curso curso, Usuario alumno);
}

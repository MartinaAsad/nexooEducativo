package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.PresentismoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentismoUsuarioRepository extends JpaRepository<PresentismoUsuario, Integer> {
    
}

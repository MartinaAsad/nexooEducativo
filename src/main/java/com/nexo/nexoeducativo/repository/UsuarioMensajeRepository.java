package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.UsuarioMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioMensajeRepository extends JpaRepository<UsuarioMensaje, Integer>{
    
}

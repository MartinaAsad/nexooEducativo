/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "curso_usuario_evento")
@NamedQueries({
    @NamedQuery(name = "CursoUsuarioEvento.findAll", query = "SELECT c FROM CursoUsuarioEvento c")})
@Data
@NoArgsConstructor
public class CursoUsuarioEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_usuario_evento")
    private Integer idCursoUsuarioEvento;
    @JoinColumn(name = "curso_usuario_id_curso_usuario", referencedColumnName = "id_curso_usuario")
    @ManyToOne(optional = false)
    private CursoUsuario cursoUsuarioIdCursoUsuario;
    @JoinColumn(name = "evento_id_evento", referencedColumnName = "id_evento")
    @ManyToOne(optional = false)
    private Evento eventoIdEvento;
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "usuario_asistencia")
@NamedQueries({
    @NamedQuery(name = "UsuarioAsistencia.findAll", query = "SELECT u FROM UsuarioAsistencia u")})
@Data
@NoArgsConstructor
public class UsuarioAsistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_asistencia")
    private Integer idUsuarioAsistencia;
    @JoinColumn(name = "asistencia_id_asistencia", referencedColumnName = "id_asistencia")
    @ManyToOne(optional = false)
    private Asistencia asistenciaIdAsistencia;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false) //lo de cascade es nuevo
    private Usuario usuarioIdUsuario;
}

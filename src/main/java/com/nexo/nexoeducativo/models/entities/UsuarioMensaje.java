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
import lombok.ToString;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "usuario_mensaje")
@NamedQueries({
    @NamedQuery(name = "UsuarioMensaje.findAll", query = "SELECT u FROM UsuarioMensaje u")})
@Data
@NoArgsConstructor
@ToString
public class UsuarioMensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_mensaje")
    private Integer idUsuarioMensaje;
    @JoinColumn(name = "mensaje_id_mensaje", referencedColumnName = "id_mensaje")
    @ManyToOne(optional = false)
    private Mensaje mensajeIdMensaje;
    @JoinColumn(name = "remitente", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario remitente;
    @JoinColumn(name = "destinatario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false) 
    private Usuario destinatario;  // Nuevo campo para el destinatario
            
}

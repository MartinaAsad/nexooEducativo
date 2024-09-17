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

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "usuario_mensaje", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioMensaje.findAll", query = "SELECT u FROM UsuarioMensaje u"),
    @NamedQuery(name = "UsuarioMensaje.findByIdUsuarioMensaje", query = "SELECT u FROM UsuarioMensaje u WHERE u.idUsuarioMensaje = :idUsuarioMensaje")})
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
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public UsuarioMensaje() {
    }

    public UsuarioMensaje(Integer idUsuarioMensaje) {
        this.idUsuarioMensaje = idUsuarioMensaje;
    }

    public Integer getIdUsuarioMensaje() {
        return idUsuarioMensaje;
    }

    public void setIdUsuarioMensaje(Integer idUsuarioMensaje) {
        this.idUsuarioMensaje = idUsuarioMensaje;
    }

    public Mensaje getMensajeIdMensaje() {
        return mensajeIdMensaje;
    }

    public void setMensajeIdMensaje(Mensaje mensajeIdMensaje) {
        this.mensajeIdMensaje = mensajeIdMensaje;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioMensaje != null ? idUsuarioMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioMensaje)) {
            return false;
        }
        UsuarioMensaje other = (UsuarioMensaje) object;
        if ((this.idUsuarioMensaje == null && other.idUsuarioMensaje != null) || (this.idUsuarioMensaje != null && !this.idUsuarioMensaje.equals(other.idUsuarioMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.UsuarioMensaje[ idUsuarioMensaje=" + idUsuarioMensaje + " ]";
    }
    
}

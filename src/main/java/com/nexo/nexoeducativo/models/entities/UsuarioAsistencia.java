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
@Table(name = "usuario_asistencia", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioAsistencia.findAll", query = "SELECT u FROM UsuarioAsistencia u"),
    @NamedQuery(name = "UsuarioAsistencia.findByIdUsuarioAsistencia", query = "SELECT u FROM UsuarioAsistencia u WHERE u.idUsuarioAsistencia = :idUsuarioAsistencia")})
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
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public UsuarioAsistencia() {
    }

    public UsuarioAsistencia(Integer idUsuarioAsistencia) {
        this.idUsuarioAsistencia = idUsuarioAsistencia;
    }

    public Integer getIdUsuarioAsistencia() {
        return idUsuarioAsistencia;
    }

    public void setIdUsuarioAsistencia(Integer idUsuarioAsistencia) {
        this.idUsuarioAsistencia = idUsuarioAsistencia;
    }

    public Asistencia getAsistenciaIdAsistencia() {
        return asistenciaIdAsistencia;
    }

    public void setAsistenciaIdAsistencia(Asistencia asistenciaIdAsistencia) {
        this.asistenciaIdAsistencia = asistenciaIdAsistencia;
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
        hash += (idUsuarioAsistencia != null ? idUsuarioAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioAsistencia)) {
            return false;
        }
        UsuarioAsistencia other = (UsuarioAsistencia) object;
        if ((this.idUsuarioAsistencia == null && other.idUsuarioAsistencia != null) || (this.idUsuarioAsistencia != null && !this.idUsuarioAsistencia.equals(other.idUsuarioAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.UsuarioAsistencia[ idUsuarioAsistencia=" + idUsuarioAsistencia + " ]";
    }
    
}

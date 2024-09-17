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
@Table(name = "usuario_usuario", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioUsuario.findAll", query = "SELECT u FROM UsuarioUsuario u"),
    @NamedQuery(name = "UsuarioUsuario.findByIdUsuarioUsuario", query = "SELECT u FROM UsuarioUsuario u WHERE u.idUsuarioUsuario = :idUsuarioUsuario")})
public class UsuarioUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_usuario")
    private Integer idUsuarioUsuario;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;
    @JoinColumn(name = "usuario_id_usuario1", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario1;

    public UsuarioUsuario() {
    }

    public UsuarioUsuario(Integer idUsuarioUsuario) {
        this.idUsuarioUsuario = idUsuarioUsuario;
    }

    public Integer getIdUsuarioUsuario() {
        return idUsuarioUsuario;
    }

    public void setIdUsuarioUsuario(Integer idUsuarioUsuario) {
        this.idUsuarioUsuario = idUsuarioUsuario;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Usuario getUsuarioIdUsuario1() {
        return usuarioIdUsuario1;
    }

    public void setUsuarioIdUsuario1(Usuario usuarioIdUsuario1) {
        this.usuarioIdUsuario1 = usuarioIdUsuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioUsuario != null ? idUsuarioUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioUsuario)) {
            return false;
        }
        UsuarioUsuario other = (UsuarioUsuario) object;
        if ((this.idUsuarioUsuario == null && other.idUsuarioUsuario != null) || (this.idUsuarioUsuario != null && !this.idUsuarioUsuario.equals(other.idUsuarioUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.UsuarioUsuario[ idUsuarioUsuario=" + idUsuarioUsuario + " ]";
    }
    
}

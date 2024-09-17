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
@Table(name = "usuario_tarea", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioTarea.findAll", query = "SELECT u FROM UsuarioTarea u"),
    @NamedQuery(name = "UsuarioTarea.findByIdUsuarioTarea", query = "SELECT u FROM UsuarioTarea u WHERE u.idUsuarioTarea = :idUsuarioTarea")})
public class UsuarioTarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_tarea")
    private Integer idUsuarioTarea;
    @JoinColumn(name = "tarea_id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne(optional = false)
    private Tarea tareaIdTarea;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public UsuarioTarea() {
    }

    public UsuarioTarea(Integer idUsuarioTarea) {
        this.idUsuarioTarea = idUsuarioTarea;
    }

    public Integer getIdUsuarioTarea() {
        return idUsuarioTarea;
    }

    public void setIdUsuarioTarea(Integer idUsuarioTarea) {
        this.idUsuarioTarea = idUsuarioTarea;
    }

    public Tarea getTareaIdTarea() {
        return tareaIdTarea;
    }

    public void setTareaIdTarea(Tarea tareaIdTarea) {
        this.tareaIdTarea = tareaIdTarea;
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
        hash += (idUsuarioTarea != null ? idUsuarioTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioTarea)) {
            return false;
        }
        UsuarioTarea other = (UsuarioTarea) object;
        if ((this.idUsuarioTarea == null && other.idUsuarioTarea != null) || (this.idUsuarioTarea != null && !this.idUsuarioTarea.equals(other.idUsuarioTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.UsuarioTarea[ idUsuarioTarea=" + idUsuarioTarea + " ]";
    }
    
}

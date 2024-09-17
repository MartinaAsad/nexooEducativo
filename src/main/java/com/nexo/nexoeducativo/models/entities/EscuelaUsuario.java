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
@Table(name = "escuela_usuario", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "EscuelaUsuario.findAll", query = "SELECT e FROM EscuelaUsuario e"),
    @NamedQuery(name = "EscuelaUsuario.findByIdEscuelaUsuario", query = "SELECT e FROM EscuelaUsuario e WHERE e.idEscuelaUsuario = :idEscuelaUsuario")})
public class EscuelaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_escuela_usuario")
    private Integer idEscuelaUsuario;
    @JoinColumn(name = "escuela_id_escuela", referencedColumnName = "id_escuela")
    @ManyToOne(optional = false)
    private Escuela escuelaIdEscuela;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public EscuelaUsuario() {
    }

    public EscuelaUsuario(Integer idEscuelaUsuario) {
        this.idEscuelaUsuario = idEscuelaUsuario;
    }

    public Integer getIdEscuelaUsuario() {
        return idEscuelaUsuario;
    }

    public void setIdEscuelaUsuario(Integer idEscuelaUsuario) {
        this.idEscuelaUsuario = idEscuelaUsuario;
    }

    public Escuela getEscuelaIdEscuela() {
        return escuelaIdEscuela;
    }

    public void setEscuelaIdEscuela(Escuela escuelaIdEscuela) {
        this.escuelaIdEscuela = escuelaIdEscuela;
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
        hash += (idEscuelaUsuario != null ? idEscuelaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EscuelaUsuario)) {
            return false;
        }
        EscuelaUsuario other = (EscuelaUsuario) object;
        if ((this.idEscuelaUsuario == null && other.idEscuelaUsuario != null) || (this.idEscuelaUsuario != null && !this.idEscuelaUsuario.equals(other.idEscuelaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.EscuelaUsuario[ idEscuelaUsuario=" + idEscuelaUsuario + " ]";
    }
    
}

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
@Table(name = "curso_mensaje", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "CursoMensaje.findAll", query = "SELECT c FROM CursoMensaje c"),
    @NamedQuery(name = "CursoMensaje.findByIdCursoMensaje", query = "SELECT c FROM CursoMensaje c WHERE c.idCursoMensaje = :idCursoMensaje")})
public class CursoMensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_mensaje")
    private Integer idCursoMensaje;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    @JoinColumn(name = "mensaje_id_mensaje", referencedColumnName = "id_mensaje")
    @ManyToOne(optional = false)
    private Mensaje mensajeIdMensaje;

    public CursoMensaje() {
    }

    public CursoMensaje(Integer idCursoMensaje) {
        this.idCursoMensaje = idCursoMensaje;
    }

    public Integer getIdCursoMensaje() {
        return idCursoMensaje;
    }

    public void setIdCursoMensaje(Integer idCursoMensaje) {
        this.idCursoMensaje = idCursoMensaje;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public Mensaje getMensajeIdMensaje() {
        return mensajeIdMensaje;
    }

    public void setMensajeIdMensaje(Mensaje mensajeIdMensaje) {
        this.mensajeIdMensaje = mensajeIdMensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoMensaje != null ? idCursoMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoMensaje)) {
            return false;
        }
        CursoMensaje other = (CursoMensaje) object;
        if ((this.idCursoMensaje == null && other.idCursoMensaje != null) || (this.idCursoMensaje != null && !this.idCursoMensaje.equals(other.idCursoMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.CursoMensaje[ idCursoMensaje=" + idCursoMensaje + " ]";
    }
    
}

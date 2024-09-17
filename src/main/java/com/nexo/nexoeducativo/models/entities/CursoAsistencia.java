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
@Table(name = "curso_asistencia", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "CursoAsistencia.findAll", query = "SELECT c FROM CursoAsistencia c"),
    @NamedQuery(name = "CursoAsistencia.findByIdCursoAsistencia", query = "SELECT c FROM CursoAsistencia c WHERE c.idCursoAsistencia = :idCursoAsistencia")})
public class CursoAsistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_asistencia")
    private Integer idCursoAsistencia;
    @JoinColumn(name = "asistencia_id_asistencia", referencedColumnName = "id_asistencia")
    @ManyToOne(optional = false)
    private Asistencia asistenciaIdAsistencia;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;

    public CursoAsistencia() {
    }

    public CursoAsistencia(Integer idCursoAsistencia) {
        this.idCursoAsistencia = idCursoAsistencia;
    }

    public Integer getIdCursoAsistencia() {
        return idCursoAsistencia;
    }

    public void setIdCursoAsistencia(Integer idCursoAsistencia) {
        this.idCursoAsistencia = idCursoAsistencia;
    }

    public Asistencia getAsistenciaIdAsistencia() {
        return asistenciaIdAsistencia;
    }

    public void setAsistenciaIdAsistencia(Asistencia asistenciaIdAsistencia) {
        this.asistenciaIdAsistencia = asistenciaIdAsistencia;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoAsistencia != null ? idCursoAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoAsistencia)) {
            return false;
        }
        CursoAsistencia other = (CursoAsistencia) object;
        if ((this.idCursoAsistencia == null && other.idCursoAsistencia != null) || (this.idCursoAsistencia != null && !this.idCursoAsistencia.equals(other.idCursoAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.CursoAsistencia[ idCursoAsistencia=" + idCursoAsistencia + " ]";
    }
    
}

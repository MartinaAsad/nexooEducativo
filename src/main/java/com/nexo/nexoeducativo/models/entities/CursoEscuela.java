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
@Table(name = "curso_escuela", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "CursoEscuela.findAll", query = "SELECT c FROM CursoEscuela c"),
    @NamedQuery(name = "CursoEscuela.findByIdCursoEscuela", query = "SELECT c FROM CursoEscuela c WHERE c.idCursoEscuela = :idCursoEscuela")})
public class CursoEscuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_escuela")
    private Integer idCursoEscuela;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    @JoinColumn(name = "escuela_id_escuela", referencedColumnName = "id_escuela")
    @ManyToOne(optional = false)
    private Escuela escuelaIdEscuela;

    public CursoEscuela() {
    }

    public CursoEscuela(Integer idCursoEscuela) {
        this.idCursoEscuela = idCursoEscuela;
    }

    public Integer getIdCursoEscuela() {
        return idCursoEscuela;
    }

    public void setIdCursoEscuela(Integer idCursoEscuela) {
        this.idCursoEscuela = idCursoEscuela;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public Escuela getEscuelaIdEscuela() {
        return escuelaIdEscuela;
    }

    public void setEscuelaIdEscuela(Escuela escuelaIdEscuela) {
        this.escuelaIdEscuela = escuelaIdEscuela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoEscuela != null ? idCursoEscuela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEscuela)) {
            return false;
        }
        CursoEscuela other = (CursoEscuela) object;
        if ((this.idCursoEscuela == null && other.idCursoEscuela != null) || (this.idCursoEscuela != null && !this.idCursoEscuela.equals(other.idCursoEscuela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.CursoEscuela[ idCursoEscuela=" + idCursoEscuela + " ]";
    }
    
}

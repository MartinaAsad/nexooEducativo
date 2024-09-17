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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "curso", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findByNumero", query = "SELECT c FROM Curso c WHERE c.numero = :numero"),
    @NamedQuery(name = "Curso.findByDivision", query = "SELECT c FROM Curso c WHERE c.division = :division"),
    @NamedQuery(name = "Curso.findByActivo", query = "SELECT c FROM Curso c WHERE c.activo = :activo")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "division")
    private Character division;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoUsuario> cursoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<MateriaCurso> materiaCursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoAsistencia> cursoAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoMensaje> cursoMensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoEscuela> cursoEscuelaList;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, int numero, Character division, short activo) {
        this.idCurso = idCurso;
        this.numero = numero;
        this.division = division;
        this.activo = activo;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Character getDivision() {
        return division;
    }

    public void setDivision(Character division) {
        this.division = division;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public List<CursoUsuario> getCursoUsuarioList() {
        return cursoUsuarioList;
    }

    public void setCursoUsuarioList(List<CursoUsuario> cursoUsuarioList) {
        this.cursoUsuarioList = cursoUsuarioList;
    }

    public List<MateriaCurso> getMateriaCursoList() {
        return materiaCursoList;
    }

    public void setMateriaCursoList(List<MateriaCurso> materiaCursoList) {
        this.materiaCursoList = materiaCursoList;
    }

    public List<CursoAsistencia> getCursoAsistenciaList() {
        return cursoAsistenciaList;
    }

    public void setCursoAsistenciaList(List<CursoAsistencia> cursoAsistenciaList) {
        this.cursoAsistenciaList = cursoAsistenciaList;
    }

    public List<CursoMensaje> getCursoMensajeList() {
        return cursoMensajeList;
    }

    public void setCursoMensajeList(List<CursoMensaje> cursoMensajeList) {
        this.cursoMensajeList = cursoMensajeList;
    }

    public List<CursoEscuela> getCursoEscuelaList() {
        return cursoEscuelaList;
    }

    public void setCursoEscuelaList(List<CursoEscuela> cursoEscuelaList) {
        this.cursoEscuelaList = cursoEscuelaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Curso[ idCurso=" + idCurso + " ]";
    }
    
}

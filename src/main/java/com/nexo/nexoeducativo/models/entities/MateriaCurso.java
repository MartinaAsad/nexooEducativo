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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "materia_curso", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "MateriaCurso.findAll", query = "SELECT m FROM MateriaCurso m"),
    @NamedQuery(name = "MateriaCurso.findByIdMateriaCurso", query = "SELECT m FROM MateriaCurso m WHERE m.idMateriaCurso = :idMateriaCurso"),
    @NamedQuery(name = "MateriaCurso.findByDia", query = "SELECT m FROM MateriaCurso m WHERE m.dia = :dia"),
    @NamedQuery(name = "MateriaCurso.findByHoraInicio", query = "SELECT m FROM MateriaCurso m WHERE m.horaInicio = :horaInicio"),
    @NamedQuery(name = "MateriaCurso.findByHoraFin", query = "SELECT m FROM MateriaCurso m WHERE m.horaFin = :horaFin")})
public class MateriaCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_curso")
    private Integer idMateriaCurso;
    @Column(name = "dia")
    private String dia;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materia materiaIdMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaCursoIdMateriaCurso")
    private List<MateriaCursoMaterial> materiaCursoMaterialList;

    public MateriaCurso() {
    }

    public MateriaCurso(Integer idMateriaCurso) {
        this.idMateriaCurso = idMateriaCurso;
    }

    public Integer getIdMateriaCurso() {
        return idMateriaCurso;
    }

    public void setIdMateriaCurso(Integer idMateriaCurso) {
        this.idMateriaCurso = idMateriaCurso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public Materia getMateriaIdMateria() {
        return materiaIdMateria;
    }

    public void setMateriaIdMateria(Materia materiaIdMateria) {
        this.materiaIdMateria = materiaIdMateria;
    }

    public List<MateriaCursoMaterial> getMateriaCursoMaterialList() {
        return materiaCursoMaterialList;
    }

    public void setMateriaCursoMaterialList(List<MateriaCursoMaterial> materiaCursoMaterialList) {
        this.materiaCursoMaterialList = materiaCursoMaterialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaCurso != null ? idMateriaCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaCurso)) {
            return false;
        }
        MateriaCurso other = (MateriaCurso) object;
        if ((this.idMateriaCurso == null && other.idMateriaCurso != null) || (this.idMateriaCurso != null && !this.idMateriaCurso.equals(other.idMateriaCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.MateriaCurso[ idMateriaCurso=" + idMateriaCurso + " ]";
    }
    
}

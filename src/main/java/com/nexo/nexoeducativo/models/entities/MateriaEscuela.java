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
@Table(name = "materia_escuela", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "MateriaEscuela.findAll", query = "SELECT m FROM MateriaEscuela m"),
    @NamedQuery(name = "MateriaEscuela.findByIdMateriaEscuela", query = "SELECT m FROM MateriaEscuela m WHERE m.idMateriaEscuela = :idMateriaEscuela")})
public class MateriaEscuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_escuela")
    private Integer idMateriaEscuela;
    @JoinColumn(name = "escuela_id_escuela", referencedColumnName = "id_escuela")
    @ManyToOne(optional = false)
    private Escuela escuelaIdEscuela;
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materia materiaIdMateria;

    public MateriaEscuela() {
    }

    public MateriaEscuela(Integer idMateriaEscuela) {
        this.idMateriaEscuela = idMateriaEscuela;
    }

    public Integer getIdMateriaEscuela() {
        return idMateriaEscuela;
    }

    public void setIdMateriaEscuela(Integer idMateriaEscuela) {
        this.idMateriaEscuela = idMateriaEscuela;
    }

    public Escuela getEscuelaIdEscuela() {
        return escuelaIdEscuela;
    }

    public void setEscuelaIdEscuela(Escuela escuelaIdEscuela) {
        this.escuelaIdEscuela = escuelaIdEscuela;
    }

    public Materia getMateriaIdMateria() {
        return materiaIdMateria;
    }

    public void setMateriaIdMateria(Materia materiaIdMateria) {
        this.materiaIdMateria = materiaIdMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaEscuela != null ? idMateriaEscuela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaEscuela)) {
            return false;
        }
        MateriaEscuela other = (MateriaEscuela) object;
        if ((this.idMateriaEscuela == null && other.idMateriaEscuela != null) || (this.idMateriaEscuela != null && !this.idMateriaEscuela.equals(other.idMateriaEscuela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.MateriaEscuela[ idMateriaEscuela=" + idMateriaEscuela + " ]";
    }
    
}

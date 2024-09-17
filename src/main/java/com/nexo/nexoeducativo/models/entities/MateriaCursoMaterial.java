/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "materia_curso_material", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "MateriaCursoMaterial.findAll", query = "SELECT m FROM MateriaCursoMaterial m"),
    @NamedQuery(name = "MateriaCursoMaterial.findByIdMateriaCursoMaterial", query = "SELECT m FROM MateriaCursoMaterial m WHERE m.idMateriaCursoMaterial = :idMateriaCursoMaterial")})
public class MateriaCursoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_materia_curso_material")
    private Integer idMateriaCursoMaterial;
    @JoinColumn(name = "materia_curso_id_materia_curso", referencedColumnName = "id_materia_curso")
    @ManyToOne(optional = false)
    private MateriaCurso materiaCursoIdMateriaCurso;
    @JoinColumn(name = "material_id_material", referencedColumnName = "id_material")
    @ManyToOne(optional = false)
    private Material materialIdMaterial;

    public MateriaCursoMaterial() {
    }

    public MateriaCursoMaterial(Integer idMateriaCursoMaterial) {
        this.idMateriaCursoMaterial = idMateriaCursoMaterial;
    }

    public Integer getIdMateriaCursoMaterial() {
        return idMateriaCursoMaterial;
    }

    public void setIdMateriaCursoMaterial(Integer idMateriaCursoMaterial) {
        this.idMateriaCursoMaterial = idMateriaCursoMaterial;
    }

    public MateriaCurso getMateriaCursoIdMateriaCurso() {
        return materiaCursoIdMateriaCurso;
    }

    public void setMateriaCursoIdMateriaCurso(MateriaCurso materiaCursoIdMateriaCurso) {
        this.materiaCursoIdMateriaCurso = materiaCursoIdMateriaCurso;
    }

    public Material getMaterialIdMaterial() {
        return materialIdMaterial;
    }

    public void setMaterialIdMaterial(Material materialIdMaterial) {
        this.materialIdMaterial = materialIdMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaCursoMaterial != null ? idMateriaCursoMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaCursoMaterial)) {
            return false;
        }
        MateriaCursoMaterial other = (MateriaCursoMaterial) object;
        if ((this.idMateriaCursoMaterial == null && other.idMateriaCursoMaterial != null) || (this.idMateriaCursoMaterial != null && !this.idMateriaCursoMaterial.equals(other.idMateriaCursoMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.MateriaCursoMaterial[ idMateriaCursoMaterial=" + idMateriaCursoMaterial + " ]";
    }
    
}

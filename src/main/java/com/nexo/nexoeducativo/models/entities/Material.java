/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "material", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByIdMaterial", query = "SELECT m FROM Material m WHERE m.idMaterial = :idMaterial"),
    @NamedQuery(name = "Material.findByArchivo", query = "SELECT m FROM Material m WHERE m.archivo = :archivo"),
    @NamedQuery(name = "Material.findByDescripcion", query = "SELECT m FROM Material m WHERE m.descripcion = :descripcion")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_material")
    private Integer idMaterial;
    @Basic(optional = false)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialIdMaterial")
    private List<MateriaCursoMaterial> materiaCursoMaterialList;

    public Material() {
    }

    public Material(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material(Integer idMaterial, String archivo) {
        this.idMaterial = idMaterial;
        this.archivo = archivo;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Material[ idMaterial=" + idMaterial + " ]";
    }
    
}

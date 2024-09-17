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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "escuela", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Escuela.findAll", query = "SELECT e FROM Escuela e"),
    @NamedQuery(name = "Escuela.findByIdEscuela", query = "SELECT e FROM Escuela e WHERE e.idEscuela = :idEscuela"),
    @NamedQuery(name = "Escuela.findByNombre", query = "SELECT e FROM Escuela e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Escuela.findByDireccion", query = "SELECT e FROM Escuela e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Escuela.findByActivo", query = "SELECT e FROM Escuela e WHERE e.activo = :activo")})
public class Escuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_escuela")
    private Integer idEscuela;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "plan_id_plan", referencedColumnName = "id_plan")
    @ManyToOne(optional = false)
    private Plan planIdPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<EscuelaComprobantePago> escuelaComprobantePagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<MateriaEscuela> materiaEscuelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<CursoEscuela> cursoEscuelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<EscuelaUsuario> escuelaUsuarioList;

    public Escuela() {
    }

    public Escuela(Integer idEscuela) {
        this.idEscuela = idEscuela;
    }

    public Escuela(Integer idEscuela, String nombre, String direccion, short activo) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
        this.direccion = direccion;
        this.activo = activo;
    }

    public Integer getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(Integer idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Plan getPlanIdPlan() {
        return planIdPlan;
    }

    public void setPlanIdPlan(Plan planIdPlan) {
        this.planIdPlan = planIdPlan;
    }

    public List<EscuelaComprobantePago> getEscuelaComprobantePagoList() {
        return escuelaComprobantePagoList;
    }

    public void setEscuelaComprobantePagoList(List<EscuelaComprobantePago> escuelaComprobantePagoList) {
        this.escuelaComprobantePagoList = escuelaComprobantePagoList;
    }

    public List<MateriaEscuela> getMateriaEscuelaList() {
        return materiaEscuelaList;
    }

    public void setMateriaEscuelaList(List<MateriaEscuela> materiaEscuelaList) {
        this.materiaEscuelaList = materiaEscuelaList;
    }

    public List<CursoEscuela> getCursoEscuelaList() {
        return cursoEscuelaList;
    }

    public void setCursoEscuelaList(List<CursoEscuela> cursoEscuelaList) {
        this.cursoEscuelaList = cursoEscuelaList;
    }

    public List<EscuelaUsuario> getEscuelaUsuarioList() {
        return escuelaUsuarioList;
    }

    public void setEscuelaUsuarioList(List<EscuelaUsuario> escuelaUsuarioList) {
        this.escuelaUsuarioList = escuelaUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuela != null ? idEscuela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escuela)) {
            return false;
        }
        Escuela other = (Escuela) object;
        if ((this.idEscuela == null && other.idEscuela != null) || (this.idEscuela != null && !this.idEscuela.equals(other.idEscuela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Escuela[ idEscuela=" + idEscuela + " ]";
    }
    
}

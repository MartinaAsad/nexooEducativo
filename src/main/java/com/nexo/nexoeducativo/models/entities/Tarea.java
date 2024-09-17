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
@Table(name = "tarea", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t"),
    @NamedQuery(name = "Tarea.findByIdTarea", query = "SELECT t FROM Tarea t WHERE t.idTarea = :idTarea"),
    @NamedQuery(name = "Tarea.findByDescripcion", query = "SELECT t FROM Tarea t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tarea.findByArchivo", query = "SELECT t FROM Tarea t WHERE t.archivo = :archivo")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarea")
    private Integer idTarea;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "archivo")
    private String archivo;
    @JoinColumn(name = "calificacion_id_calificacion", referencedColumnName = "id_calificacion")
    @ManyToOne(optional = false)
    private Calificacion calificacionIdCalificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tareaIdTarea")
    private List<UsuarioTarea> usuarioTareaList;

    public Tarea() {
    }

    public Tarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Tarea(Integer idTarea, String descripcion) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Calificacion getCalificacionIdCalificacion() {
        return calificacionIdCalificacion;
    }

    public void setCalificacionIdCalificacion(Calificacion calificacionIdCalificacion) {
        this.calificacionIdCalificacion = calificacionIdCalificacion;
    }

    public List<UsuarioTarea> getUsuarioTareaList() {
        return usuarioTareaList;
    }

    public void setUsuarioTareaList(List<UsuarioTarea> usuarioTareaList) {
        this.usuarioTareaList = usuarioTareaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.idTarea == null && other.idTarea != null) || (this.idTarea != null && !this.idTarea.equals(other.idTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Tarea[ idTarea=" + idTarea + " ]";
    }
    
}

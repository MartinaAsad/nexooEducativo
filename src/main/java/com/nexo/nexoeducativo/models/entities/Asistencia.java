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
@Table(name = "asistencia", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByAsistio", query = "SELECT a FROM Asistencia a WHERE a.asistio = :asistio"),
    @NamedQuery(name = "Asistencia.findByRetiroAntes", query = "SELECT a FROM Asistencia a WHERE a.retiroAntes = :retiroAntes")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "asistio")
    private short asistio;
    @Column(name = "retiro_antes")
    private Short retiroAntes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistenciaIdAsistencia")
    private List<CursoAsistencia> cursoAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistenciaIdAsistencia")
    private List<UsuarioAsistencia> usuarioAsistenciaList;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Integer idAsistencia, Date fecha, short asistio) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.asistio = asistio;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getAsistio() {
        return asistio;
    }

    public void setAsistio(short asistio) {
        this.asistio = asistio;
    }

    public Short getRetiroAntes() {
        return retiroAntes;
    }

    public void setRetiroAntes(Short retiroAntes) {
        this.retiroAntes = retiroAntes;
    }

    public List<CursoAsistencia> getCursoAsistenciaList() {
        return cursoAsistenciaList;
    }

    public void setCursoAsistenciaList(List<CursoAsistencia> cursoAsistenciaList) {
        this.cursoAsistenciaList = cursoAsistenciaList;
    }

    public List<UsuarioAsistencia> getUsuarioAsistenciaList() {
        return usuarioAsistenciaList;
    }

    public void setUsuarioAsistenciaList(List<UsuarioAsistencia> usuarioAsistenciaList) {
        this.usuarioAsistenciaList = usuarioAsistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}

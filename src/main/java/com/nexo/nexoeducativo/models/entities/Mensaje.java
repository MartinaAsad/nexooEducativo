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
@Table(name = "mensaje", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByIdMensaje", query = "SELECT m FROM Mensaje m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "Mensaje.findByFecha", query = "SELECT m FROM Mensaje m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensaje.findByContenido", query = "SELECT m FROM Mensaje m WHERE m.contenido = :contenido"),
    @NamedQuery(name = "Mensaje.findByArchivo", query = "SELECT m FROM Mensaje m WHERE m.archivo = :archivo")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mensaje")
    private Integer idMensaje;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "archivo")
    private String archivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensajeIdMensaje")
    private List<CursoMensaje> cursoMensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensajeIdMensaje")
    private List<UsuarioMensaje> usuarioMensajeList;

    public Mensaje() {
    }

    public Mensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Mensaje(Integer idMensaje, String contenido) {
        this.idMensaje = idMensaje;
        this.contenido = contenido;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public List<CursoMensaje> getCursoMensajeList() {
        return cursoMensajeList;
    }

    public void setCursoMensajeList(List<CursoMensaje> cursoMensajeList) {
        this.cursoMensajeList = cursoMensajeList;
    }

    public List<UsuarioMensaje> getUsuarioMensajeList() {
        return usuarioMensajeList;
    }

    public void setUsuarioMensajeList(List<UsuarioMensaje> usuarioMensajeList) {
        this.usuarioMensajeList = usuarioMensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Mensaje[ idMensaje=" + idMensaje + " ]";
    }
    
}

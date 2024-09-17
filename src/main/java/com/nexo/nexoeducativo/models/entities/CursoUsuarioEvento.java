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
@Table(name = "curso_usuario_evento", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "CursoUsuarioEvento.findAll", query = "SELECT c FROM CursoUsuarioEvento c"),
    @NamedQuery(name = "CursoUsuarioEvento.findByIdCursoUsuarioEvento", query = "SELECT c FROM CursoUsuarioEvento c WHERE c.idCursoUsuarioEvento = :idCursoUsuarioEvento")})
public class CursoUsuarioEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_usuario_evento")
    private Integer idCursoUsuarioEvento;
    @JoinColumn(name = "curso_usuario_id_curso_usuario", referencedColumnName = "id_curso_usuario")
    @ManyToOne(optional = false)
    private CursoUsuario cursoUsuarioIdCursoUsuario;
    @JoinColumn(name = "evento_id_evento", referencedColumnName = "id_evento")
    @ManyToOne(optional = false)
    private Evento eventoIdEvento;

    public CursoUsuarioEvento() {
    }

    public CursoUsuarioEvento(Integer idCursoUsuarioEvento) {
        this.idCursoUsuarioEvento = idCursoUsuarioEvento;
    }

    public Integer getIdCursoUsuarioEvento() {
        return idCursoUsuarioEvento;
    }

    public void setIdCursoUsuarioEvento(Integer idCursoUsuarioEvento) {
        this.idCursoUsuarioEvento = idCursoUsuarioEvento;
    }

    public CursoUsuario getCursoUsuarioIdCursoUsuario() {
        return cursoUsuarioIdCursoUsuario;
    }

    public void setCursoUsuarioIdCursoUsuario(CursoUsuario cursoUsuarioIdCursoUsuario) {
        this.cursoUsuarioIdCursoUsuario = cursoUsuarioIdCursoUsuario;
    }

    public Evento getEventoIdEvento() {
        return eventoIdEvento;
    }

    public void setEventoIdEvento(Evento eventoIdEvento) {
        this.eventoIdEvento = eventoIdEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoUsuarioEvento != null ? idCursoUsuarioEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoUsuarioEvento)) {
            return false;
        }
        CursoUsuarioEvento other = (CursoUsuarioEvento) object;
        if ((this.idCursoUsuarioEvento == null && other.idCursoUsuarioEvento != null) || (this.idCursoUsuarioEvento != null && !this.idCursoUsuarioEvento.equals(other.idCursoUsuarioEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.CursoUsuarioEvento[ idCursoUsuarioEvento=" + idCursoUsuarioEvento + " ]";
    }
    
}

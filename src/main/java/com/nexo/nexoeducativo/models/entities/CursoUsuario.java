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
@Table(name = "curso_usuario", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "CursoUsuario.findAll", query = "SELECT c FROM CursoUsuario c"),
    @NamedQuery(name = "CursoUsuario.findByIdCursoUsuario", query = "SELECT c FROM CursoUsuario c WHERE c.idCursoUsuario = :idCursoUsuario")})
public class CursoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_usuario")
    private Integer idCursoUsuario;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoUsuarioIdCursoUsuario")
    private List<CursoUsuarioEvento> cursoUsuarioEventoList;

    public CursoUsuario() {
    }

    public CursoUsuario(Integer idCursoUsuario) {
        this.idCursoUsuario = idCursoUsuario;
    }

    public Integer getIdCursoUsuario() {
        return idCursoUsuario;
    }

    public void setIdCursoUsuario(Integer idCursoUsuario) {
        this.idCursoUsuario = idCursoUsuario;
    }

    public Curso getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Curso cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public List<CursoUsuarioEvento> getCursoUsuarioEventoList() {
        return cursoUsuarioEventoList;
    }

    public void setCursoUsuarioEventoList(List<CursoUsuarioEvento> cursoUsuarioEventoList) {
        this.cursoUsuarioEventoList = cursoUsuarioEventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoUsuario != null ? idCursoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoUsuario)) {
            return false;
        }
        CursoUsuario other = (CursoUsuario) object;
        if ((this.idCursoUsuario == null && other.idCursoUsuario != null) || (this.idCursoUsuario != null && !this.idCursoUsuario.equals(other.idCursoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.CursoUsuario[ idCursoUsuario=" + idCursoUsuario + " ]";
    }
    
}

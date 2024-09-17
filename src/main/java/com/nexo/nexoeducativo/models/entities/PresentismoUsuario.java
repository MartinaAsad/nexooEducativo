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
@Table(name = "presentismo_usuario", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "PresentismoUsuario.findAll", query = "SELECT p FROM PresentismoUsuario p"),
    @NamedQuery(name = "PresentismoUsuario.findByIdPresentismoUsuario", query = "SELECT p FROM PresentismoUsuario p WHERE p.idPresentismoUsuario = :idPresentismoUsuario")})
public class PresentismoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presentismo_usuario")
    private Integer idPresentismoUsuario;
    @JoinColumn(name = "presentismo_id_presentismo", referencedColumnName = "id_presentismo")
    @ManyToOne(optional = false)
    private Presentismo presentismoIdPresentismo;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public PresentismoUsuario() {
    }

    public PresentismoUsuario(Integer idPresentismoUsuario) {
        this.idPresentismoUsuario = idPresentismoUsuario;
    }

    public Integer getIdPresentismoUsuario() {
        return idPresentismoUsuario;
    }

    public void setIdPresentismoUsuario(Integer idPresentismoUsuario) {
        this.idPresentismoUsuario = idPresentismoUsuario;
    }

    public Presentismo getPresentismoIdPresentismo() {
        return presentismoIdPresentismo;
    }

    public void setPresentismoIdPresentismo(Presentismo presentismoIdPresentismo) {
        this.presentismoIdPresentismo = presentismoIdPresentismo;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresentismoUsuario != null ? idPresentismoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PresentismoUsuario)) {
            return false;
        }
        PresentismoUsuario other = (PresentismoUsuario) object;
        if ((this.idPresentismoUsuario == null && other.idPresentismoUsuario != null) || (this.idPresentismoUsuario != null && !this.idPresentismoUsuario.equals(other.idPresentismoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.PresentismoUsuario[ idPresentismoUsuario=" + idPresentismoUsuario + " ]";
    }
    
}

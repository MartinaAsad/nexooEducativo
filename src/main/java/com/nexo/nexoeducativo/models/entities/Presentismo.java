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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "presentismo", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Presentismo.findAll", query = "SELECT p FROM Presentismo p"),
    @NamedQuery(name = "Presentismo.findByIdPresentismo", query = "SELECT p FROM Presentismo p WHERE p.idPresentismo = :idPresentismo"),
    @NamedQuery(name = "Presentismo.findByCantAsistencia", query = "SELECT p FROM Presentismo p WHERE p.cantAsistencia = :cantAsistencia"),
    @NamedQuery(name = "Presentismo.findByCantInasistencia", query = "SELECT p FROM Presentismo p WHERE p.cantInasistencia = :cantInasistencia")})
public class Presentismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presentismo")
    private Integer idPresentismo;
    @Column(name = "cant_asistencia")
    private Integer cantAsistencia;
    @Column(name = "cant_inasistencia")
    private Integer cantInasistencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentismoIdPresentismo")
    private List<PresentismoUsuario> presentismoUsuarioList;

    public Presentismo() {
    }

    public Presentismo(Integer idPresentismo) {
        this.idPresentismo = idPresentismo;
    }

    public Integer getIdPresentismo() {
        return idPresentismo;
    }

    public void setIdPresentismo(Integer idPresentismo) {
        this.idPresentismo = idPresentismo;
    }

    public Integer getCantAsistencia() {
        return cantAsistencia;
    }

    public void setCantAsistencia(Integer cantAsistencia) {
        this.cantAsistencia = cantAsistencia;
    }

    public Integer getCantInasistencia() {
        return cantInasistencia;
    }

    public void setCantInasistencia(Integer cantInasistencia) {
        this.cantInasistencia = cantInasistencia;
    }

    public List<PresentismoUsuario> getPresentismoUsuarioList() {
        return presentismoUsuarioList;
    }

    public void setPresentismoUsuarioList(List<PresentismoUsuario> presentismoUsuarioList) {
        this.presentismoUsuarioList = presentismoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresentismo != null ? idPresentismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presentismo)) {
            return false;
        }
        Presentismo other = (Presentismo) object;
        if ((this.idPresentismo == null && other.idPresentismo != null) || (this.idPresentismo != null && !this.idPresentismo.equals(other.idPresentismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Presentismo[ idPresentismo=" + idPresentismo + " ]";
    }
    
}

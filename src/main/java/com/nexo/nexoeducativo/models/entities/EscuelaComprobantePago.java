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
@Table(name = "escuela_comprobante_pago", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "EscuelaComprobantePago.findAll", query = "SELECT e FROM EscuelaComprobantePago e"),
    @NamedQuery(name = "EscuelaComprobantePago.findByIdEscuelaComprobantePago", query = "SELECT e FROM EscuelaComprobantePago e WHERE e.idEscuelaComprobantePago = :idEscuelaComprobantePago")})
public class EscuelaComprobantePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_escuela_comprobante_pago")
    private Integer idEscuelaComprobantePago;
    @JoinColumn(name = "comprobante_pago_id_comprobante_pago", referencedColumnName = "id_comprobante_pago")
    @ManyToOne(optional = false)
    private ComprobantePago comprobantePagoIdComprobantePago;
    @JoinColumn(name = "escuela_id_escuela", referencedColumnName = "id_escuela")
    @ManyToOne(optional = false)
    private Escuela escuelaIdEscuela;

    public EscuelaComprobantePago() {
    }

    public EscuelaComprobantePago(Integer idEscuelaComprobantePago) {
        this.idEscuelaComprobantePago = idEscuelaComprobantePago;
    }

    public Integer getIdEscuelaComprobantePago() {
        return idEscuelaComprobantePago;
    }

    public void setIdEscuelaComprobantePago(Integer idEscuelaComprobantePago) {
        this.idEscuelaComprobantePago = idEscuelaComprobantePago;
    }

    public ComprobantePago getComprobantePagoIdComprobantePago() {
        return comprobantePagoIdComprobantePago;
    }

    public void setComprobantePagoIdComprobantePago(ComprobantePago comprobantePagoIdComprobantePago) {
        this.comprobantePagoIdComprobantePago = comprobantePagoIdComprobantePago;
    }

    public Escuela getEscuelaIdEscuela() {
        return escuelaIdEscuela;
    }

    public void setEscuelaIdEscuela(Escuela escuelaIdEscuela) {
        this.escuelaIdEscuela = escuelaIdEscuela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuelaComprobantePago != null ? idEscuelaComprobantePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EscuelaComprobantePago)) {
            return false;
        }
        EscuelaComprobantePago other = (EscuelaComprobantePago) object;
        if ((this.idEscuelaComprobantePago == null && other.idEscuelaComprobantePago != null) || (this.idEscuelaComprobantePago != null && !this.idEscuelaComprobantePago.equals(other.idEscuelaComprobantePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.EscuelaComprobantePago[ idEscuelaComprobantePago=" + idEscuelaComprobantePago + " ]";
    }
    
}

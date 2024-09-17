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
@Table(name = "comprobante_pago", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "ComprobantePago.findAll", query = "SELECT c FROM ComprobantePago c"),
    @NamedQuery(name = "ComprobantePago.findByIdComprobantePago", query = "SELECT c FROM ComprobantePago c WHERE c.idComprobantePago = :idComprobantePago"),
    @NamedQuery(name = "ComprobantePago.findByFecha", query = "SELECT c FROM ComprobantePago c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "ComprobantePago.findByImporte", query = "SELECT c FROM ComprobantePago c WHERE c.importe = :importe")})
public class ComprobantePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comprobante_pago")
    private Integer idComprobantePago;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "importe")
    private long importe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comprobantePagoIdComprobantePago")
    private List<EscuelaComprobantePago> escuelaComprobantePagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comprobantePagoIdComprobantePago")
    private List<UsuarioComprobantePago> usuarioComprobantePagoList;

    public ComprobantePago() {
    }

    public ComprobantePago(Integer idComprobantePago) {
        this.idComprobantePago = idComprobantePago;
    }

    public ComprobantePago(Integer idComprobantePago, Date fecha, long importe) {
        this.idComprobantePago = idComprobantePago;
        this.fecha = fecha;
        this.importe = importe;
    }

    public Integer getIdComprobantePago() {
        return idComprobantePago;
    }

    public void setIdComprobantePago(Integer idComprobantePago) {
        this.idComprobantePago = idComprobantePago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getImporte() {
        return importe;
    }

    public void setImporte(long importe) {
        this.importe = importe;
    }

    public List<EscuelaComprobantePago> getEscuelaComprobantePagoList() {
        return escuelaComprobantePagoList;
    }

    public void setEscuelaComprobantePagoList(List<EscuelaComprobantePago> escuelaComprobantePagoList) {
        this.escuelaComprobantePagoList = escuelaComprobantePagoList;
    }

    public List<UsuarioComprobantePago> getUsuarioComprobantePagoList() {
        return usuarioComprobantePagoList;
    }

    public void setUsuarioComprobantePagoList(List<UsuarioComprobantePago> usuarioComprobantePagoList) {
        this.usuarioComprobantePagoList = usuarioComprobantePagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComprobantePago != null ? idComprobantePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprobantePago)) {
            return false;
        }
        ComprobantePago other = (ComprobantePago) object;
        if ((this.idComprobantePago == null && other.idComprobantePago != null) || (this.idComprobantePago != null && !this.idComprobantePago.equals(other.idComprobantePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.ComprobantePago[ idComprobantePago=" + idComprobantePago + " ]";
    }
    
}

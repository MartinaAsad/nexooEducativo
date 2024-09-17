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
@Table(name = "usuario_comprobante_pago", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "UsuarioComprobantePago.findAll", query = "SELECT u FROM UsuarioComprobantePago u"),
    @NamedQuery(name = "UsuarioComprobantePago.findByIdUsuarioComprobantePago", query = "SELECT u FROM UsuarioComprobantePago u WHERE u.idUsuarioComprobantePago = :idUsuarioComprobantePago")})
public class UsuarioComprobantePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_comprobante_pago")
    private Integer idUsuarioComprobantePago;
    @JoinColumn(name = "comprobante_pago_id_comprobante_pago", referencedColumnName = "id_comprobante_pago")
    @ManyToOne(optional = false)
    private ComprobantePago comprobantePagoIdComprobantePago;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public UsuarioComprobantePago() {
    }

    public UsuarioComprobantePago(Integer idUsuarioComprobantePago) {
        this.idUsuarioComprobantePago = idUsuarioComprobantePago;
    }

    public Integer getIdUsuarioComprobantePago() {
        return idUsuarioComprobantePago;
    }

    public void setIdUsuarioComprobantePago(Integer idUsuarioComprobantePago) {
        this.idUsuarioComprobantePago = idUsuarioComprobantePago;
    }

    public ComprobantePago getComprobantePagoIdComprobantePago() {
        return comprobantePagoIdComprobantePago;
    }

    public void setComprobantePagoIdComprobantePago(ComprobantePago comprobantePagoIdComprobantePago) {
        this.comprobantePagoIdComprobantePago = comprobantePagoIdComprobantePago;
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
        hash += (idUsuarioComprobantePago != null ? idUsuarioComprobantePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioComprobantePago)) {
            return false;
        }
        UsuarioComprobantePago other = (UsuarioComprobantePago) object;
        if ((this.idUsuarioComprobantePago == null && other.idUsuarioComprobantePago != null) || (this.idUsuarioComprobantePago != null && !this.idUsuarioComprobantePago.equals(other.idUsuarioComprobantePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.UsuarioComprobantePago[ idUsuarioComprobantePago=" + idUsuarioComprobantePago + " ]";
    }
    
}

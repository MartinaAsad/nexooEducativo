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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "comprobante_pago")
@NamedQueries({
    @NamedQuery(name = "ComprobantePago.findAll", query = "SELECT c FROM ComprobantePago c")})
@Data
@NoArgsConstructor
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
    
}

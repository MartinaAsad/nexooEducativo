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
@Table(name = "calificacion")
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c")})
@Data
@NoArgsConstructor
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;
    @Basic(optional = false)
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calificacionIdCalificacion")
    private List<Tarea> tareaList;
}
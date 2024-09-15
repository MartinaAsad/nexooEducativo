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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "escuela")
@NamedQueries({
    @NamedQuery(name = "Escuela.findAll", query = "SELECT e FROM Escuela e")})
@Data
@NoArgsConstructor
public class Escuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_escuela")
    private Integer idEscuela;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "plan_id_plan", referencedColumnName = "id_plan")
    @ManyToOne(optional = false)
    private Plan planIdPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<EscuelaComprobantePago> escuelaComprobantePagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<MateriaEscuela> materiaEscuelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<CursoEscuela> cursoEscuelaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuelaIdEscuela")
    private List<EscuelaUsuario> escuelaUsuarioList;
    
}

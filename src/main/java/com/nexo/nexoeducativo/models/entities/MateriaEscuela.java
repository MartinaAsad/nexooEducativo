/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "materia_escuela")
@NamedQueries({
    @NamedQuery(name = "MateriaEscuela.findAll", query = "SELECT m FROM MateriaEscuela m")})
@Data
@NoArgsConstructor
@ToString(exclude = {"escuelaIdEscuela"})

public class MateriaEscuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_escuela")
    private Integer idMateriaEscuela;
    @JoinColumn(name = "escuela_id_escuela", referencedColumnName = "id_escuela")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Escuela escuelaIdEscuela;
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Materia materiaIdMateria;
    
}

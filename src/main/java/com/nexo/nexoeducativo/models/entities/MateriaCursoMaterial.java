/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "materia_curso_material")
@NamedQueries({
    @NamedQuery(name = "MateriaCursoMaterial.findAll", query = "SELECT m FROM MateriaCursoMaterial m")})
@Data
@NoArgsConstructor
public class MateriaCursoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_materia_curso_material")
    private Integer idMateriaCursoMaterial;
    @JoinColumn(name = "materia_curso_id_materia_curso", referencedColumnName = "id_materia_curso")
    @ManyToOne(optional = false)
    private MateriaCurso materiaCursoIdMateriaCurso;
    @JoinColumn(name = "material_id_material", referencedColumnName = "id_material")
    @ManyToOne(optional = false)
    private Material materialIdMaterial;
    
}

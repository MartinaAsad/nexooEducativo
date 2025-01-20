
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materia_curso_material")
@NamedQueries({
    @NamedQuery(name = "MateriaCursoMaterial.findAll", query = "SELECT m FROM MateriaCursoMaterial m")})
@Data
@NoArgsConstructor
public class MateriaCursoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

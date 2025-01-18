
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "materia_curso")
@NamedQueries({
    @NamedQuery(name = "MateriaCurso.findAll", query = "SELECT m FROM MateriaCurso m")})
@Data
@NoArgsConstructor
public class MateriaCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia_curso")
    private Integer idMateriaCurso;
    @Column(name = "dia")
    private String dia;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private LocalTime horaInicio;//poner tipo de dato Time en mysql
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private LocalTime horaFin;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    
    //agregue este nuevo campo
    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Usuario profesor;
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materia materiaIdMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaCursoIdMateriaCurso")
    private List<MateriaCursoMaterial> materiaCursoMaterialList;
    
}

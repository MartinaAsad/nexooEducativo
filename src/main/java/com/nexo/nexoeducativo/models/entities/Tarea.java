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
import jakarta.persistence.Lob;
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
@Table(name = "tarea")
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")})
@Data
@NoArgsConstructor
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarea")
    private Integer idTarea;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "archivo")
   @Lob  // Indica que es un campo grande (BLOB)
    private byte[] archivo;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "tipo_archivo")
    private String tipoArchivo;
    
    //relacion con la materia
     @ManyToOne(optional = false)
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    private Materia materiaIdMateria; 
    @JoinColumn(name = "calificacion_id_calificacion", referencedColumnName = "id_calificacion")
    @ManyToOne(optional = false)
    private Calificacion calificacionIdCalificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tareaIdTarea")
    private List<UsuarioTarea> usuarioTareaList;

}

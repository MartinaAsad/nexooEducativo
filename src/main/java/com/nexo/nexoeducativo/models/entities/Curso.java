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
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")})
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "division")
    private Character division;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cursoIdCurso", orphanRemoval = true)//PARA QUE AL ELIMINAR UN CURSO, NO SE BORREN LOS USUARIOS ASOCIADOS
    private List<CursoUsuario> cursoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<MateriaCurso> materiaCursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoAsistencia> cursoAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoMensaje> cursoMensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoIdCurso")
    private List<CursoEscuela> cursoEscuelaList;
}

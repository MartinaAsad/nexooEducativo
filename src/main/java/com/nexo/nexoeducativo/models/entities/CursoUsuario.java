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
import lombok.ToString;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "curso_usuario")
@NamedQueries({
    @NamedQuery(name = "CursoUsuario.findAll", query = "SELECT c FROM CursoUsuario c")})
@Data
@NoArgsConstructor
@ToString
public class CursoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso_usuario")
    private Integer idCursoUsuario;
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso")
    @ToString.Exclude //para evitar stackOverFlowError
    @ManyToOne(optional = false)
    private Curso cursoIdCurso;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ToString.Exclude //para evitar stackOverFlowError
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;
     @ToString.Exclude //para evitar stackOverFlowError
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoUsuarioIdCursoUsuario")
    private List<CursoUsuarioEvento> cursoUsuarioEventoList;
    
}

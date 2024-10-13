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
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Entity
@Table(name = "presentismo")
@NamedQueries({
    @NamedQuery(name = "Presentismo.findAll", query = "SELECT p FROM Presentismo p")})
@Data
@NoArgsConstructor
public class Presentismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presentismo")
    private Integer idPresentismo;
    @Column(name = "cant_asistencia")
    private Integer cantAsistencia;
    @Column(name = "cant_inasistencia")
    private Integer cantInasistencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentismoIdPresentismo")
    private List<PresentismoUsuario> presentismoUsuarioList;
    
}

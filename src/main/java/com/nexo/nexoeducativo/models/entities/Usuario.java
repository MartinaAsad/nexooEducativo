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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "dni")
    private int dni;
    @Basic(optional = false)
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "telefono")
    private Integer telefono;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @Column(name = "pago_cuota")
    private Short pagoCuota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<CursoUsuario> cursoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<PresentismoUsuario> presentismoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioTarea> usuarioTareaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioAsistencia> usuarioAsistenciaList;
    @JoinColumn(name = "Rol_id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol rolidrol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioMensaje> usuarioMensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<EscuelaUsuario> escuelaUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioComprobantePago> usuarioComprobantePagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioUsuario> usuarioUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario1")
    private List<UsuarioUsuario> usuarioUsuarioList1;
}

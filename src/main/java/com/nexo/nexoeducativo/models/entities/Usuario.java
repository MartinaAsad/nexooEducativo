package com.nexo.nexoeducativo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
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
    @Column(name = "mail")
    private String mail;
    @Column(name = "clave")
    private String clave;
    @Column(name = "telefono")
    private Integer telefono;
    @Basic(optional = false)
    @Column(name = "activo")
    private short activo;
    @Column(name = "pago_cuota")
    private Short pagoCuota;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<CursoUsuario> cursoUsuarioList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<PresentismoUsuario> presentismoUsuarioList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioTarea> usuarioTareaList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioAsistencia> usuarioAsistenciaList;
    @JoinColumn(name = "Rol_id_rol", referencedColumnName = "id_rol")
    @ToString.Exclude 
    @JsonIgnore
    @ManyToOne
    private Rol rolidrol;
    @ToString.Exclude
    @JsonIgnore
    @Column(name = "tipo_jornada")
    private String tipoJornada;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remitente")
    private List<UsuarioMensaje> usuarioMensajeList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<EscuelaUsuario> escuelaUsuarioList;
     @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioComprobantePago> usuarioComprobantePagoList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<UsuarioUsuario> usuarioUsuarioList;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario1")
    private List<UsuarioUsuario> usuarioUsuarioList1;
    @ToString.Exclude 
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor")
    private List<MateriaCurso> materiaCursoList;
}

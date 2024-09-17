
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


@Entity
@Table(name = "usuario", catalog = "nexoeducativo", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni"),
    @NamedQuery(name = "Usuario.findByEMail", query = "SELECT u FROM Usuario u WHERE u.eMail = :eMail"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuario.findByPagoCuota", query = "SELECT u FROM Usuario u WHERE u.pagoCuota = :pagoCuota")})
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

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, int dni, String eMail, short activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.eMail = eMail;
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Short getPagoCuota() {
        return pagoCuota;
    }

    public void setPagoCuota(Short pagoCuota) {
        this.pagoCuota = pagoCuota;
    }

    public List<CursoUsuario> getCursoUsuarioList() {
        return cursoUsuarioList;
    }

    public void setCursoUsuarioList(List<CursoUsuario> cursoUsuarioList) {
        this.cursoUsuarioList = cursoUsuarioList;
    }

    public List<PresentismoUsuario> getPresentismoUsuarioList() {
        return presentismoUsuarioList;
    }

    public void setPresentismoUsuarioList(List<PresentismoUsuario> presentismoUsuarioList) {
        this.presentismoUsuarioList = presentismoUsuarioList;
    }

    public List<UsuarioTarea> getUsuarioTareaList() {
        return usuarioTareaList;
    }

    public void setUsuarioTareaList(List<UsuarioTarea> usuarioTareaList) {
        this.usuarioTareaList = usuarioTareaList;
    }

    public List<UsuarioAsistencia> getUsuarioAsistenciaList() {
        return usuarioAsistenciaList;
    }

    public void setUsuarioAsistenciaList(List<UsuarioAsistencia> usuarioAsistenciaList) {
        this.usuarioAsistenciaList = usuarioAsistenciaList;
    }

    public Rol getRolidrol() {
        return rolidrol;
    }

    public void setRolidrol(Rol rolidrol) {
        this.rolidrol = rolidrol;
    }

    public List<UsuarioMensaje> getUsuarioMensajeList() {
        return usuarioMensajeList;
    }

    public void setUsuarioMensajeList(List<UsuarioMensaje> usuarioMensajeList) {
        this.usuarioMensajeList = usuarioMensajeList;
    }

    public List<EscuelaUsuario> getEscuelaUsuarioList() {
        return escuelaUsuarioList;
    }

    public void setEscuelaUsuarioList(List<EscuelaUsuario> escuelaUsuarioList) {
        this.escuelaUsuarioList = escuelaUsuarioList;
    }

    public List<UsuarioComprobantePago> getUsuarioComprobantePagoList() {
        return usuarioComprobantePagoList;
    }

    public void setUsuarioComprobantePagoList(List<UsuarioComprobantePago> usuarioComprobantePagoList) {
        this.usuarioComprobantePagoList = usuarioComprobantePagoList;
    }

    public List<UsuarioUsuario> getUsuarioUsuarioList() {
        return usuarioUsuarioList;
    }

    public void setUsuarioUsuarioList(List<UsuarioUsuario> usuarioUsuarioList) {
        this.usuarioUsuarioList = usuarioUsuarioList;
    }

    public List<UsuarioUsuario> getUsuarioUsuarioList1() {
        return usuarioUsuarioList1;
    }

    public void setUsuarioUsuarioList1(List<UsuarioUsuario> usuarioUsuarioList1) {
        this.usuarioUsuarioList1 = usuarioUsuarioList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.davinci.nexoeducativo.model.entities.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}

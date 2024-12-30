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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asistencia")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")})
@Data   //hace metodos get, set, constructor con parametros, metodo equals y toString
@NoArgsConstructor //hace constructor vacio
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "asistio")
    private short asistio;
    @Column(name = "retiro_antes")
    private Short retiroAntes;
    @Column(name = "media_falta")
    private Short mediaFalta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistenciaIdAsistencia")
    private List<CursoAsistencia> cursoAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asistenciaIdAsistencia")
    private List<UsuarioAsistencia> usuarioAsistenciaList;
    
}

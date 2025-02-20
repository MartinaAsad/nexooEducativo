package com.nexo.nexoeducativo.repository;


import com.nexo.nexoeducativo.models.dto.request.CancelarMembresiaDTO;
import com.nexo.nexoeducativo.models.dto.request.DesplegableChatView;
import com.nexo.nexoeducativo.models.dto.request.InfoAlumnoCuotaView;
import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioSegunRolDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
       final Logger LOGGER = LoggerFactory.getLogger(UsuarioRepository.class);
    
    boolean existsByDni(int dni);

    boolean existsByMail(String mail);

    boolean existsByRolidrolAndIdUsuario(Rol rolidrol, Integer idUsuario);
    
    //@Query("SELECT")
    boolean existsByIdUsuarioAndRolidrol(int idUsuario, Rol rolidrol );

    //@Query("SELECT u.rol FROM Usuario u WHERE u.idUsuario = :idUsuario")
   @Query("SELECT u.rolidrol FROM Usuario u WHERE u.idUsuario = :idUsuario")
Rol findRolidrolByIdUsuario(@Param("idUsuario") Integer idUsuario);

    
    
    
    //boolean existsByRolidrolAndIdUsuario(Rol rolidrol, Integer idUsuario);
    //EL PROBLEMA ES QUE EL MAIL NO ESTA LLEGANDO BIEN AL METODO, DESDE EL CONTROLADOR FUNCIONA BIEN PERO NO LLEGA ACA

    //@Query(value = "SELECT * FROM Usuario u WHERE u.mail=:mail", nativeQuery = true)
    //Usuario findByMail(String mail);

    @Query(value = "SELECT u.nombre, u.apellido FROM Usuario u WHERE id_usuario = :idUsuario", nativeQuery = true)
    List<NombreCompletoDTO> getFullName(@Param("idUsuario") int idUsuario);

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido FROM usuario u LEFT JOIN escuela_usuario eu ON eu.usuario_id_usuario=u.id_usuario WHERE u.Rol_id_rol=2 and eu.escuela_id_escuela IS NULL", nativeQuery = true)
    List<NombreCompletoDTO> getJefeColegioWithoutSchool();
    
    //PARA EL LOGIN
     Optional<Usuario> findByMail(String mail);
     
     
     
     //para chequear usuario logueado
      Usuario findUsuarioByMail(String mail); 
      
      //para obtener todos los usuario segun el rol
      @Query(value="SELECT new com.nexo.nexoeducativo.models.dto.request.InfoUsuarioSegunRolDTO(u.idUsuario, u.nombre, u.apellido, u.dni) FROM Usuario"
              + " u INNER JOIN Rol r ON r.idRol=u.rolidrol INNER JOIN EscuelaUsuario eu ON eu.usuarioIdUsuario=u.idUsuario WHERE r.nombre = :nombre AND eu.escuelaIdEscuela = :escuelaIdEscuela")
      List<InfoUsuarioSegunRolDTO>getUsuarioByRol(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "escuelaIdEscuela") Escuela escuelaIdEscuela );
       
      //para obtener nombre y apellido del usuario logueado
          @Query(value = "SELECT u.nombre, u.apellido FROM Usuario u WHERE mail = :mail", nativeQuery = true)
          NombreCompletoDTO getFullName(String mail);
          
      Usuario findNombreAndApellidoByIdUsuario(Integer idUsuario);
      
      @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.UsuarioView(u.idUsuario,u.nombre, u.apellido) FROM Usuario u "
              + "JOIN CursoUsuario cu ON u.idUsuario=cu.usuarioIdUsuario"
              + " WHERE cu.cursoIdCurso= :curso and u.rolidrol=7")
      
      List<UsuarioView> infoAlumnos(@Param("curso") Curso curso);
      
       @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.UsuarioView(u.nombre, u.apellido) FROM Usuario u "
              + "JOIN CursoUsuario cu ON u.idUsuario=cu.usuarioIdUsuario"
              + " WHERE cu.cursoIdCurso= :curso and u.rolidrol=4")
      UsuarioView infoPreceptor(@Param("curso") Curso curso);
      
      //para mostrar numero y division de los cursos de un colegio
      /*@Query("SELECT eu.escuelaIdEscuela FROM Usuario u INNER JOIN EscuelaUsuario eu ON u.idUsuario=eu.usuarioIdUsuario"
              + "WHERE u.rolIdRol=2 and u.mail= :mail and eu.escuelaIdEscuela= :escuelaIdEscuela ")
      int buscarEscuelaDelJefeColegio(@Param("mail") String mail, @Param("escuelaIdEscuela") int escuelaIdEscuela);*/
      
      @Query("SELECT eu.escuelaIdEscuela FROM Usuario u INNER JOIN EscuelaUsuario eu ON u.idUsuario=eu.usuarioIdUsuario "
              + "WHERE u.rolidrol=2 and u.mail= :mail")
      Escuela obtenerIdEscuela(@Param("mail") String mail);
      
      @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.verCursoView (c.numero, c.division, c.activo, c.idCurso)"
              + " FROM Curso c INNER JOIN CursoEscuela ce ON ce.cursoIdCurso=c.idCurso WHERE ce.escuelaIdEscuela= :escuelaIdEscuela")
      List<verCursoView> obtenerCursos(@Param("escuelaIdEscuela") Escuela escuelaIdEscuela);
      
         @Query("SELECT eu.escuelaIdEscuela FROM Usuario u INNER JOIN EscuelaUsuario eu ON u.idUsuario=eu.usuarioIdUsuario "
              + "WHERE u.rolidrol=3 and u.mail= :mail")
      Escuela obtenerIdEscuelaAdministrativo(@Param("mail") String mail);
       @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.verCursoView (c.numero, c.division, c.activo, c.idCurso)"
              + " FROM Curso c INNER JOIN CursoEscuela ce ON " +
"ce.cursoIdCurso=c.idCurso " +
"WHERE c.activo=1 and ce.escuelaIdEscuela= :escuelaIdEscuela")
      List<verCursoView> obtenerCursosAdministrativo(@Param("escuelaIdEscuela") Escuela escuelaIdEscuela);

      //para preceptor MAS ADELANTE VER COMO MODIFICAR ESTO PARA QUE SEA REUTILIZABLE CON EL ROL ID 2 Y 3
     Usuario findIdUsuarioByMail(String mail);
     
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.verCursoView(c.numero, c.division, c.activo) " +
       "FROM CursoUsuario cu " +
       "JOIN cu.cursoIdCurso c " +
       "WHERE cu.usuarioIdUsuario.idUsuario = :usuario")
List<verCursoView> obtenerCursosPreceptor(@Param("usuario") Integer usuario);

 @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido FROM Usuario u "
              + "JOIN CursoUsuario cu ON u.idUsuario=cu.usuarioIdUsuario"
              + " WHERE cu.cursoIdCurso= :curso and u.activo=1 and u.rolidrol=7")
      List<NombreCompletoDTO> tomarLista(@Param("curso") Curso curso);
      
      
      @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido FROM Usuario u"
              + " JOIN EscuelaUsuario eu ON " +
"eu.usuarioIdUsuario=u.idUsuario " +
"WHERE u.rolidrol= :rol and eu.escuelaIdEscuela= :escuela")
      List<NombreCompletoDTO> obtenerInfoUsuario(Rol rol, Escuela escuela);
      
      @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido"
              + " FROM Usuario u INNER JOIN Rol r ON r.idRol=u.rolidrol"
              + " INNER JOIN CursoUsuario cu ON cu.usuarioIdUsuario=u.idUsuario "
              + "INNER JOIN CursoEscuela ce ON ce.cursoIdCurso=cu.cursoIdCurso "
              + "WHERE r.nombre='alumno' AND ce.escuelaIdEscuela= :escuelaIdEscuela AND u.activo=1")
      List<NombreCompletoDTO> obtenerAlumnos(Escuela escuelaIdEscuela);

      @Query("SELECT u FROM Usuario u LEFT JOIN CursoUsuario cu ON cu.usuarioIdUsuario=u.idUsuario "
              + "WHERE cu.cursoIdCurso= :c and u.rolidrol=7")
    public List<Usuario> findByCurso(Curso c);
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.InfoAlumnoCuotaView"
            + " (u.idUsuario as idUsuario, u.nombre AS nombre, u.apellido AS apellido, c.importe AS importe, u.tipoJornada AS tipoJornada) " +
"FROM Usuario u " +
"LEFT JOIN Cuota c ON c.tipoJornada = u.tipoJornada " +
"LEFT JOIN UsuarioUsuario uu ON uu.usuarioIdUsuario = u.idUsuario " +
"WHERE uu.usuarioIdUsuario1 = :usuarioIdUsuario1")
    public List<InfoAlumnoCuotaView> obtenerCuotaHijos(Usuario usuarioIdUsuario1);
    
      @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido, u.mail AS mail FROM Usuario u"
              + " JOIN EscuelaUsuario eu ON " +
"eu.usuarioIdUsuario=u.idUsuario " +
"WHERE u.rolidrol= :rol and eu.escuelaIdEscuela= :escuela and u.activo= 1")
      List<DesplegableChatView> obtenerInfoDesplegables(Rol rol, Escuela escuela);
      
       @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido, u.mail AS mail"
              + " FROM Usuario u INNER JOIN Rol r ON r.idRol=u.rolidrol"
              + " INNER JOIN CursoUsuario cu ON cu.usuarioIdUsuario=u.idUsuario "
              + "INNER JOIN CursoEscuela ce ON ce.cursoIdCurso=cu.cursoIdCurso "
              + "WHERE r.nombre='alumno' AND ce.escuelaIdEscuela= :escuelaIdEscuela AND u.activo=1")
      List<DesplegableChatView> obtenerAlumnosDesplegable(Escuela escuelaIdEscuela);
      
      @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido, u.mail AS mail"
              + " FROM Usuario u INNER JOIN Rol r ON r.idRol=u.rolidrol"
              + " INNER JOIN CursoUsuario cu ON cu.usuarioIdUsuario=u.idUsuario "
              + "INNER JOIN CursoEscuela ce ON ce.cursoIdCurso=cu.cursoIdCurso "
              + "WHERE r.nombre='alumno' AND ce.escuelaIdEscuela= :escuelaIdEscuela AND u.activo=1 AND cu.cursoIdCurso= :c")
      List<DesplegableChatView> obtenerAlumnosProfe(Escuela escuelaIdEscuela, Curso c );
      
     @Query("SELECT u.idUsuario AS id_usuario, u.nombre AS nombre, u.apellido AS apellido, u.mail AS mail " +
                  "FROM Usuario u " +
                  "JOIN UsuarioUsuario uu ON uu.usuarioIdUsuario = u.idUsuario " +
                  "JOIN CursoUsuario cu ON cu.usuarioIdUsuario = uu.usuarioIdUsuario1 " +
                  "JOIN Usuario padre ON uu.usuarioIdUsuario1= padre.idUsuario " +
                  "WHERE padre.rolidrol=7 AND cu.cursoIdCurso = :cursoIdCurso")

      List<DesplegableChatView> infoPadresCurso(Curso cursoIdCurso);
      
      @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.CancelarMembresiaDTO (u.idUsuario AS id1, u.activo AS activo1,"
              + " e.idEscuela AS id2, e.activo AS activo2) FROM Usuario u " +
"INNER JOIN EscuelaUsuario eu ON " +
"eu.usuarioIdUsuario=u.idUsuario " +
"INNER JOIN Escuela e ON " +
"e.idEscuela=eu.escuelaIdEscuela " +
"WHERE eu.escuelaIdEscuela= :escuelaIdEscuela")
      List<CancelarMembresiaDTO> usuariosEscuela(Escuela escuelaIdEscuela);
    
      

          
          
          
          
       
       
}

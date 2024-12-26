/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoView;
import com.nexo.nexoeducativo.models.dto.request.MateriaView;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
    
    @Autowired
    private CursoUsuarioRepository cursoUsuarioRepository;
    
    @Autowired
    private MateriaCursoRepository materiaCursoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
        
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoService.class);
    
       public boolean siYaExisteCombinacion(Integer escuelaId, int numero, Character division){
        return escuelaRepository.existsCursoInEscuela(escuelaId, numero, division);
    
}
    //PROBAR NUEVAMENTE
    public void crearCurso(CursoDTO c){
        Curso curso=new Curso();
        curso.setNumero(c.getNumero());
        curso.setDivision(c.getDivision());
        curso.setActivo(c.getActivo());
        int numero=curso.getNumero();
        Character division=curso.getDivision();
        
        Escuela e=new Escuela();
        e.setIdEscuela(c.getEscuela());//se almacena el id de la escuela
        int escuelaId=e.getIdEscuela();
        
        CursoEscuela ce=new CursoEscuela();//tabla intermedia donde se va a guardar a que escuela esta asociado ese curso
        ce.setCursoIdCurso(curso);//se guarda el id del curso creado
        ce.setEscuelaIdEscuela(e);//se guarda el id de la escuela asociada
        
        //valida que la division ingresada sea solo una letra
        if(!Character.isLetter(c.getDivision())){
            throw new IllegalArgumentException("La división debe ser un carácter alfabético");
        }else if (!Character.toString(curso.getDivision()).matches("^[a-z]$")){
//luego valida que el caracter ingresado sea una minuscula nomas
            throw new IllegalArgumentException("El campo division solo puede una letra minuscula");
        }
        
        //para que se guarde el curso, el id del colegio,el numero y division ingresada (la combinacion) NO debe existir previamente en la base
        if (!siYaExisteCombinacion(escuelaId,numero, division)) {
            this.cursoRepository.save(curso);//se guarda el curso
            this.cursoEscuelaRepository.save(ce);//se guarda la info en la tabla intermedia
         }else{
             throw new IllegalArgumentException("El curso ya existe en la escuela!");
        }

    }
    
    public List<CursoView> seleccionarCurso (int idCurso){
        /*datos del curso*/
        Curso c=cursoRepository.findById(idCurso)
                 .orElseThrow(() -> new CursoNotFound("El curso no existe"));
        /*datos del preceptor*/
        // cu=cursoUsuarioRepository.findUsuarioIdUsuarioBycursoIdCurso(c);//obtengo el id del preceptor
        //LOGGER.info("info del repository: "+cu.getUsuarioIdUsuario());
        //Usuario u = cu.getUsuarioIdUsuario(); //guardo el id aqui para luego obtener nombre y apellido
        //LOGGER.info("info del repository: "+u.getRolidrol());
        
        UsuarioView p=usuarioRepository.infoPreceptor(c);
        //en caso de que no haya un preceptor
        if(p==null){
            throw new UsuarioNotFoundException("No hay un preceptor en este curso");
        }
        
        /*datos de la materia (nombre y profesor)*/
        List<Object[]> lista=materiaCursoRepository.infoMateria(c.getIdCurso());
        List<MateriaView> lista2 = lista.stream()
        .map(row -> new MateriaView((String) row[2], (String) row[0], (String) row[1]))
        .collect(Collectors.toList());
        // LOGGER.info("contenido de la lista"+lista2.toString());
         //LOGGER.info("Parametro : "+c);
         
         /*datos de los usuarios*/
          List<UsuarioView> alumnos=usuarioRepository.infoAlumnos(c);
         //LOGGER.info("lis a de alumnos: "+alumnos.toString());
         
        CursoView datosCurso=new CursoView(c.getNumero(), c.getDivision(), p.getNombre(), p.getApellido(), lista2, alumnos);
        List<CursoView> cView = new ArrayList<CursoView>();
        cView.add(datosCurso);
        return cView;
    }
    
    public List<verCursoView> verCursos(String mail){
        Escuela escuelaIdEscuela=usuarioRepository.obtenerIdEscuela(mail);//buscar el id de la escuela del usuario logueado
        List<verCursoView> cursos= usuarioRepository.obtenerCursos(escuelaIdEscuela);
        
        return cursos;
    }
}

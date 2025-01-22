package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.exception.MaterialNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.MaterialDTO;
import com.nexo.nexoeducativo.models.dto.request.SeleccionarMaterialView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.MateriaCursoMaterial;
import com.nexo.nexoeducativo.models.entities.Material;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoMaterialRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.MateriaRepository;
import com.nexo.nexoeducativo.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Martina
 */
@Service
@Transactional
public class MaterialService {
    
    @Autowired
    private MaterialRepository materRepository;
    
    @Autowired
    private MateriaCursoRepository mcRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Autowired
    private MateriaCursoMaterialRepository mcmRepository;
    
    
    public void altaMaterial(MultipartFile file,MaterialDTO m, Usuario profesor) throws IOException{//endpooint altaMaterial
        
        Material material=new Material();
        guardarImagen(file, material);
        //material.setArchivo(urlArchivo);
        material.setDescripcion(m.getDescripcion());
        materRepository.save(material);
        
        //entidades necesarias para asociar el material a un curso
        Curso c=cursoRepository.findById(m.getIdCurso())
                .orElseThrow(()-> new CursoNotFound("No existe el curso ingresado" +m.getIdCurso()));
        
        Materia materia = materiaRepository.findById(m.getIdMateria()).orElseThrow(
                ()->new MateriaNotFoundException("La materia ingresada no existe"));
        
        MateriaCurso mc=new MateriaCurso();
        mc.setCursoIdCurso(c);
        mc.setMateriaIdMateria(materia);
        mc.setProfesor(profesor);
        mcRepository.save(mc);
        
        MateriaCursoMaterial mcm=new MateriaCursoMaterial();
        mcm.setMateriaCursoIdMateriaCurso(mc);
        mcm.setMaterialIdMaterial(material);
        mcmRepository.save(mcm);    
    }
    
    
    public void guardarImagen(MultipartFile file, Material material) throws IOException {
         material.setArchivo(file.getBytes()); // Convertir a byte[]
    }
    
    public List<SeleccionarMaterialView> seleccionarMaterial( Integer curso, Integer materia){
        Curso cursoIdCurso=cursoRepository.findById(curso).orElseThrow(()-> new CursoNotFound("No se encunetra el curso seleccionado"));
        Materia materiaIdMateria=materiaRepository.findById(materia).orElseThrow(()-> new MateriaNotFoundException("No existe la materia seleccionada"));
        List<SeleccionarMaterialView> buscarMaterial=materRepository.buscarMaterial(cursoIdCurso, materiaIdMateria);
        //List<String> descripcion=new ArrayList<>();
        return buscarMaterial;
    }//utilizar endpoint /selecMaterialProfesor
    
    public void borrarMaterial(Integer material){//MISMOS ENDPPOINTS QUE MATERIALDTO Y VER CONTROLADOR LINEA 484 MAS QUE NADA PARA VER QUE ES LO QUE SE DEBE MOSTRAR AL USUARIO APARTE DE LOS ENDPOOINTS DEL DTO
        
        Material materialIdMaterial=materRepository.findById(material).orElseThrow(()->
        new MaterialNotFoundException("No existe el material seleccionado"));
        
        /*Curso c=cursoRepository.findById(cursoIdCurso).orElseThrow(()->
        new CursoNotFound("No existe el curso seleccionado"));*/
        
       /* Materia materia=materiaRepository.findById(materiaIdMateria).orElseThrow(
        ()-> new MateriaNotFoundException("No existe la materia seleccionada"));*/
        
        /*MateriaCurso mc=new MateriaCurso();
        mc.setCursoIdCurso(c);
        mc.setMateriaIdMateria(materia);
        mc.setProfesor(null);*/
        materRepository.deleteById(material);
        mcmRepository.deleteByMaterialIdMaterial(materialIdMaterial);
    }
    
   
    
        
    
    
}

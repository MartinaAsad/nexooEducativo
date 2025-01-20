package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.MaterialDTO;
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
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    
    public void borrarMaterial( Material materialIdMaterial, Curso cursoIdCurso, Materia materiaIdMateria){//MISMOS ENDPPOINTS QUE MATERIALDTO
        MateriaCurso mc=new MateriaCurso();
        mc.setCursoIdCurso(cursoIdCurso);
        mc.setMateriaIdMateria(materiaIdMateria);
        mc.setProfesor(null);
        materRepository.deleteById(Integer.SIZE);
        mcmRepository.deleteByMaterialIdMaterial(materialIdMaterial);
    }
    
   
    
        
    
    
}

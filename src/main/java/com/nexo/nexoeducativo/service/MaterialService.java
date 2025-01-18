package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.MaterialDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.MateriaCursoMaterial;
import com.nexo.nexoeducativo.models.entities.Material;
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

/**
 *
 * @author Martina
 */
@Service
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
    
    private final String url = "subirArchivo/";
    
    public void altaMaterial(MaterialDTO m) throws IOException{
        
        Material material=new Material();
        String urlArchivo=saveUpload(m.getUrlArchivo());
        material.setArchivo(urlArchivo);
        material.setDescripcion(m.getDescripcion());
        materRepository.save(material);
        
        //entidades necesarias para asociar el material a un curso
        Curso c=cursoRepository.findById(m.getIdCurso())
                .orElseThrow(()-> new CursoNotFound("No existe el curso ingresado"));
        
        Materia materia = materiaRepository.findById(m.getIdMateria()).orElseThrow(
                ()->new MateriaNotFoundException("La materia ingresada no existe"));
        
        MateriaCurso mc=new MateriaCurso();
        mc.setCursoIdCurso(c);
        mc.setMateriaIdMateria(materia);
        mcRepository.save(mc);
        
        MateriaCursoMaterial mcm=new MateriaCursoMaterial();
        mcm.setMateriaCursoIdMateriaCurso(mc);
        mcm.setMaterialIdMaterial(material);
        mcmRepository.save(mcm);    
    }
    
     public String saveUpload(MultipartFile file) throws IOException {
     if (!file.isEmpty()){
         byte [] bytes = file.getBytes();
         // Codificar el nombre del archivo
         String encodedFileName = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
         Path path = Paths.get(url + encodedFileName);
         Files.write(path, bytes);
         return encodedFileName;
     }
     return null;
 }
    
        
    
    
}

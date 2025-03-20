
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.EditarMateriaCursoView;
import com.nexo.nexoeducativo.models.dto.request.SeleccionarProfesorView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class MateriaCursoService {

    @Autowired
    private MateriaCursoRepository mcRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PresentismoService presentismoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MateriaCursoService.class);

    public List<EditarMateriaCursoView> infoCompletaMaterias(Integer curso) {
        Curso c = cursoRepository.findById(curso).orElseThrow(
                () -> new CursoNotFound("No existe el curso"));

        List<EditarMateriaCursoView> infoCompletaMaterias = mcRepository.findDistinctByCursoIdCurso(c);
        return infoCompletaMaterias;
    }

    public Integer cantCursos(Integer profe) {
        Usuario u = usuarioRepository.findById(profe).orElseThrow(() -> new UsuarioNotFoundException("No existe el usuario"));
        Integer cant = mcRepository.cantCursosProfesor(u);

        return cant;
    }
    
    public Double cantHoras(Integer profe) {
        Usuario u = usuarioRepository.findById(profe).orElseThrow(() -> new UsuarioNotFoundException("No existe el usuario"));
        Double cant = mcRepository.cantMinutosProfesor(u);

        return cant;
    }
    
    public SeleccionarProfesorView infoProfesor (Integer profesor){
        SeleccionarProfesorView retorno=new SeleccionarProfesorView();
        retorno.setCantCursos(cantCursos(profesor));
        retorno.setCantHoras(cantHoras(profesor));
        retorno.setAsistencias(presentismoService.cantPresentismo(profesor));
        return retorno;
    }


}


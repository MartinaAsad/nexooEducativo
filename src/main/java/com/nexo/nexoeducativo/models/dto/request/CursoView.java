package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CursoView implements Serializable{
    private int numero;
    private Character division;
    private String nombreP;
    private String apellidoP;
    
    List<MateriaView> materias;
    List<UsuarioView> alumnos;    
}

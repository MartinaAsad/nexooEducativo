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
    private int numero;//numero del curso
    private Character division;
    private String nombreP;
    private String apellidoP;
    
    List<MateriaView> materias;
    List<UsuarioView> alumnos;    

    //lleno con solo datos del curso
    public CursoView(int numero, Character division, String nombreP,String apellidoP) {
        this.numero=numero;
        this.division=division;
        this.nombreP=nombreP;
        this.apellidoP=apellidoP;
    }
    
    
}

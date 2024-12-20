package com.nexo.nexoeducativo.models.dto.request;

import com.nexo.nexoeducativo.models.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CursoView implements Serializable{
    private int numero;//numero del curso
    private Character division;
    private String nombreP;
    private String apellidoP;
    
    List<MateriaView> materias;
   List<UsuarioView> alumnos;    

    //lleno con solo datos del curso

   /* public CursoView(int numero, Character division, String nombreP, String apellidoP, List<MateriaView> materias) {
        this.numero = numero;
        this.division = division;
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.materias = materias;
    }*/
    
    
}

package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@AllArgsConstructor
@ToString
@NoArgsConstructor
public class verCursoView implements Serializable{
     private int numero;//numero del curso
    private Character division;
    private short activo;
    private Integer idCurso;

    public verCursoView(int numero, Character division, short activo, Integer idCurso) {
        this.numero = numero;
        this.division = division;
        this.activo = activo;
        this.idCurso = idCurso;
    }
    
    
    
    public verCursoView (Integer idCurso){
        this.idCurso=idCurso;
    }
}

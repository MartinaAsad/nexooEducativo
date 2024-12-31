package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class verCursoView implements Serializable{
     private int numero;//numero del curso
    private Character division;
    private short activo;
}

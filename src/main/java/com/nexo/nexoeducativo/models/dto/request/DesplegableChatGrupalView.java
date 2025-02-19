
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DesplegableChatGrupalView extends UsuarioView implements Serializable{
     private int numero;//numero del curso
    private Character division;
    private short activo;
    private int idCurso;
    
    //constructor para cursos
  /*  public DesplegableChatGrupalView(int numero, Character division, short activo, int idCurso) {
        this.numero = numero;
        this.division = division;
        this.activo = activo;
        this.idCurso = idCurso;
    }*/
   

    
    
    
    
}

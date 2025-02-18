
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class MensajeIndividualDTO implements Serializable{
    @NotBlank(message = "no puede enviar un mensaje vacio")
    @Length(min=3, max=255)
    private String contenido;
    @Length(max=255)
    private String archivo;
    @Email(message="formato de email invalido")
    @NotBlank(message="campo comunicador vacio")
    private String comunicador;
     @Email(message="formato de email invalido")
    @NotBlank(message="campo destinatario vacio")
    private String destinatario;
    
    
    
}

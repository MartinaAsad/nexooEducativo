
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MensajeIndividualDTO implements Serializable{
    
    @NotBlank(message = "no puede enviar un mensaje vacio")
    @Length(min=2, max=255)
    private String contenido;
    
    @NotNull(message="campo destinatario vacio")
    private String destinatario;
    
    
    
}

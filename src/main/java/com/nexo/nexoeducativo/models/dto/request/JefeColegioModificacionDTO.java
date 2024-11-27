package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexo.nexoeducativo.models.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JefeColegioModificacionDTO implements Serializable{
    @JsonInclude(JsonInclude.Include.NON_NULL) //acepta valores nulos este campo
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @Length(min=3, max=30) //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String nombre;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "formato de apellido es invalido")
    @Length(min=4, max=30)
    private String apellido;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 6, max = 8, message = "El DNI debe tener entre 6 y 8 caracteres")
    private String dni;

    
    //@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Formato de email incorrecto")
     @JsonInclude(JsonInclude.Include.NON_NULL)
    @Email(message="formato de email invalido")
    private String mail;
    
     @JsonInclude(JsonInclude.Include.NON_NULL)
     @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$",
        message = "La clave debe tener entre 8 y 32 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un carácter especial."
    )
    private String clave;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0100000, message = "El telefono debe tener al menos 7 numeros")
    @Max(value = 999999999, message = "El telefono debe tener como maximo 9 numeros")
    private Integer telefono;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1")
    private short activo;
    
    @JsonCreator
     public JefeColegioModificacionDTO(
             @JsonProperty("nombre")String nombre,
             @JsonProperty("apellido") String apellido,
             @JsonProperty("dni") String dni, 
             @JsonProperty("mail") String mail,
             @JsonProperty("clave") String clave, 
             @JsonProperty("telefono") Integer telefono, 
             @JsonProperty("activo") short activo) {
         
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.clave = clave;
        this.telefono = telefono;
        this.activo = activo;
    }
    
    
    
     public JefeColegioModificacionDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.dni = String.valueOf(usuario.getDni());
        this.mail = usuario.getMail();
        this.telefono = usuario.getTelefono();
        this.activo = usuario.getActivo();
    }
        
}

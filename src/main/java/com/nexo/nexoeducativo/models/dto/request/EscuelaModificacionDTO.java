/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Plan;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
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
public class EscuelaModificacionDTO implements Serializable{
    @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "campo nombre invalido")//solo acepta letras
    @Length(min=4, max=60, message = "minimo 4 caracteres y maximo 60")
    private String nombre;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "campo direccion invalido")//solo acepta letras
    @Length(min=4, max=70, message = "minimo 4 caracteres y maximo 70")
    private String direccion;
     
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1") 
    private Short activo;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
     @Min(value = 1, message = "El valor debe ser 1 o 2")
    //@Max(value = 2, message = "El valor debe ser 1 o 2") 
    private Integer idPlan; //tipo de plan, verificacion hecha en service
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    private Integer jefeColegio;
    
    @JsonCreator
    public EscuelaModificacionDTO(
              @JsonProperty("nombre")String nombre,
              @JsonProperty("direccion")String direccion,
              @JsonProperty("activo") Short activo, 
              @JsonProperty("idPlan")Integer idPlan,
              @JsonProperty("jefeColegio")Integer jefeColegio) {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.activo = activo;
        this.idPlan = idPlan;
        this.jefeColegio = jefeColegio;
    }
    
    public EscuelaModificacionDTO(Escuela e){
        Plan p=new Plan();
         p.setIdPlan(this.idPlan); //obtener el nuevo plan
         
         Rol r=new Rol();
        r.setIdRol(2);// para asegurarnos que el id del jefe colegio se guarde como tal
        
        Usuario u=new Usuario(); //obtener el nuevo jefe colegio
        u.setIdUsuario(this.jefeColegio);
        u.setRolidrol(r);
        
        EscuelaUsuario eu= new EscuelaUsuario(); //para cambiar que jefe colegio esta asociado a que colegio
        eu.setEscuelaIdEscuela(e);
        eu.setUsuarioIdUsuario(u);
       
        this.idPlan=p.getIdPlan();
         this.nombre = e.getNombre();
        this.direccion = e.getDireccion();
        this.activo = e.getActivo();
        this.idPlan = p.getIdPlan();
        this.jefeColegio = u.getIdUsuario();
    }
    


}

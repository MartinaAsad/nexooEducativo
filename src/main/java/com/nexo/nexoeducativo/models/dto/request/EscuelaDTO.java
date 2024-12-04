/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


//@Data
@AllArgsConstructor
public class EscuelaDTO implements Serializable{
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "campo nombre invalido")//solo acepta letras
    @NotBlank(message="campo nombre no puede estar vacio")//notblank para string
    @Length(min=4, max=60, message = "minimo 4 caracteres y maximo 60")
    private String nombre;
    
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "campo direccion invalido")//solo acepta letras
    @NotBlank(message="campo direccion no puede estar vacio")//notblank para string
    @Length(min=4, max=70, message = "direccion debe tener minimo 4 caracteres y maximo 70")
    private String direccion;
     
    @NotNull(message="campo activo invalido")
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1") 
    private short activo;
    
    @NotNull(message="campo plan invalido")
    private int idPlan; //tipo de plan, verificacion hecha en service
    
    @NotNull(message="campo jefe colegio invalido")
    private int jefeColegio;//verificacion hecha en service

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getJefeColegio() {
        return jefeColegio;
    }

    public void setJefeColegio(int jefeColegio) {
        this.jefeColegio = jefeColegio;
    }
    
    
    
    
}

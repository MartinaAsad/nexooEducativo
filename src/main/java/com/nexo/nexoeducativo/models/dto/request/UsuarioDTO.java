/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;



import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;


/**
 *
 * @author Martina
 */
//@Data

public class UsuarioDTO implements Serializable{
    //atributos del usuario que van a ser recibidos desde afuera del sistema, sale desde el request
    //poner aca atributos comunes entre todos los tipos de usuario
    //utilizar la herencia para implementar en los otros roles
    
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @NotBlank(message="campo nombre invalido")//notblank para string
    @Length(min=3, max=30) //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String nombre;
    
    @NotBlank(message="apellido no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "formato de apellido es invalido")
    @Length(min=4, max=30)
    private String apellido;
    
    @NotNull(message="campo dni invalido")
    @Min(value = 100000, message = "El DNI debe tener al menos 6 numeros")
    @Max(value = 99999999, message = "El DNI debe tener como maximo 8 numeros")
    private int dni;
    
    //@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Formato de email incorrecto")
    @Email(message="formato de email invalido")
    @NotBlank(message="campo email vacio")
    //@NotBlank(message = "campo email vacio")
    private String eMail;
    
    @NotNull(message="campo telefono invalido")
    @Min(value = 0100000, message = "El telefono debe tener al menos 7 numeros")
    @Max(value = 999999999, message = "El telefono debe tener como maximo 9 numeros")
    private Integer telefono;
    
    @NotNull(message="campo activo invalido")
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1")
    private short activo;
    
    @NotNull(message="campo telefono invalido")
    @Min(value = 0, message = "Rol inexistente")
    @Max(value = 6, message = "Rol inexistente")
    private Integer rol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public UsuarioDTO(String nombre, String apellido, int dni, String eMail, Integer telefono, short activo, Integer rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.eMail = eMail;
        this.telefono = telefono;
        this.activo = activo;
        this.rol = rol;
    }
    
    

    
    
    
    
}
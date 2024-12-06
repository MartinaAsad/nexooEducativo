/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignarPreceptorDTO {
    
    @NotNull
    private int preceptor;
    
    @NotNull
    private int curso;
    
}

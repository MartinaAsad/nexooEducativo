/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.exception;

/**
 *
 * @author Martina
 */
public class CursoNotFound extends RuntimeException{
    public CursoNotFound(String message){
        super(message);
    }
}

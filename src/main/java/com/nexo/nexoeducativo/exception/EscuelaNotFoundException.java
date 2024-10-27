/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.exception;

/**
 *
 * @author Martina
 */
public class EscuelaNotFoundException extends RuntimeException{
    public EscuelaNotFoundException(String message){
        super(message);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.handler;

import com.nexo.nexoeducativo.exception.CalificacionWrongException;
import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaExistingException;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.HoraInvalidatedexception;
import com.nexo.nexoeducativo.exception.MateriaExistingException;
import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioExistingException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioWithPadreException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Martina
 */
@ControllerAdvice
public class GlobalExceptionHandler {
   
    @ExceptionHandler(UsuarioExistingException.class)
    public ResponseEntity<String> handleUserExistingException(UsuarioExistingException ue){
        return new ResponseEntity<>(ue.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String>handleUserNotFoundException(UsuarioNotFoundException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.NOT_FOUND);
    }
    
     @ExceptionHandler(RolNotFound.class)
    public ResponseEntity<String>handleRolNotFound(RolNotFound uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.NOT_FOUND);
    }
    
     @ExceptionHandler(CursoNotFound.class)
    public ResponseEntity<String>handleCursoNotFound(CursoNotFound uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UsuarioAssignedException.class)
    public ResponseEntity<String>handleUsuarioAssignedException(UsuarioAssignedException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsuarioNotAuthorizedException.class)
    public ResponseEntity<String>handleUsuarioNotAuthorizedException(UsuarioNotAuthorizedException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsuarioWithPadreException.class)
    public ResponseEntity<String>handleUsuarioWithPadreException(UsuarioWithPadreException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
     @ExceptionHandler(EscuelaNotFoundException.class)
    public ResponseEntity<String>handleEscuelaNotFoundException(EscuelaNotFoundException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.NOT_FOUND);
    }
     @ExceptionHandler(MateriaExistingException.class)
    public ResponseEntity<String>handleMateriaExistingException(MateriaExistingException uf){
         return new ResponseEntity<>(uf.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EscuelaExistingException.class)
    public ResponseEntity<String>handleEscuelaExistingException(EscuelaExistingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
     @ExceptionHandler(HoraInvalidatedexception.class)
    public ResponseEntity<String>handleHoraInvalidatedexception(HoraInvalidatedexception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CalificacionWrongException.class)
     public ResponseEntity<String>handleCalificacionWrongExceptionException(CalificacionWrongException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
   
}

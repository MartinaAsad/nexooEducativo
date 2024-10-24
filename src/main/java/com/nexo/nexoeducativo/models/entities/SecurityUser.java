/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.entities;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Martina
 */
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private Usuario user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRolidrol().getNombre()));
    }

    @Override
    public String getPassword() {
        return user.getClave();
    }

    @Override
    public String getUsername() {
        return user.getMail();
    }
    
    @Override
      public boolean isEnabled() {
          boolean activo=false;
          if(user.getActivo()==1){
              activo=true;
          }
        return activo; // Usa tu método para verificar si el usuario está habilitado
    }
}


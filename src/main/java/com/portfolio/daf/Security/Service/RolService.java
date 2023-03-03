/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Security.Service;


import com.portfolio.daf.Security.Entity.Rol;
import com.portfolio.daf.Security.Enuns.RolNombre;
import com.portfolio.daf.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pablo
 */
@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository irolRepository; 
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
        
    }
   public void save(Rol rol){
      irolRepository.save(rol);
    } 
    
}

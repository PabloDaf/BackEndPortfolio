/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Service;

import com.portfolio.daf.Entity.Habilidad;
import com.portfolio.daf.Repository.RHabilidad;
import java.util.List;
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

public class SHabilidad {
    @Autowired
    RHabilidad rhabilidad;
            
    public List<Habilidad> list(){
        return rhabilidad.findAll();
    }
    
    public Optional<Habilidad> getOne(int id){
        return rhabilidad.findById(id);
    }
    
    public Optional<Habilidad> getByNombre(String nombre){
        return rhabilidad.findByNombre(nombre);
    }
    
    public void save(Habilidad habil){
        rhabilidad.save(habil);
    }
    
    public void delete(int id){
        rhabilidad.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhabilidad.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhabilidad.existsByNombre(nombre);
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Service;

import com.portfolio.daf.Entity.Proyecto;
import com.portfolio.daf.Repository.RProyecto;
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
public class SProyecto {
   @Autowired
    RProyecto rProyecto;
    
    public List<Proyecto> list(){
        return rProyecto.findAll();    
    }
    public Optional<Proyecto> getOne(int id){
        return rProyecto.findById(id);
    }
    public Optional<Proyecto> getByNombreP(String nombreP){
        return rProyecto.findByNombreP(nombreP);
    }
    public void save(Proyecto proyecto){
        rProyecto.save(proyecto);
    }
    public void delete(int id){
        rProyecto.deleteById(id);
    }
    public boolean existsById(int id){
        return rProyecto.existsById(id);
    }
    public boolean existsByNombreP(String nombreP){
        return rProyecto.existsByNombreP(nombreP);
    } 
}

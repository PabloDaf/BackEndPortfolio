/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Controller;

import com.portfolio.daf.Dto.dtoHabilidad;
import com.portfolio.daf.Entity.Habilidad;
import com.portfolio.daf.Security.Controller.Mensaje;
import com.portfolio.daf.Service.SHabilidad;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pablo
 */
@RestController
@RequestMapping("/habilidad")
@CrossOrigin(origins = "http://localhost:4200")
public class CHabilidad {
    @Autowired
    SHabilidad shabilidad;
    
     // traemos la lista de habilidad

   @GetMapping("/lista")

   public ResponseEntity<List<Habilidad>> list(){
       List<Habilidad> list = shabilidad.list();
     return new ResponseEntity(list, HttpStatus.OK);  
   }
   
//creamos una habilidad
   
   @PostMapping("/create")
   
   public ResponseEntity<?> create(@RequestBody dtoHabilidad dtohabil){
       if(StringUtils.isBlank(dtohabil.getNombre()))
           return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(shabilidad.existsByNombre(dtohabil.getNombre()))
            return new ResponseEntity(new Mensaje("Esa habilidad Existe"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = new Habilidad(dtohabil.getNombre(), dtohabil.getPorcentaje());
        shabilidad.save(habilidad);
        
        return new ResponseEntity(new Mensaje("La habilidad fue agregada con exito"), HttpStatus.OK);
   }
  
   //actualizamos una habilidad
    
    @PutMapping("/update/{id}")
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidad dtohabil){
        
    // Se hace la validacion si el ID ya existe
        if(!shabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
    // se hace la validacion si el nombre de la habilidad ya existe
        if(shabilidad.existsByNombre(dtohabil.getNombre()) && shabilidad.getByNombre(dtohabil.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST );
       
    // se hace la validacion para que el campo no este vacio
        if(StringUtils.isBlank(dtohabil.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
        Habilidad habilidad = shabilidad.getOne(id).get();
        habilidad.setNombre(dtohabil.getNombre());
        habilidad.setPorcentaje(dtohabil.getPorcentaje());
        
        shabilidad.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad actualizada con exito"), HttpStatus.OK);
    }
    // borramos una habilidad
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        // Se hace la validacion si el ID ya existe
        if(!shabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        shabilidad.delete(id);
        return new ResponseEntity(new Mensaje(" La habilidad fue eliminada con exito"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id){
        if(!shabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.NOT_FOUND);
        Habilidad habilidad = shabilidad.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }
 
}
   


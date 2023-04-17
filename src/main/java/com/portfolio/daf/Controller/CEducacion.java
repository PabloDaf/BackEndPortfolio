/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Controller;

import com.portfolio.daf.Dto.dtoEducacion;
import com.portfolio.daf.Entity.Educacion;;
import com.portfolio.daf.Security.Controller.Mensaje;
import com.portfolio.daf.Service.SEducacion;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://daffrontend.web.app")
/*@CrossOrigin(origins = "http://localhost:4200")*/

public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }  
    
    //creamos una experiencia
   
   @PostMapping("/create")
   
   public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu){
       if(StringUtils.isBlank(dtoedu.getNombreE())){
           return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       }
           if(sEducacion.existsByNombreE(dtoedu.getNombreE())){
            return new ResponseEntity(new Mensaje("Esa educacion Existe"), HttpStatus.BAD_REQUEST);
           }
        Educacion educacion = new Educacion(dtoedu.getNombreE(), dtoedu.getDescripcionE());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("La educacion fue agregada con exito"), HttpStatus.OK);
   }
  
   //actualizamos una experiencia
    
    @PutMapping("/update/{id}")
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu){
        
    // Se hace la validacion si el ID ya existe
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
    // se hace la validacion si el nombre de la experiencia ya existe
        if(sEducacion.existsByNombreE(dtoedu.getNombreE()) && sEducacion.getByNombreE(dtoedu.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Esa Educacion ya existe"), HttpStatus.BAD_REQUEST );
       
    // se hace la validacion para que el campo no este vacio
        if(StringUtils.isBlank(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    // Actualizamos el objeto
        Educacion educacion = sEducacion.getOne(id).get();

    //Actualizamos los parametros
        educacion.setNombreE(dtoedu.getNombreE());
        educacion.setDescripcionE(dtoedu.getDescripcionE());
    // guardamos la variable educacion    
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada con exito"), HttpStatus.OK);
    }
    // borramos una experiencia
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    // Se hace la validacion si el ID ya existe
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje(" La educacion fue eliminada con exito"), HttpStatus.OK);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        //validamos si existe el id
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }
        //En caso de existir que haga lo siguiente
        Educacion educacion = sEducacion.getOne(id).get();
        // y cuando lo encuantra que realice lo siguiente
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
 
}

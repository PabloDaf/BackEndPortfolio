/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Controller;

import com.portfolio.daf.Dto.dtoExperiencia;
import com.portfolio.daf.Entity.Experiencia;
import com.portfolio.daf.Security.Controller.Mensaje;
import com.portfolio.daf.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pablo
 */
@Controller
@RequestMapping("/explab")
@CrossOrigin(origins = "https://daffrontend.web.app")
/*@CrossOrigin(origins = "http://localhost:4200")*/

public class CExperiencia {
   @Autowired
   SExperiencia sExperiencia;
   
    // traemos la lista de experiencias

   @GetMapping("/lista")

   public ResponseEntity<List<Experiencia>> list(){
       List<Experiencia> list = sExperiencia.list();
     return new ResponseEntity(list, HttpStatus.OK);  
   }
   
//creamos una experiencia
   
   @PostMapping("/create")
   
   public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
       if(StringUtils.isBlank(dtoexp.getNombreE()))
           return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsbyNombreE(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia Existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("La experiencia fue agregada con exito"), HttpStatus.OK);
   }
  
   //actualizamos una experiencia
    
    @PutMapping("/update/{id}")
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
        
    // Se hace la validacion si el ID ya existe
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
    // se hace la validacion si el nombre de la experiencia ya existe
        if(sExperiencia.existsbyNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Esa Experiencia ya existe"), HttpStatus.BAD_REQUEST );
       
    // se hace la validacion para que el campo no este vacio
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE(dtoexp.getDescripcionE());
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada con exito"), HttpStatus.OK);
    }
    // borramos una experiencia
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        // Se hace la validacion si el ID ya existe
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        return new ResponseEntity(new Mensaje(" La experiencia fuen eliminada con exito"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
 
}
   


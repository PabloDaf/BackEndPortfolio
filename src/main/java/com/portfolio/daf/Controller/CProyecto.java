/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Controller;

import com.portfolio.daf.Dto.dtoProyecto;
import com.portfolio.daf.Entity.Proyecto;
import com.portfolio.daf.Security.Controller.Mensaje;
import com.portfolio.daf.Service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://daffrontend.web.app")
/*@CrossOrigin(origins = "http://localhost:4200")*/
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }  
    
    //creamos un Proyecto Realizado
   
   @PostMapping("/create")
   
   public ResponseEntity<?> create(@RequestBody dtoProyecto dtopro){
       if(StringUtils.isBlank(dtopro.getNombreP())){
           return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       }
           if(sProyecto.existsByNombreP(dtopro.getNombreP())){
            return new ResponseEntity(new Mensaje("Ese Proyecto Existe"), HttpStatus.BAD_REQUEST);
           }
        Proyecto proyecto = new Proyecto(dtopro.getNombreP(), dtopro.getFechaP(), dtopro.getDescripcionP(), dtopro.getImgP());
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("La educacion fue agregada con exito"), HttpStatus.OK);
   }
  
   //actualizamos una experiencia
    
    @PutMapping("/update/{id}")
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtopro){
        
    // Se hace la validacion si el ID ya existe
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
    // se hace la validacion si el nombre de la experiencia ya existe
        if(sProyecto.existsByNombreP(dtopro.getNombreP()) && sProyecto.getByNombreP(dtopro.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST );
       
    // se hace la validacion para que el campo no este vacio
        if(StringUtils.isBlank(dtopro.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    // Actualizamos el objeto
        Proyecto proyecto = sProyecto.getOne(id).get();

    //Actualizamos los parametros
        proyecto.setNombreP(dtopro.getNombreP());
        proyecto.setFechaP(dtopro.getFechaP());
        proyecto.setDescripcionP(dtopro.getDescripcionP());
        proyecto.setImgP(dtopro.getImgP());
        
    // guardamos la variable educacion    
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado con exito"), HttpStatus.OK);
    }
    // borramos un proyecto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    // Se hace la validacion si el ID ya existe
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje(" El proyecto fue eliminado con exito"), HttpStatus.OK);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        //validamos si existe el id
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }
        //En caso de existir que haga lo siguiente
        Proyecto proyecto = sProyecto.getOne(id).get();
        // y cuando lo encuantra que realice lo siguiente
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
 
    
}

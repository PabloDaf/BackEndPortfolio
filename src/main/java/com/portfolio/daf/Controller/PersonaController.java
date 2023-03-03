/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Controller;


import com.portfolio.daf.Dto.dtoPersona;
import com.portfolio.daf.Entity.Persona;
import com.portfolio.daf.Security.Controller.Mensaje;
import com.portfolio.daf.Service.ImplPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pablo
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/personas")
public class PersonaController {
   @Autowired
    ImplPersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }  
    
    //creamos una persona
   /*
   @PostMapping("/create")
   
   public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
       if(StringUtils.isBlank(dtoper.getNombre())){
           return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       }
           if(personaService.existsbyNombre(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Esa persona Existe"), HttpStatus.BAD_REQUEST);
           }
        Persona persona = new Persona(dtopersona.getNombre(), dtopersona.getApellido(),dtopersona.getDescripcion(),dtoperersona.getImg());
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje("La educacion fue agregada con exito"), HttpStatus.OK);
   }
  */
   //actualizamos una Persona
    
    @PutMapping("/update/{id}")
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        
    // Se hace la validacion si el ID ya existe
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
    // se hace la validacion si el nombre de la persona ya existe
        if(personaService.existsbyNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Esa Persona ya existe"), HttpStatus.BAD_REQUEST );
       
    // se hace la validacion para que el campo no este vacio
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    // Actualizamos el objeto
        Persona persona = personaService.getOne(id).get();

    //Actualizamos los parametros
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        
    // guardamos la variable persona   
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada con exito"), HttpStatus.OK);
    }
    // borramos una persona
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    // Se hace la validacion si el ID ya existe
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        
        personaService.delete(id);
        return new ResponseEntity(new Mensaje(" La persona fue eliminada con exito"), HttpStatus.OK);
    
    }
    */
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        //validamos si existe el id
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.BAD_REQUEST);
        }
        //En caso de existir que haga lo siguiente
        Persona persona = personaService.getOne(id).get();
        // y cuando lo encuantra que realice lo siguiente
        return new ResponseEntity(persona, HttpStatus.OK);
    }
}

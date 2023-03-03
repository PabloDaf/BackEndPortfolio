/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.daf.Repository;

import com.portfolio.daf.Entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pablo
 */
public interface RHabilidad extends JpaRepository<Habilidad, Integer>{
        Optional<Habilidad> findByNombre(String nombre);
        public boolean existsByNombre(String nombre);
    
}

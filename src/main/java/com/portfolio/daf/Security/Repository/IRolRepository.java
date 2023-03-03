/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.daf.Security.Repository;

import com.portfolio.daf.Security.Entity.Rol;
import com.portfolio.daf.Security.Enuns.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pablo
 */
@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
   Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

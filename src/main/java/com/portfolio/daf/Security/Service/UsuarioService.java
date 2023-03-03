/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Security.Service;

import com.portfolio.daf.Security.Entity.Usuario;
import com.portfolio.daf.Security.Repository.IUsuarioRepository;
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
public class UsuarioService {
    @Autowired
    IUsuarioRepository iusuarioRepository;
  public Optional<Usuario> getByNombreUsuario(String NombreUsuario){
     return iusuarioRepository.findByNombreUsuario(NombreUsuario);
  }  
  public Boolean existByNombreUsuario(String nombreUsuario){
   return iusuarioRepository.existsByNombreUsuario(nombreUsuario);   
  }
   public Boolean existByEmail(String email){
   return iusuarioRepository.existsByEmail(email);   
  }
   public void save(Usuario usuario){
       iusuarioRepository.save(usuario);
   }
}

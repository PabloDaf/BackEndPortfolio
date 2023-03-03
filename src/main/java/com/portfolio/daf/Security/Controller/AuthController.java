/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Security.Controller;

import com.portfolio.daf.Security.Dto.JwtDto;
import com.portfolio.daf.Security.Dto.LoginUsuario;
import com.portfolio.daf.Security.Dto.NuevoUsuario;
import com.portfolio.daf.Security.Entity.Rol;
import com.portfolio.daf.Security.Entity.Usuario;
import com.portfolio.daf.Security.Enuns.RolNombre;
import com.portfolio.daf.Security.Service.RolService;
import com.portfolio.daf.Security.Service.UsuarioService;
import com.portfolio.daf.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pablo
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    /**
     *
     * @param nuevoUsuario
     * @param bindingResult
     * @return
     */
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
       if(bindingResult.hasErrors())
           return new ResponseEntity(new Mensaje("Campos Mal colocados o email invalido"),HttpStatus.BAD_REQUEST);
    
       if (usuarioService.existByNombreUsuario(nuevoUsuario.getNombreUsuario()))
           return new ResponseEntity(new Mensaje("Nombre de usuario ya existente"), HttpStatus.BAD_REQUEST);
       if (usuarioService.existByEmail(nuevoUsuario.getEmail()))
           return new ResponseEntity(new Mensaje("Email ya existente"), HttpStatus.BAD_REQUEST);
       
       Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
    
       Set<Rol>roles = new HashSet<>();
       roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
       
       if(nuevoUsuario.getRoles().contains("admin"))
           roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
       
       usuario.setRoles(roles);
       usuarioService.save(usuario);
       return new ResponseEntity(new Mensaje("El usuario fue Guardado exitosamente"), HttpStatus.CREATED);
          
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto>login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal colocados"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}

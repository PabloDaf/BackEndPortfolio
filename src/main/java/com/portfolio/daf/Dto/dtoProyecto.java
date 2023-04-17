/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.daf.Dto;

import java.text.SimpleDateFormat;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author pablo
 */
public class dtoProyecto {
   @NotBlank
   private String nombreP;
   @NotBlank
   private String fechaP;
   @NotBlank
   private String descripcionP;
   @NotBlank
   private String imgP;

   
   
   
    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String fechaP, String descripcionP, String imgP) {
        this.nombreP = nombreP;
        this.fechaP = fechaP;
        this.descripcionP = descripcionP;
        this.imgP = imgP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getFechaP(){
    return fechaP;
    }

    public void setFechaP(String fechaP) {
        this.fechaP = fechaP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }
   
   
 
}

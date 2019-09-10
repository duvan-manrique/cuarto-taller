/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.antecedentes;

import java.util.Date;

/**
 * Clase encapsulador
 * @author duvan
 * @author ivan camilo
 */
public class Antecedentes {
    private Date fecha;
    private String descrip;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public TipoAnte getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnte tipo) {
        this.tipo = tipo;
    }
    private TipoAnte tipo;
}

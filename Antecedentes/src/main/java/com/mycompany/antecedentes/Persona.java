/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.antecedentes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author duvan
 */
public class Persona implements Serializable{
    private String nombre;

    public Persona(String nombre, long cedula, byte edad, String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
        this.lista = new ArrayList();
    }
    private long cedula;
    private byte edad;
    private String genero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ArrayList<Antecedentes> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Antecedentes> lista) {
        this.lista = lista;
    }
    private ArrayList <Antecedentes> lista ; 
}

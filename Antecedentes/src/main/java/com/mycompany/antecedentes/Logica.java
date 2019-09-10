/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.antecedentes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author duvan8
 */
public class Logica {
      ArrayList<Persona> listaPersona;
    public Logica() {
        listaPersona = new ArrayList<>();
        ingresar();
    }
     
    List<TipoAnte> listaA;
    Scanner ingreso;
    public void ingresar(){
           listaPersona =recoverPersonas();
           if(listaPersona==null){
               listaPersona=new ArrayList(); 
           }
            
         
        Scanner ingreso = new Scanner(System.in);
         String continuar="s";     
        
         do{
            System.out.println("Antecedentes");
            System.out.println("a. Insertar persona");
            System.out.println("b. Insertar antecedente");
            System.out.println("c. Editar una persona");
            System.out.println("d. Eliminar antecendetes");
            System.out.println("e. Visualizar antecedentes");
            validarMetodo(ingreso.next().charAt(0));
            System.out.println("Desea Continuar S/N");        
            continuar=ingreso.next();
         }while("s".equals(continuar.toLowerCase()));
    
         
    }
     private void validarMetodo(char opcion) {
        switch (opcion){
            case 'a':
                insertarPersona();
                break;
            case 'b':
                insertarAntecedentes();
                break;
            case 'c':
                 editarPersona();
                break;
            case 'd':
                eliminarAntecedentesN();
                break;
            case 'e':
                visualizarAntecedentes();
                break;
            default: 
                System.out.println("error de opcion");
               
        }
        insertArchivo(listaPersona);
        
    }
     private void insertarPersona() { 
        ingreso=new Scanner(System.in);
        System.out.println("Digite su nombre");
        String nombre=ingreso.nextLine();
        System.out.println("Digite su identificacion");
        int cedula=ingreso.nextInt();
        System.out.println("Digite su edad");
        byte edad=ingreso.nextByte();
        System.out.println("Digite su genero ");
        String genero=ingreso.next();
        
        Persona persona=new Persona(nombre, cedula, edad, genero);
        listaPersona.add(persona);
        
       
        System.out.println("Registro Exitoso ");
    }
     
       private void insertarAntecedentes() {
        int contador=0;
        ingreso=new Scanner(System.in);
        System.out.println("Ingrese el numero identificacion");
        long cedula=ingreso.nextLong();
        for (Persona persona : listaPersona) {
            if(persona.getCedula()==cedula){
               contador ++;
               do{
                 System.out.println("Ingrese descripcion: ");
                 Antecedentes nuevo = new Antecedentes();  
                 String descri=ingreso.next();
                 nuevo.setDescrip(descri);
                 System.out.println("tipo de antecedente(-/+): ");
                 String resul =ingreso.next();
                 if(resul.charAt(0)=='+'){
                     nuevo.setTipo(ingresarTipo(1));
                     
                 }else{
                     nuevo.setTipo(ingresarTipo(-1));
                 }
                 persona.getLista().add(nuevo);
                 System.out.println("desea ingresar otro antesedente(s/n) ");
               }while('s'==ingreso.next().charAt(0));
               
            }
        }
        if(contador<=0)
            System.out.println("La persona no esta registrada");  
    }
       private TipoAnte ingresarTipo (int i){
            TipoAnte nuevot = new TipoAnte();
            System.out.println("Ingrese descripcion de antesedente: ");
            nuevot.setDescri(ingreso.next());
            System.out.println("Ingrese nombre de antesedente: ");
            nuevot.setNombre(ingreso.next());
            nuevot.setTipo(i);
            return nuevot;
       }
       private void editarPersona(){
            ingreso=new Scanner(System.in);
            System.out.println("Ingrese el numero identificacion");
            long cedula=ingreso.nextLong();
            for (Persona persona : listaPersona) {
                if(persona.getCedula()==cedula){
                    editarp(persona);
                }

           }
     
}
    private void editarp(Persona persona){ 
            ingreso=new Scanner(System.in);
            System.out.println("Digite su nombre");
            persona.setNombre(ingreso.next());
            System.out.println("Digite su identificacion");
            persona.setCedula(ingreso.nextLong());
            System.out.println("Digite su edad");
            persona.setEdad(ingreso.nextByte());
            System.out.println("Digite su genero ");
            persona.setGenero(ingreso.next());
            System.out.println("editor Exitoso ");
        }

    private void eliminarAntecedentesN(){
         ingreso=new Scanner(System.in);
        System.out.println("Ingrese el numero identificacion");
        long cedula=ingreso.nextLong();
        for (Persona persona : listaPersona) {
            if(persona.getCedula()==cedula)
            {
                System.out.println(persona.getLista().size());
                for(int i=0 ;i<persona.getLista().size();i++){
                    if(persona.getLista().get(i).getTipo().getTipo()==(-1)){
                        persona.getLista().remove(i);
                    }
                }           
            }
        
            }
    }
    private void visualizarAntecedentes(){
         ingreso=new Scanner(System.in);
        System.out.println("Ingrese el numero identificacion");
        long cedula=ingreso.nextLong();
        for (Persona persona : listaPersona) {
            if(persona.getCedula()==cedula)
            {
                 for(Antecedentes ante : persona.getLista()){
                     System.out.println("Persona"+persona.getNombre());   
                     System.out.println("Antecedente ");   
                     System.out.println("Descripcion"+ante.getDescrip());   
                     System.out.println("Descripcion tipo"+ante.getTipo().getTipo());      
                }
                 
            }
        
            }
    }
    
 public ArrayList<Persona> recoverPersonas(){
            ArrayList<Persona> listaPersonas=null;
            try{
                FileInputStream archivo=new FileInputStream("carpeta/lista_persona");
                ObjectInputStream objeto=new ObjectInputStream(archivo);
                listaPersonas=(ArrayList) objeto.readObject();
                objeto.close();
                archivo.close();
            }catch (IOException | ClassNotFoundException io){
            }
           return listaPersonas;          

        }
 
     public void insertArchivo(ArrayList<Persona> persona){
        try{
            File arch=new File("carpeta/lista_persona");
            if (!arch.exists()) 
		arch.createNewFile();
            FileOutputStream archivo=new FileOutputStream(arch);
            ObjectOutputStream objeto=new ObjectOutputStream(archivo);
            objeto.writeObject(persona);
            objeto.close();
            archivo.close();
        }catch (Exception io){
            System.err.println(io);
            System.out.println(io);
        }
    }
       

}
     
     



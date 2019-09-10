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
 * Clase encargada de toda las operaciones logicas y peticion de datos
 * @author duvan
 * @author ivan camilo
 */
public class Logica {
    /**
     * Variable que se usa en todos los metodos para guardar los datos de persona en una lista
     */
      ArrayList<Persona> listaPersona;
      /**
     * Constructor de la clase
     */
    public Logica() {
        listaPersona = new ArrayList<>();
        ingresar();
    }
    /**
     * Variable que se usa en todos los metodos para guardar los datos del tipo de antecedente
     * 
     */
     
    List<TipoAnte> listaA;
    /**
     * Variable que se usa en recibir datos por teclado
     */
    Scanner ingreso;
    /**
     * Funcion en la cual pedimos los una letra para saber que desea hacer el usuarop
     *  
     */
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
            OpcionUsuario(ingreso.next().charAt(0));
            System.out.println("Desea Continuar S/N");        
            continuar=ingreso.next();
         }while("s".equals(continuar.toLowerCase()));
    
         
    }
     /**
     * Funcion en la cual se resive el parametro que da el usuario para llamar dicha accion elegida por el mismo   * 
     * @param opcion 
     *  
     */
     private void OpcionUsuario(char opcion) {
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
      /**
     * Funcion en solicitamos los datos de la persona para registrarla     
     *  
     */
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
      /**
     * Funcion en la cual solicitamso los datos de el antecedente para crear este mismo   * 
    
     *  
     */
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
        /**
     * Funcion en la cual registramos el tipo de amtecedemte para saber luego si se puede borrar o no
     *  
     */
       private TipoAnte ingresarTipo (int i){
            TipoAnte nuevot = new TipoAnte();
            System.out.println("Ingrese descripcion de antesedente: ");
            nuevot.setDescri(ingreso.next());
            System.out.println("Ingrese nombre de antesedente: ");
            nuevot.setNombre(ingreso.next());
            nuevot.setTipo(i);
            return nuevot;
       }
        /**
     * Funcion en cual se trae una persona para editar o modificar sus campos   * 
     * 
     *  
     */
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
        /**
     * Funcion en cual se trae una persona para editar o modificar sus campos   * 
     * 
     *  
     */
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
     /**
     * Funcion en cual se eliminan los antecedentes de el usuario elegido   * 
     * 
     *  
     */

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
     /**
     * Funcion en cual se listamos los antecedentes por persona   * 
     * 
     *  
     */
    private void visualizarAntecedentes(){
         ingreso=new Scanner(System.in);
        System.out.println("Ingrese el numero identificacion");
        long cedula=ingreso.nextLong();
        for (Persona persona : listaPersona) {
            if(persona.getCedula()==cedula)
            {
                 for(Antecedentes ante : persona.getLista()){
                     System.out.println("Persona : "+persona.getNombre());   
                      System.out.println("Cedula : "+persona.getCedula());
                     System.out.println("Antecedente ");   
                     System.out.println("Descripcion: "+ante.getDescrip());
                     System.out.println("Descripcion tipo: "+ante.getTipo().getDescri()); 
                     if(ante.getTipo().getTipo()==1){
                         System.out.println("Tipo de antecedente ´Positivo");      
                     }else{
                         System.out.println("Tipo de antecedente ´Negrativo");
                     }
                     
                }
                 
            }
        
            }
    }

     /**
     * Funcion en cual traemos las personas de el archivo
     * 
     *  
     */
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
  /**
     * Funcion en cual guardamos las personas en el archivo
     * 
     *  
     */
 
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
     
     



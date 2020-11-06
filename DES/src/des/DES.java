/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

/**
 *
 * @author demon
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//usar una libreria que se encarga de los metodos de cifrado y de la generacion de claves de seguridad
import javax.crypto.*;

import javax.crypto.interfaces.*;
//esta libreria sirve para la creacion de claves a partir de una interfaz

import javax.crypto.spec.*;
//libreria que nos ayuda a generar las sub llaves del algortimo seleccionado

import java.security.*;
//libreria que nos ayuda a definir el tipo de algoritmo de seguridad que se ocupa sea simetrico o asimetrico


public class DES {

    /**
     * @param args the command line arguments
     * 
     * Vamos a crear un cifrador descifrador de archivos a traves del uso del algortimo DES
     * 
     * 
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
        // TODO code application logic here
        
        
        //comprobar que se carge los argumentos el archivo
        if(args.length != 1){
            mensajeAyuda();
            System.exit(1);
        }
        
        
        
        /*
        
        Para poder utilizar el cifrado simetrico DES, debemos de cargar el tipo de cifrado
        a traves del uso de "provider" 
        
        Paso 1.- crear e inicilizar la clave o llave privada
        
        */
        
        System.out.println("1.- Generar Clave DES: ");
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        //la generacion de la llave se debe de hacer a partir de algoritmo DES la cual es una funcion 
        //que devuelve numeros pseudoaleatorios a una cadena especifica de datos y "la cifra"
        System.out.println("uwu");
        //el tamaño de la llave
        generadorDES.init(56);  //la llave debe de ser de 56 bits
        
        //vamos a instancear la clave para que se genere
        
        SecretKey clave = generadorDES.generateKey();
        
        System.out.println("la clave es: "+clave);
        
        //como todo esta en bites o bytes dependiendo tenemos que crear un metodo para poderlos ver
        mostrarBytes(clave.getEncoded());
        
        System.out.println();
        
        //Paso 2: crear el cifrador
        
        /*
        El tipo de cifrado que se va a crear: simetrico DES
        Decir el modo de ciframiento:  FLUJO Y BLOQUES:  por ECB (Electornic Code Book)
        Si va o no a ocupar relleno (padding), nos sirve para una vez que se formen los bloques
        de 64 bits para el cifrado DES, debemos entender que algunos bloques no van a quedar completamente
        cubiertos, es por eso que se necesita rellenar
        
        PKCS5Padding
        
        */
        
        Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        //tenemos que crear el menu para la carga del archivo a cifrar
        
        System.out.println("2.- Cifrar con DES el fichero : "+ args[0] + ", dejar el resultado en: "
        + args[0] + " cifrado");
        
        //tenemos que cargar un archivo de texto el cual lo va  acifrar con des y una vez cifrado va a 
        //generar un archivo que lo va a guardar dentro del proyecto
        
        //la parte mas complicada el cifrado por pasos
        
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        
        //wiii que cansado ufff
        
        //entonces tenemos que transformar y leer el fichero en bytes
        
        byte[] buffer = new byte[1000];
        
        byte[] bufferCifrado;
        
        
        //vamos a generar el archivo
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0]+" .cifrado");
        
        //tenemos que empezar por la lectura del archivo y converlo en bytes
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            bufferCifrado  = cifrador.update(buffer, 0, bytesleidos);
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }
        
        //cuando termine de leer el archivo
        bufferCifrado = cifrador.doFinal();
        //escribir el archivo de salida
        out.write(bufferCifrado);
        
        in.close();
        out.close();
        
        
        
        
        //vamos a descifrar
        //tenemos que crear el menu para la carga del archivo a cifrar
        
        System.out.println("3.- Descifrar con DES el fichero : "+ args[0] + ", dejar el resultado en: "
        + args[0] + " cifrado");
        
       
        //la parte mas complicada el cifrado por pasos
        
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        
        //wiii que cansado ufff
        
        //entonces tenemos que transformar y leer el fichero en bytes
        
        
        
        byte[] bufferPlano;
        
        
        //vamos a generar el archivo
        
        in = new FileInputStream(args[0]+" .cifrado");
        out = new FileOutputStream(args[0]+" .descifrado");
        
        //tenemos que empezar por la lectura del archivo y converlo en bytes
        
        bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            bufferPlano  = cifrador.update(buffer, 0, bytesleidos);
            out.write(bufferPlano);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }
        
        //cuando termine de leer el archivo
        bufferPlano = cifrador.doFinal();
        //escribir el archivo de salida
        out.write(bufferPlano);
        
        in.close();
        out.close();
        
        
        
        
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de Cifrado DES, utilizando una llave HASH");
        System.out.println("\t Sintesis: Manejo de ficheros por favor agregue un fichero a este"
                + "programa para que pueda ejecutarse, debe de ser un texto plano '.txt' ");
        System.out.println();
    }

    public static void mostrarBytes(byte[] buffer) {
        //que este metodo nos va a convertir los archivos en bytes
        System.out.write(buffer, 0, buffer.length);
    }
    
}

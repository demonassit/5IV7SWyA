/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigital;

/**
 *
 * @author demon
 */

import java.security.*;
import java.security.Signature;

//formato de codificacion de firma
import sun.misc.BASE64Encoder;

public class FirmaDigital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        /*
        Vamos a programar un firma digital simple que es para
        validar la autenticidad de un documento, esto con el fin
        de comprobar que eld ocumento no haya tenido cambios
        */
        
        //primero vamos a generar nuestra instancia de rsa
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        //inicializar la llave
        generador.initialize(2048);
        
        //generamos la llave
        KeyPair llaves = generador.genKeyPair();
        
        //ejemplo del documento a firmar, que en este caso es un string
        
        byte[] dato = "Holi mundo kawaii".getBytes("UTF8");
        
        byte[] datos01 = "Holi mundo kawaii".getBytes("UTF8");
        
        byte[] datos1 = "Holi mundo kawaii    ".getBytes("UTF8");
        
        //ahora vamos a necesitar signature para firmar 
        Signature firma = Signature.getInstance("SHA1WithRSA");
        /*
        el poder utilizar una funcion hash dentro del algoritmo genera 
        mucho caos al documento, significa que le agrega mucho relleno
        entonces empieza a crecer el tamaño de firma y parece enorme y segura
        */
        
        firma.initSign(llaves.getPrivate());
        //ahora actualizamos al dato que vamos a firmar
        //o se aplica la llave
        firma.update(dato);
        
        //la firma al final es un grupo de bytes
        byte[] firmabytes = firma.sign(); //firmando el documento
        
        //esta es la cadena que se imprime en todos los documento originales
        System.out.println("Firma: " + new BASE64Encoder().encode(firmabytes));
        
        //ahora de forma independiente cuando se obtenga el archivo
        //o el documento ahora se debe de verificar que sea valido
        
        //se utiliza la llave publica
        firma.initVerify(llaves.getPublic());
        
        //ya necesitas el documento o dato
        firma.update(datos1);
        
        //entamos que nos regresa un T o F
        
        System.out.println(firma.verify(firmabytes));
        
        
        
        
    }
    
}

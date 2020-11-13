/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes5iv7;

/**
 *
 * @author demon
 */

import javax.crypto.Cipher;
//para el algoritmo de cifrado
import javax.crypto.spec.SecretKeySpec;
//para la generacion de las subllaves en el proceso del cifrado simetrico AES
import java.util.*;
import sun.misc.BASE64Encoder;

public class AES5IV7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        //String dato;
        /*
        Para el manejo de la llave dentro del algoritmo AES, se debe de tener un String
        de largo multiplo de 8 ya que debe de ser compactible con 
        
        128     16
        192     24
        256     32    caracteres
        
        Para este ejemplo utilizaremos un largo de 32 para un AES de 256
        
        */
        
        System.out.println("¿Cual es la llave secreta?");
        
        
        String llavesimetrica = entrada.next();
        
        //vamos a empezar a generar las llaves
        
        SecretKeySpec key = new SecretKeySpec(llavesimetrica.getBytes(), "AES");
        
        //creamos un objeto para el cifrado
        Cipher cipher;
        
        try{
            //crear la instancia de AES
            cipher = Cipher.getInstance("AES");
            
            //inicializamos el cifrado
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            //debemos de tener en un arreglo de bytes el mensaje a cifrar
            
            byte[] campoCifrado = cipher.doFinal("Habia una vez un patito que decia miau miau miau y tenia sueñito".getBytes());
            
            String mensaje_cifrado = new String(campoCifrado);
            
            System.out.println("El texto cifrado es: " + mensaje_cifrado);
            
            //para poderlo codificar hay que pasarlo a base 64 
            
            String base64 = new BASE64Encoder().encode(campoCifrado);
            
            System.out.println("El texto cifrado codificado: " + base64);
            
            //ahora vamos a decifrar
            
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            //necesitamos un arreglo para almacenar los datos del descifrado
            
            byte[] datosDescifrados = cipher.doFinal(campoCifrado);
            
            //generamos la cadena del mensaje
            
            String mensaje_original = new String(datosDescifrados);
            
            System.out.println("Mensaje descifrado :" + mensaje_original);
        
        }catch(Exception e){
            
            System.out.println("Error no se pudo T_T");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
    }
    
}

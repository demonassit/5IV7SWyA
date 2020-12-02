/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaejemplolib;

/**
 *
 * @author demon
 */

import java.security.*;
import javax.crypto.*;
import java.io.*;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
//esta libreria nos ayduara a generar numeros mas grandes y agregar padding 
//para la aplicacion del algoritmo de RSA

//para que aparezca el corazon debo agregar UTF-8 o ???


public class RSAEjemploLib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        //lo primero que tenemos que hacer es agregar el provider 
        Security.addProvider(new BouncyCastleProvider()); //cargar el nuevo proveedor del servicio del proveedor
        
        System.out.println("1.- Creacion de llaves publica y privada:");
        
        //recordermos que en RSA necesitamos una llave publica y una privada
        
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA", "BC");
        
        //inicializar las llaves, para eso tenemos que recordar que en rsa
        //NOS SUGIERE LLAVES DE 1024
        
        keygen.initialize(1024);
        //generar las llaves de rsa
        KeyPair clavesRSA = keygen.generateKeyPair();
        
        //determino mi llave publica 
        PublicKey clavePublica = clavesRSA.getPublic();
        //determino mi llave privada
        PrivateKey clavePrivada = clavesRSA.getPrivate();
        
        System.out.println("2.- Introduzca el texto a cifrar, por fis max 64 caracteres");
        
        //necesitamos un buffer de bytes
        
        byte[] bufferPlano = leerLinea(System.in);
        
        //paso 2 crear el cifrador de RSA
        
        /*
        BouncyClastle no funciona en modo ECB ya que no divide el mensaje
        en bloques significa que toma el flujo de el texto  y cifra bit por bit
        
        */
        
        Cipher cifrador = Cipher.getInstance("RSA", "BC");
        
        cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
        
        System.out.println("3.- Cifrado con llave publica: ");
        byte[] bufferCifrado = cifrador.doFinal(bufferPlano);
        System.out.println(" Texto cifrado : ");
        mostrarBytes(bufferCifrado);
        System.out.println("\nuwu fin del cifrado");
        
        
        //vamos a decifrar con privada
        cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
        System.out.println("4.- Desciframos con la llave privada");
        byte[] bufferDescifrado = cifrador.doFinal(bufferCifrado);
        System.out.println(" Texto Descifrado:  ");
        mostrarBytes(bufferDescifrado);
        System.out.println("\nnwn wiiiii se logro");
        
        
        //ahora veamos que pasa si ciframos con privada y desciframos con publica
        
        cifrador.init(Cipher.ENCRYPT_MODE, clavePrivada);
        System.out.println("5.- Cifrado con llave privada: ");
        bufferCifrado = cifrador.doFinal(bufferPlano);
        System.out.println(" Texto cifrado : ");
        mostrarBytes(bufferCifrado);
        System.out.println("\nuwu fin del cifrado");
        
        //ahora la publica
        cifrador.init(Cipher.DECRYPT_MODE, clavePublica);
        System.out.println("6.- Desciframos con la llave publica");
        bufferDescifrado = cifrador.doFinal(bufferCifrado);
        System.out.println(" Texto Descifrado:  ");
        mostrarBytes(bufferDescifrado);
        System.out.println("\nnwn wiiiii se logro");
        
        
        
        
    }

    public static byte[] leerLinea(InputStream in) throws Exception{
        byte[] buffer1 = new byte[1000];
        int i = 0;
        byte c;
        c =(byte)in.read();
        
        while((c!='\n')&&(i<1000)){
            buffer1[i] = c;
            c = (byte)in.read();
            i++;
        }
        
        byte[] buffer2 = new byte[i];
        for(int j = 0; j<i; j++){
            buffer2[j] = buffer1[j];
        }
        return buffer2;
    }   

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaarchivo;

/**
 *
 * @author demon
 * se debera de realizar un programa en java en el cual podamos, sacar en archivo
 * las llaves publicas y privadas para la compribacion del cifrado 
 */

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;

public class RSAArchivo {
    
    private static Cipher rsa;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        //iniciamos con la generacion de las llaves
        KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("RSA");
        
        //generacion de llaves a partir de la clase
        
        KeyPair llaves = keypairgenerator.generateKeyPair();
        //System.out.println("uwu");
        //llave publica
        PublicKey llavepublica = llaves.getPublic();
        
        
        //llave privada
        PrivateKey llaveprivada = llaves.getPrivate();
        
        //vamos a crear un metodo que se encargue de guardar en un fichero dicha llave
        
        guardarLlave(llavepublica, "publickey.key");
        
        //cargar la llave de un fichero
        
        llavepublica = cargarLlave("publickey.key");
        
        guardarLlave(llaveprivada, "privatekey.key");
        
        llaveprivada = cargarLLave("privatekey.key");
        
        
        //obtener la clase del cifrado
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //cifrado rsa que es asimetrico, por bloques y con relleno
        
        String texto = "Habia una vez un patito que decia miau miau";
        
        //cifrar
        rsa.init(Cipher.ENCRYPT_MODE, llavepublica);
        
        //nuestro arreglo de byte
        byte[] encriptado = rsa.doFinal(texto.getBytes());
        
        
        //vamos a escribir en el cifrado y ver los caracteres visibles
        
        for(byte b : encriptado){
            System.out.print("Cifrado: "+ Integer.toHexString(0xFF & b));
        }
        System.out.println();
        
        
        //descifrado
        rsa.init(Cipher.DECRYPT_MODE, llaveprivada);
        
        byte[] descencriptar = rsa.doFinal(encriptado);
        
        String textoDecifrado = new String(descencriptar);
        
        System.out.println("Descifrado: "+ textoDecifrado);
    }

    private static void guardarLlave(Key llave, String archivo) throws FileNotFoundException, IOException {
        byte[] llaves = llave.getEncoded(); //recibo una llave publica o privada
        
        //generar la salida de un archivo para guardar esa llave publica o privada
        FileOutputStream fos = new FileOutputStream(archivo);
        //escribe la llave publica o privada
        fos.write(llaves);
        fos.close();
    }


    private static PublicKey cargarLlave(String archivo) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //vamos a generar el archivo que se cargar
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        //ahora falta el tratamiento a la llave publica
        KeyFactory keyfactor = KeyFactory.getInstance("RSA");
        //vamos a darle un formato de salida a la llave publica para que se pueda visualizar 
        //para ello necesitamos ocupar el certificado x509 de codificacion de llaves
        KeySpec keyspec = new X509EncodedKeySpec(bytes);
        //ya que tiene formato
        //vamos a necesitar a la clase para poder importar el elemento
        PublicKey llaveparaBytes = keyfactor.generatePublic(keyspec);
        //retorno mi variable
        return llaveparaBytes;
        
    }


    private static PrivateKey cargarLLave(String archivo) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //vamos a generar el archivo que se cargar
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        //para una llave privada se necesita el certificado que viene por parte
        //de PKCS8 para certificarlas
         KeyFactory keyfactor = KeyFactory.getInstance("RSA");
         
         KeySpec keyspec = new PKCS8EncodedKeySpec(bytes);
         
         PrivateKey llaveparaBytes = keyfactor.generatePrivate(keyspec);
         
         return llaveparaBytes;
        
        
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmabouncy;

/**
 *
 * @author demon
 */

import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Encoder;

public class FirmaBouncy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //agregar el provider de BC ya que security no tiene soporte
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        
        //generacion de las llaves
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA", "BC");
        
        //inicializacion
        generador.initialize(2048, new SecureRandom());
        
        KeyPair llaves = generador.genKeyPair();
        
        Signature firma = Signature.getInstance("SHA1WithRSA", "BC");
        
        firma.initSign(llaves.getPrivate(), new SecureRandom());
        
        byte[] dato = "Habia una vez un patito que decia miau miau".getBytes("UTF8");
        
        firma.update(dato);
        

        
        //firma de los bytes
        byte[] firmabytes = firma.sign();
        
        System.out.println("Firma: " + new BASE64Encoder().encode(firmabytes));
        
        //verificamos la firma
        firma.initVerify(llaves.getPublic());
        
        firma.update(dato);
        
        System.out.println(firma.verify(firmabytes));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viggenere5iv7;

/**
 *
 * @author demon
 */
public class PrinViggenere {
    
    //variables
    
    char[] mensaje;
    char[] clave;
    char[] resultado;
    char matriz[][];  //la tabla del abecedario
    
    //metodo para recibir el mensaje y la clave
    
    public Mensaje(String msj, String clave){
        
        //obtener el mensaje y la clave y mandarlos a los arreglos de char
        this.mensaje = msj.toCharArray();
        char[] claveTemp = clave.toCharArray();
        this.clave = new char[mensaje.length];
        int cont = 0;
        
        //recorrer el mensje
        // vamos a tener en la parte superior al mensaje y a la izquierda la clave
        // y tenemos que recorrer las posiciones del mensaje vs posiciones de la clave
        // adentro de la matriz
        for(int i = 0; i<mensaje.length; i++){
            this.clave[i] = claveTemp[cont];
            cont++;
            if(cont == claveTemp.length)
                cont=0;
            
        }
        
        this.matriz = generarMatrizABC();
        Cifrar();
        
        
    }

    private char[][] generarMatrizABC() {
        
        int contador;
        char abcTemporal[] = this.generarAbecedario();
        char abc[] = new char[abcTemporal.length*2];
        
        for(int c =0; c<26; c++){
            abc[c] = abcTemporal[c];
            abc[c+26]= abcTemporal[c];
        }
        
        //vamos a generar la matriz de 2x2, para rellenarla con mis abc´s
        char[][] matriz = new char[26][26];
        //vamos a rellenar
        for(int i=0; i<26; i++){
            contador =0;
            for(int j=0; j<26; j++){
                matriz[i][j]=abc[contador+i];
                contador++;
            }
        }
        return matriz;
    }

    public void Cifrar() {
        //para poder cifrar vamso a necesitar las siguientes variables
        char[] cifrado = new char[mensaje.length];
        int i, j;
        //vamos a recorrer al mensaje
        for(int cont = 0; cont<mensaje.length; cont++){
            //saber la posicion de la letra a partir del codigo ASCII
            i = (int)this.mensaje[cont]-97; //mensaje
            j = (int)this.clave.length-97;  //clave
            cifrado[cont] = this.matriz[i][j];
        }
        this.resultado = cifrado;
        //letras del abc
        for(int k=0; k<26; k++){
            System.out.println("El abc:" +this.matriz[k]);//abecedario
            System.out.println("El mensaje:" +this.mensaje);//mensaje
            System.out.println("La clave:" +this.clave);//clave
            System.out.println("El cifrado:" +cifrado);//resultado del mensaje cifrado
        
        }
    }
    
    public String getMensajeCifrado(){
        String result = "";
        for(int i =0; i<resultado.length; i++){
            result = result+this.resultado[i];
        }
        return result;
        
    }
    
    //para generar mi abecedario conforme al codigo ascci
    
    private char[] generarAbecedario(){
        char [] abc = new char[26];
        for(int i = 97; i<122; i++){
            abc[i-97]=(char)i;
        }
        return abc;
    }
    
}

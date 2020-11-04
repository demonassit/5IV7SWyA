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
public class DecifrarVigenere {
    //una tabla de el abc
    char tablaCesar[]={'A', 'B', 'C', 'D', 'E', 'F',
                        'G', 'H', 'I', 'J', 'K', 'L', 
                        'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    public char getTextoDescifrado(char TextoCifrado, char TextoClave){
        
        //indices para encontrar los elementos
        //int indiceX = 0;
        //int indiceY = 0;
        
        int indiceCharTextoCifrado = 0;
        int indiceCharTextoClave = 0;
        
        for(int i = 0; i < tablaCesar.length; i++){
            if(TextoCifrado == tablaCesar[i]){
                indiceCharTextoCifrado = i;
                break;
            }
        }
        
        for(int j = 0; j<tablaCesar.length; j++){
            if(TextoCifrado == tablaCesar[j]){
                indiceCharTextoClave = j;
                break;
            }
        }
        
        
        if(indiceCharTextoCifrado >= indiceCharTextoClave){
            return tablaCesar[(indiceCharTextoCifrado - indiceCharTextoClave)%27];
        }else{
            return tablaCesar[27-(indiceCharTextoClave - indiceCharTextoCifrado)];
        }
    }
}

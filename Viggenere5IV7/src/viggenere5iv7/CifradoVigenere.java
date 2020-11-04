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
public class CifradoVigenere {
    
    //una tabla de el abc
    char tablaCesar[]={'A', 'B', 'C', 'D', 'E', 'F',
                        'G', 'H', 'I', 'J', 'K', 'L', 
                        'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    public char getTextoCifrado(char TextoClaro, char TextoClave){
        
        //indices para encontrar los elementos
        int indiceX = 0;
        int indiceY = 0;
        
        for(int i = 0; i < tablaCesar.length; i++){
            if(TextoClaro == tablaCesar[i]){
                indiceX = i;
                break;
            }
        }
        
        for(int j = 0; j<tablaCesar.length; j++){
            if(TextoClaro == tablaCesar[j]){
                indiceY = j;
                break;
            }
        }
        
        return tablaCesar[(indiceX + indiceY)%27];
    }
    
}

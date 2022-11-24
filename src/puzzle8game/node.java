/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8game;

import static puzzle8game.GUIPuzzle.endvalues;

/**
 *
 * @author USER
 */
public class node {
 
        int key;    
        int values [][];
        int hur;
        node parent;
        int valueThatMoved;

     public node(int curentValues[][], int hurType, node paret, int valueThatMove){
         values = curentValues;
         key = calKey(curentValues);
         if(hurType == 0)
           hur = services.M_Huirestic(curentValues, endvalues);
         else
           hur = services.T_Huirestic(curentValues, endvalues);
         parent = paret; 
         valueThatMoved = valueThatMove;
     }
    
     
        public static Integer calKey(int curentValues[][]){
                StringBuilder strNum = new StringBuilder();
        for(int row = 0 ; row < 3 ; row++){
         for (int col = 0; col<3; col++){ 
               strNum.append(curentValues[row][col]);
         }}
        Integer finalInt = Integer.parseInt(strNum.toString());
        return finalInt;

        }     
}

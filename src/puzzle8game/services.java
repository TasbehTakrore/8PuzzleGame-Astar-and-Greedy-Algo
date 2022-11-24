/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8game;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author USER
 */
public class services{
    
    
    
    public static int M_Huirestic(int curentValues[][], int endvalues[][]){
       
        int h = 0;
        int curValu;
        // nested loop for current values
        for(int rowC = 0 ; rowC < 3 ; rowC++){
         for (int colC = 0; colC<3; colC++){
            
           curValu = curentValues[rowC][colC];
           if(curValu == 0) continue;
        // nested loop for find vulue in End puzzle 
           for(int rowE=0; rowE<3; rowE++ ){
               for(int colE=0; colE<3; colE++){
                   
                   if(endvalues[rowE][colE] == curValu){
                       
                    h +=  Math.abs(colE-colC) + Math.abs(rowE-rowC);                    
                   }        
               } 
           }
            
        }
        }
        System.out.println("Hur: "+h);
        return h;
    
    
    }
    public static final int T_Huirestic(int curentValues[][], int endvalues[][]){
        int h=0;
        for(int row = 0 ; row < 3 ; row++){
         for (int col = 0; col<3; col++){  
             if(curentValues[row][col]==0)continue;
             if(curentValues[row][col] != endvalues[row][col])
                 h+=1;
         }
        }      
        
        return h;
    
    }   
//        public static final Integer calKey(int curentValues[][]){
//                StringBuilder strNum = new StringBuilder();
//        for(int row = 0 ; row < 3 ; row++){
//         for (int col = 0; col<3; col++){ 
//               strNum.append(curentValues[row][col]);
//         }}
//        Integer finalInt = Integer.parseInt(strNum.toString());
//        return finalInt;
//
//        }

    
    public static final void AstarAlgo(node startNode){
    HashMap <Integer, String> openList = new HashMap<Integer, String>();
    HashMap <Integer, String> aceesHurOpen = new HashMap<Integer, String>();
    LinkedHashMap <Integer, String> closedList = new LinkedHashMap<Integer, String>();
    
    
    
    
    openList.put(12, "aa");
    openList.put(3, "bb");
    openList.put(0, "cc");
System.out.println("openList: "+openList);

    //capitalCities.put("11", "hhh");
   // capitalCities.
    

    }
    public static final void greedyAlgo(node startNode){
        
    HashMap <Integer, String> openList = new HashMap<Integer, String>();
    HashMap <Integer, String> aceesHurOpen = new HashMap<Integer, String>();
    LinkedHashMap <Integer, String> closedList = new LinkedHashMap<Integer, String>();
    
    System.out.println("in greedy!");
    
    }

}

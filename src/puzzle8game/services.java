/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

/**
 *
 * @author USER
 */
public class services{
    
    static HashMap <Integer, node> openList;// Integer: Key
    static PriorityQueue <node> aceesHurOpen;// to be able acess less hur. node.. then get it from openlist
    static HashMap <Integer, node> closedList;
    
    
    
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
        System.out.println("Hur_M: "+h);
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
        System.out.println("Hur_T: "+h);
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
    public static final node greedyAlgo(node startNode, int HurType){ // i need to change it to return path not node
        
    openList = new HashMap<>(); // Integer: Key
    aceesHurOpen  = new PriorityQueue<>(new nodeComparator()); // to be able acess less hur. node.. then get it from openlist
    closedList = new LinkedHashMap<>(); // Integer: Key
    
    openList.put(startNode.key, startNode);
    aceesHurOpen.add(startNode);
    node bestHurNode;
    node childNode=null;
    int nextChildeNodeValues[][];
    int f=1; // flage to break nested loop whin fined 0 button
    int row=0;
    int col=0;
    
    try{
    
    do{
        f=1;
        System.out.println("pro Q:"+aceesHurOpen);
        System.out.println("Open List:"+openList);
        System.out.println("Closed List:"+closedList+"\n");
        
        if(openList.isEmpty()){
            System.out.println("******************************Open List empty! :/");
            return startNode;
        }
        bestHurNode = aceesHurOpen.poll();
        //System.out.println("hur for currently best"+ bestHurNode.hur);
        if(bestHurNode.hur ==0) {  // when end solu.
           System.out.println("findBest H:"+bestHurNode.hur +" | "+ bestHurNode.myPath);
            return bestHurNode;
        }
        
        //to know row and col  for (0) button  in bestHurNode
        for(row =0; row<3; row++){
            for(col=0; col<3; col++){
            if (bestHurNode.values[row][col]==0)
                {  f=0;
                   break;
                }  
            }
          if (f==0) break;
          }
        System.out.println("\n col + row"+col+" | "+row);
          // create childe (4 childe or less) depend on row and col values
          if(row>0){ // up 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row-1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode, bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]));
            pushtoOpenIfcanIt(childNode);
                      System.out.println(childNode.myPath);

          }
          
          if(row<2){ // down 0 button
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row+1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode,bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]));
            pushtoOpenIfcanIt(childNode);   
                      System.out.println(childNode.myPath);

              
          }
           System.out.println(bestHurNode.myPath);

          if(col>0){ // left 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row, col-1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode,bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]));
            pushtoOpenIfcanIt(childNode);
                      System.out.println(childNode.myPath);

          }     
          
           System.out.println(bestHurNode.myPath);

          if(col<2){ // right 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row, col+1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode, bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]));
            pushtoOpenIfcanIt(childNode);
                      System.out.println(childNode.myPath);

          }  
          
                 
          closedList.put(bestHurNode.key,openList.remove(bestHurNode.key)); // remove base best hur. node from open and set to close
    //
    }while (!openList.isEmpty());
    
    } catch(Exception e){
    System.out.println("ee"+e);
    }
    //System.out.println("in greedy!");
    return startNode; // no need in real, this to delet error
}
    
    static int[][] swap(int[][] parentnodeValues,int row1,int col1,int row2, int col2){ //row1, col1> (value) ||| row2, col2> (0)

        int nextCildeNodeValues[][] = Arrays.stream(parentnodeValues).map(int[]::clone).toArray(int[][]::new);
        //int nextCildeNodeValues[][] = parentnodeValues;
         nextCildeNodeValues[row1][col1] = nextCildeNodeValues[row2][col2];
         nextCildeNodeValues[row2][col2] =0;
         return nextCildeNodeValues;
         
                   
//          System.out.print("bestHurNode.val: ");
//         for(int rowp =0; rowp<3; rowp++){
//         for(int colp=0; colp<3; colp++){
//             System.out.print(bestHurNode.values[rowp][colp]+",");
//             }
//         }
//       System.out.println();
//          
         
    }
    
    static void pushtoOpenIfcanIt(node childNode){
        int childeKey = childNode.key;
        if(closedList.containsKey(childeKey))
            return;
        if(openList.containsKey(childeKey))
            return;       
        openList.put(childeKey, childNode);
        aceesHurOpen.add(childNode);
        
    }

}

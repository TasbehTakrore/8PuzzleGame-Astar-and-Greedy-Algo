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
import static puzzle8game.GUIPuzzle.*;

/**
 *
 * @author USER
 */
public class services{
    
    static HashMap <Integer, node> openList;// Integer: Key
    static PriorityQueue <node> aceesHurOpen;// to be able acess less hur. node.. then get it from openlist
    static LinkedHashMap <Integer, node> closedList;
    
    
    
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
                       
                    h +=  (Math.abs(colE-colC) + Math.abs(rowE-rowC));                    
                   }        
               } 
           }
            
        }
        }
       // System.out.println("Hur_M: "+h);
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
        //System.out.println("Hur_T: "+h);
        return h;
    
    }   

    
    public static final node AstarAlgo(node startNode, int HurType){
        openList = new HashMap<>();
        aceesHurOpen = new PriorityQueue<>(new nodeComparator());
        closedList = new LinkedHashMap<>();
        
        int cost =0;
        
        openList.put(startNode.key, startNode);
        aceesHurOpen.add(startNode);       
        
        node bestHurPlsCostNode;
        node childNode;
        int nextChildeNodeValues[][];       
        int f; // flage to break nested loop whin fined 0 button
        int row;
        int col=0;
        int isPushed;

        
        do{
            
            f=1;
        // System.out.println("pro Q:"+aceesHurOpen.);
        System.out.println("Open List:"+openList.keySet());
        System.out.println("Closed List:"+closedList.keySet()+"\n");
//        System.out.print("___________________________________________________________\n");

            if(openList.isEmpty()){
            System.out.println("******************************Open List empty! :/");
            return startNode;
            } 
            
            bestHurPlsCostNode = aceesHurOpen.poll();
          //          System.out.println("endKey+++++: "+endKey);
          
            cost = bestHurPlsCostNode.hurPlsCost - bestHurPlsCostNode.hur +1;
            System.out.println("cos: "+cost);
            

            if(bestHurPlsCostNode.key == endKey) {  // when end solu. // here only hur+cost, the best when h+c lowest and the key = end key
            System.out.println("Closed List:"+closedList.keySet()+"\n"); // here test path
            return bestHurPlsCostNode;
            }
            
            //to know row and col  for (0) button  in bestHurNode
            for(row =0; row<3; row++){
                for(col=0; col<3; col++){
                    if (bestHurPlsCostNode.values[row][col]==0)
                    {  f=0;
                       break;
                    }  
                }
                if (f==0) break;
            }
          System.out.println("row: "+row+", Col: "+col);

          // create childe (4 childe or less) depend on row and col values, then push if can, if not can push if path better
          if(row>0){ // up 0 button  
            nextChildeNodeValues = swap(bestHurPlsCostNode.values, row, col, row-1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurPlsCostNode, bestHurPlsCostNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],cost);
           System.out.println("one: "+childNode.key);
            isPushed = pushtoOpenIfcanIt(childNode);
            if(isPushed == 0){ // didnt 
                replaceIfPathbetter(childNode);
                
            }
            //System.out.println(childNode.myPath);
          }
          
          if(row<2){ // down 0 button
              
            nextChildeNodeValues = swap(bestHurPlsCostNode.values, row, col, row+1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurPlsCostNode,bestHurPlsCostNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],cost);
            System.out.println("Two: "+childNode.key);

            isPushed = pushtoOpenIfcanIt(childNode);
            if(isPushed == 0){ // didnt 
                replaceIfPathbetter(childNode);
                
            }
              
          }

          if(col>0){ // left 0 button  
            nextChildeNodeValues = swap(bestHurPlsCostNode.values, row, col, row, col-1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurPlsCostNode,bestHurPlsCostNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],cost);
                       System.out.println("Three: "+childNode.key);

            isPushed = pushtoOpenIfcanIt(childNode);
            if(isPushed == 0){ // didnt 
                replaceIfPathbetter(childNode);
                
            }

          }
                    

          if(col<2){ // right 0 button  
            nextChildeNodeValues = swap(bestHurPlsCostNode.values, row, col, row, col+1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurPlsCostNode, bestHurPlsCostNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],cost);
                       System.out.println("Four: "+childNode.key);

            isPushed = pushtoOpenIfcanIt(childNode);
            if(isPushed == 0){ // didnt 
                replaceIfPathbetter(childNode);
                
            }
          } 
                 
          closedList.put(bestHurPlsCostNode.key,openList.remove(bestHurPlsCostNode.key)); // remove base best hur. node from open and set to close
    //
    }while (!openList.isEmpty());

    return startNode; // no need in real, this to delet error
          
    }
    
    public static final node greedyAlgo(node startNode, int HurType){ // i need to change it to return path not node
        
        openList = new HashMap<>(); // Integer: Key
        aceesHurOpen  = new PriorityQueue<>(new nodeComparator()); // to be able acess less hur. node.. then get it from openlist
        closedList = new LinkedHashMap<>(); // Integer: Key
    
        openList.put(startNode.key, startNode);
        aceesHurOpen.add(startNode);
        node bestHurNode;
        node childNode;
        int nextChildeNodeValues[][];
        int f; // flage to break nested loop whin fined 0 button
        int row;
        int col=0;
        
        do{
        f=1;
      //  System.out.println("pro Q:"+aceesHurOpen);
      //  System.out.println("Open List:"+openList);
       // System.out.println("Closed List:"+closedList+"\n");
       
        System.out.println("Open List:"+openList.keySet());
        System.out.println("Closed List:"+closedList.keySet()+"\n");
       
        if(openList.isEmpty()){
            System.out.println("******************************Open List empty! :/");
            return startNode;
        }
        bestHurNode = aceesHurOpen.poll();
        //System.out.println("hur for currently best"+ bestHurNode.hur);
        if(bestHurNode.hur ==0) {  // when end solu. // hwre only hur, cost =0
        System.out.println("Closed List:"+closedList.keySet()+"\n"); // here test path
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
                  System.out.println("row: "+row+", Col: "+col);

          // create childe (4 childe or less) depend on row and col values, then push if can
          if(row>0){ // up 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row-1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode, bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],0);
                       System.out.println("one: "+childNode.key);

            pushtoOpenIfcanIt(childNode);
                    //  System.out.println(childNode.myPath);
          }
          
          if(row<2){ // down 0 button
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row+1, col);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode,bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],0);
                       System.out.println("Two: "+childNode.key);

            pushtoOpenIfcanIt(childNode);   
                  //    System.out.println(childNode.myPath);

              
          }
           //System.out.println(bestHurNode.myPath);

          if(col>0){ // left 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row, col-1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode,bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],0);
                       System.out.println("Three: "+childNode.key);

            pushtoOpenIfcanIt(childNode);
                  //    System.out.println(childNode.myPath);

          }     
          
           //System.out.println(bestHurNode.myPath);

          if(col<2){ // right 0 button  
            nextChildeNodeValues = swap(bestHurNode.values, row, col, row, col+1);
            childNode = new node(nextChildeNodeValues, HurType, bestHurNode, bestHurNode.myPath+","+Integer.toString(nextChildeNodeValues[row][col]),nextChildeNodeValues[row][col],0);
                       System.out.println("one: "+childNode.key);

            pushtoOpenIfcanIt(childNode);
                 //     System.out.println(childNode.myPath);

          }  
          
                 
          closedList.put(bestHurNode.key,openList.remove(bestHurNode.key)); // remove base best hur. node from open and set to close
    //
    }while (!openList.isEmpty());

    return startNode; // no need in real, this to delet error
}
    
    static void replaceIfPathbetter(node childNode){ // i 
        
        System.out.println("inside replace if path is better **out**");
        int childeKey = childNode.key;
        int childeHurPlsCost = childNode.hurPlsCost;
        if(openList.containsKey(childeKey)){
            if(openList.get(childeKey).hurPlsCost>childeHurPlsCost){
                openList.put(childeKey, childNode); // in HashMap.. replace node to new node
                        System.out.println("inside replace if path is better **in open**");

        }}
        if(closedList.containsKey(childeKey)){
            if(closedList.get(childeKey).hurPlsCost>childeHurPlsCost){
                openList.put(childeKey, childNode); // reback to open list with new Hur+cost
                closedList.remove(childeKey);
                       System.out.println("inside replace if path is better **in closed**");

            
            }              
        } 
    
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
    
    static int pushtoOpenIfcanIt(node childNode){
        int childeKey = childNode.key;
        int i = 0;   // 0: didnt push
        if(closedList.containsKey(childeKey))
        { 
            System.out.println("not push, the Key="+childeKey);
            return i;}
        if(openList.containsKey(childeKey))
        {  
            System.out.println("not push, the Key="+childeKey);
            return i;    
        }   
        openList.put(childeKey, childNode);
        aceesHurOpen.add(childNode);
        i = 1;  // yes push
        return i; // 
        
    }
    
//    static String findeTestPath(){
//        String testPath="";
//        node nodefromCloseList;
//        
//        for (Map.Entry<Integer, node> i : closedList.entrySet()) {
//           testPath+= closedList.get(i.getKey()).valueThatSwaped+",";
//        }
//        return testPath.substring(2);
//    }

}

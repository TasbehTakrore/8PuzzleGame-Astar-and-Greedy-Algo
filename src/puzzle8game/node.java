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
    int values[][];
    int hur; // when greedy> cost =0, when A* we have cost...
    node parent;
    String myPath;
    int valueThatSwaped;
    int hurPlsCost;

    public node(int curentValues[][], int hurType, node paret, String valueThatMove, int valThatSwaped, int cost) {
        values = curentValues;
        key = calKey(curentValues);
        if (hurType == 0) {
            hur = services.M_Huirestic(curentValues, endvalues)+cost;
            hurPlsCost = hur +cost;
        } else {
            hur = services.T_Huirestic(curentValues, endvalues)+cost;
            hurPlsCost = hur +cost;
        }
        parent = paret;
        myPath = valueThatMove;
        valueThatSwaped = valThatSwaped;
    }

    public static Integer calKey(int curentValues[][]) { // Key calculation 
        StringBuilder strNum = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                strNum.append(curentValues[row][col]);
            }
        }
        Integer finalInt = Integer.parseInt(strNum.toString());
        return finalInt;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8game;

import java.util.*;

/**
 *
 * @author USER
 */
public class nodeComparator implements Comparator<node>{
             
            // Overriding compare()method of Comparator
                        // for descending order of cgpa
      @Override
            public int compare(node n1, node n2) {
                if (n1.hurPlsCost <= n2.hurPlsCost)
                    return -1;
                else if (n1.hurPlsCost > n2.hurPlsCost)
                    return 1;
                                return 0;
                }
        }

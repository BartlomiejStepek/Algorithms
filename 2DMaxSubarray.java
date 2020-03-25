/*** Bartlomiej Stepek - 2D Maximum Subarray ***/
package source;

import java.util.Scanner;

public class Source {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
            int number_of_sets = sc.nextInt();
            
            for(int  i = 0; i < number_of_sets; i++) { 
                int line = sc.nextInt();                
                int column = sc.nextInt();
                int[][]tab = new int[line][column];

                for(int j = 0; j < line; j++)
                    for(int z = 0; z < column; z++)
                        tab[j][z] = sc.nextInt();

                
                int max_temp = 0;  
                int max_part = -1; 
                int max_sum = -1;  
                int column_begin = -1, column_end = -1;
                int line_begin = -1, line_end = -1;
                int c1 = -1, c2 = -1, c3 = -1, c4 = -1; 
                
                int[] tmp = new int[column];   
        
                for(int a = 0; a < line; a++) {
                    
                    for(int w = 0; w < column; w++) 
                        tmp[w] = 0;        
                    
                    for(int j  = a; j < line; j++) {
                        max_temp = 0;  
                        max_part = -1;  

                        for(int k = 0; k < column; k++) {    
                            tmp[k] += tab[j][k];
                            max_temp = Math.max(tmp[k] + max_temp, tmp[k]);
                            
                            if(k == 0 || max_temp == tmp[k])
                                c1 = k;                    
                            c2 = k;                    

                            if(max_temp > max_part) {       
                                max_part = max_temp;
                                c3 = c1; c4 = c2;
                            }
                            if(max_temp == max_part && (c1 - c2 > c3 - c4 || c3 == -1))
                                c3 = c1; c4 = c2;        

                        }

                        if(max_sum < max_part) {    
                            max_sum = max_part;   
                            line_begin = a;
                            line_end = j;
                            column_begin = c3;
                            column_end = c4;
                        }
                        if(max_sum == max_part && 
                                (((c4 - c3+1) * (j - a+1)) <   
                                (column_end - column_begin + 1) * (line_end - line_begin + 1))){
                            column_begin = c3;     
                            column_end = c4;
                            line_begin = a;
                            line_end = j;
                        }
                    }
                }
                if(max_sum >= 0){
                    System.out.println("[" + line_begin + ".." + line_end +
                                    "," + column_begin + ".." + column_end + "]");
                    System.out.println("max_sum=" + max_sum);
                }
                else
                    System.out.println("empty"); 
            }                             
    }
}
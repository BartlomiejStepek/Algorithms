/*** Bartlomiej Stepek - 2D Maximum Subarray ***/
package 2dmaxsubarray;

import java.util.Scanner;

public class 2DMaxSubarray {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
            int number_of_sets = sc.nextInt();
            
            for(int  i = 0; i < number_of_sets; i++) { 
                int line = sc.nextInt();                
                int column = sc.nextInt();
                int[][]array = new int[line][column];

                for(int j = 0; j < line; j++)
                    for(int z = 0; z < column; z++)
                        array[j][z] = sc.nextInt();

                
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
                            tmp[k] += array[j][k];
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

/***
    Input
    21  
    2 5
    1 1 -1 -1 0
    1 1 -1 -1 4
    2 5
    0 -1 -1 1 1
    4 -2 -2 1 1
    2 5
    0 -1 -1 4 0
    4 -2 -2 0 0
    2 5
    -1 -2 -3 -1 -2
    -1 -1 -1 -1 -5
    2 5
    0 0 0 0 0
    0 0 0 0 0
    3 6
    0 0 0 -2 1 1
    0 1 1 -2 1 1
    0 1 1 -2 0 0
    4 8
    -1 10 -3 5 -4 -8 3 -3
    8 -2 -6 -8 2 -5 4 1
    3 -2 9 -9 -1 10 -5 2
    1 -3 5 -7 8 -2 2 -6
    2 2
    -1 0
    0 -1
    1 6
    -2 7 -4 8 -5 4
    1 1
    5
    3 3
    -1 -1 -1
    -1 0 -1
    -1 -1 -1
    1 7
    -3 -2 -3 -2 3 -2 -1
    7 1
    -3
    -2
    -3
    -2
    3 
    -2
    -1
    1 3
    8 -2 10
    4 4
    -2 -2 1 -2
    1 -2 -2 -2
    -1 -1 -2 -2
    1 -2 1 -2
    1 1
    0
    6 2
    5 7
    -1 -2
    4 8
    -10 -12
    9 3
    -2 -2
    3 5
    3 2 3 4 5
    5 5 -30 5 5
    -2 -2 10 -2 -2
    2 5
    -2 -2 -2 1 4
    3 2 -2 -2 -2
    3 3
    -10 -10 11
    -10 10 -10
    12 -10 -10
    2 2
    -1 -1 
    -1 -1

    Output
    [1..1,4..4]
    max_sum=4
    [1..1,0..0]
    max_sum=4
    [0..0,3..3]
    max_sum=4
    empty
    [0..0,0..0]
    max_sum=0
    [0..1,4..5]
    max_sum=4
    [0..3,0..2]
    max_sum=19
    [0..0,1..1]
    max_sum=0
    [0..0,1..3]
    max_sum=11
    [0..0,0..0]
    max_sum=5
    [1..1,1..1]
    max_sum=0
    [0..0,4..4]
    max_sum=3
    [4..4,0..0]
    max_sum=3
    [0..0,0..2]
    max_sum=16
    [0..0,2..2]
    max_sum=1
    [0..0,0..0]
    max_sum=0
    [0..2,0..1]
    max_sum=21
    [0..1,3..4]
    max_sum=19
    [0..0,3..4]
    max_sum=5
    [2..2,0..0]
    max_sum=12
    empty
***/

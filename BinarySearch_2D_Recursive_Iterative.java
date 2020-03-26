/*** Bartlomiej Stepek - BinarySearch_2D_Recursive_Iterative ***/
package binarysearch_2d_recursive_iteriative;

import java.util.Scanner; 

public class BinarySearch_2D_Recursive_Iterative {

    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        
        int number_of_sets = in.nextInt(); 

        for(int i = 0; i < number_of_sets; i++) {

                int line = in.nextInt();     
                int column = in.nextInt();
                int[][]array = new int[line][column];

                for(int j = 0; j < line; j++)
                    for(int z = 0; z < column; z++)
                        array[j][z] = in.nextInt();
                
                int number_searched = in.nextInt();
                
                RecFirst(array, number_searched, 0, line, column);
                RecLast(array, number_searched, line-1, line, column);
                IterFirst(array, number_searched, line, column);
                IterLast(array, number_searched, line, column);
                System.out.println("---");
                
                
        }
    }
    
    public static int Binary_Search_First(int [][]array, int number, int actual_line, int first_, int last_){
        int first = first_, last = last_, s,  a = actual_line;
            s = (first+last)/2;
            
            if(first > last)
                return -1;
            else{
                if(array[a][s] == number) {
                    if(s == 0)  
                        return s;       
                    else {
                        if(array[a][s - 1] == number)
                            return Binary_Search_First(array, number, actual_line, first, s-1);
                        else
                            return s;   
                    }
                }
                else if(array[a][s] > number)
                    return Binary_Search_First(array, number, actual_line, first, s-1); 
                else 
                    return Binary_Search_First(array, number, actual_line, s+1, last); 
            }
    }
    
    
    public static void RecFirst(int [][]array, int number, int actual_line, int line, int column) {
        int a = actual_line;
        int s = Binary_Search_First(array, number, a, 0, column-1);  
        if(s == -1 && a < line-1){
            a++;
            RecFirst(array, number, a, line, column);  
        }
        else if (s == -1 && a == line-1)   
            System.out.println("RecFirst: there is no " + number);
        else    
            System.out.println("RecFirst: " + number + " w (" + a + "," + s + ")");
            
    }
   
    public static int Binary_Search_Last(int [][]array, int number, int actual_line, int first_, int last_, int column){
        int first = first_, last = last_, s,  a = actual_line;
            s = (first+last)/2;
            
            if(first > last)
                return -1;
            else{
                if(array[a][s] == number) {
                    if(s == column-1)  
                        return s;       
                    else {
                        if(array[a][s + 1] == number)
                            return Binary_Search_Last(array, number, actual_line, s+1, last, column);
                        else
                            return s;  
                    }
                }
                else if(array[a][s] > number)
                    return Binary_Search_Last(array, number, actual_line, first, s-1, column); 
                else 
                    return Binary_Search_Last(array, number, actual_line, s+1, last, column); 
            }
    }
    
    public static void RecLast(int [][]array, int number, int actual_line, int line, int column) {
        int a = actual_line;
        int s = Binary_Search_Last(array, number, a, 0, column-1, column); 
        if(s == -1 && a > 0){
            a--;
            RecLast(array, number, a, line, column);  
        }
        else if (s == -1 && a == 0)
            System.out.println("RecLast: there is no " + number); 
        else
            System.out.println("RecLast: " + number + " w (" + a + "," + s + ")");
            
    }
        
    public static void IterFirst(int [][]array, int number, int line, int column) {
            int a = 0, s=-1, first, last;
            while(a <= line-1) {
                first = 0;
                last = column - 1; 
                while(first <= last) {      
                    s = (first + last)/ 2;
            
                    if(array[a][s] == number) {
                        if(s == 0)     
                            break;       
                        else {
                            if(array[a][s - 1] == number)
                                last = s - 1;   
                            else
                                break;       
                        }
                    }
                    else if(array[a][s] > number)
                        last = s - 1;
                    else 
                        first = s + 1;
                }
                if(first > last)   
                    a++;
                else        
                    break;
            }
            if(a == line)
                System.out.println("IterFirst: there is no " + number);   
            else
                System.out.println("IterFirst: " + number + " w (" + a + "," + s + ")"); 
    }
            
    public static void IterLast(int [][]array, int number, int line, int column) {
            int a = line-1, s=-1, first, last;
            while(a >= 0) {
                first = 0;
                last = column - 1; 
                while(first <= last) {      
                    s = (first + last)/ 2;
            
                    if(array[a][s] == number) {
                        if(s == column-1)      
                            break;       
                        else {
                            if(array[a][s + 1] == number)
                                first = s + 1;   
                            else
                                break;    
                        }
                    }
                    else if(array[a][s] > number)
                        last = s - 1;
                    else 
                        first = s + 1;
                }
                if(first > last)  
                    a--;
                else        
                    break;
        }
            if(a == -1)
                System.out.println("IterLast: there is no " + number); 
            else
                System.out.println("IterLast: " + number + " w (" + a + "," + s + ")"); 
    }
}

/***
    Input
    3
    3 4
    10 10 10 10
    10 20 20 30
    20 20 20 40
    20 
    3 4
    10 10 10 10
    10 20 20 30
    20 20 20 40
    50
    3 3 
    10 10 10
    10 20 20
    20 20 20
    10
    1 1
    10 
    20
    1 1
    10
    10
    4 4
    10 20 20 30
    10 20 20 30
    20 20 20 40
    20 30 40 50
    50
    4 4
    10 20 20 30
    10 20 20 30
    20 20 20 40
    20 30 40 50
    60
    2
    2 4
    10 10 10 20
    20 30 30 30
    20
    2 2
    10 10
    10 10
    10

    Output
    RecFirst: 20 w (1,1)
    RecLast: 20 w (2,2)
    IterFirst: 20 w (1,1)
    IterLast: 20 w (2,2)
    ---
    RecFirst: there is no 50
    RecLast: there is no 50
    IterFirst: there is no 50
    IterLast: there is no 50
    ---
    RecFirst: 10 w (0,0)
    RecLast: 10 w (1,0)
    IterFirst: 10 w (0,0)
    IterLast: 10 w (1,0)
***/

/*** Bartlomiej Stepek - QuickSort without recursion and stack ***/
package quicksort;
import java.util.Scanner; 

public class QuickSortWithoutRecursionAndStack {
    
    public static int Partition(long[] array, int L, int R)  {  
        int i = L -1;
        long x = array[R]; 
        for ( int  j = L; j < R; j++) {   
            if (array[j] < x )     {           
                i = i+1;           
                swap (array, i, j);      
            }  
        }
        swap (array, i+1, R); 
        return (i+1);
    }
    
    public static void swap(long[] array, int i, int j)  {  
        long tmp = array[i];  array[i] = array[j]; array[j] = tmp;               
    }

    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        
        int number_of_sets = in.nextInt();

        for(int a = 0; a < number_of_sets; a++) {
            
            int numbers = in.nextInt();  
            long[] array = new long [numbers];
            
            for(int k = 0; k < numbers; k++)
                array[k] = in.nextInt();
            
            int L = 0, R = numbers - 1;
            
            while(L < R || L < numbers-1){
                if(L < R) {
                    int q = Partition (array,L,R); 
                    
                    long max = array[L]; 
                    int k = L;
                    for(int i = L + 1; i < q; i++)
                        if(array[i] > max) {
                            max = array[i];
                            k = i;
                        }

                    if(q == L) 
                        L++;
                    else if(q == R) 
                        R--;
                    else {
                        long _R = array[R];               
                        long _q = array[q];              
                        long _q_minus_one = array[q-1];  
                        array[R] = array[k];              
                        array[q] = _R;
                        array[q-1] = _q;          
                        if(q-1 != k) 
                            array[k] = _q_minus_one;
                                               
                        R = q - 2;              
                    }
                    
                } else {    

                    int i = R + 1;
                    long q = array[R + 1];
                    while(array[i] >= q && i<numbers - 1)
                        i++;
                    
                    if(array[i] < q) { 
                        long tmp = array[R+1];  
                        array[R+1] = array[i];
                        array[i] = array[R+2];
                        array[R+2] = tmp;
                        L = R + 3;  
                        R = i;  
                    }
                    else { 
                        L++;
                        R++;
                    }
                }            
            }
            
            for(int k = 0; k < numbers; k++)
                System.out.print(array[k] + " ");
            System.out.println();
        }
    }
}

/***
    Input
    7
    5
    4 2 3 1 5
    10
    2 4 6 8 10 9 7 5 3 1
    10
    10 9 8 7 6 5 4 3 2 1
    10
    1 2 3 4 5 6 7 8 9 10
    17
    14 28 72 92 49 19 30 38 37 20 17 10 22 29 3 50 73
    11
    5 -5 4 -4 3 -3 2 -2 1 -1 0
    8 
    2832 8273 2873 3983 5095 4090 5059 3737

    Output
    1 2 3 4 5
    1 2 3 4 5 6 7 8 9 10
    1 2 3 4 5 6 7 8 9 10
    1 2 3 4 5 6 7 8 9 10
    3 10 14 17 19 20 22 28 29 30 37 38 49 50 72 73 92
    -5 -4 -3 -2 -1 0 1 2 3 4 5 
    2832 2873 3737 3983 4090 5059 5095 8273 
***/
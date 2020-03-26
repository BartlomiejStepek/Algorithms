/*** Bartlomiej Stepek - Binary Search in 1D array ***/
package binarysearch1d;
import java.util.Scanner;

public class BinarySearch1D {

    public static Scanner sc = new Scanner(System.in);
    
    public static int find_the_number(int x, int[] array, int nb) {
        int first = 0, s, last = nb - 1;
        while(first <= last) {     
            s = (first + last)/ 2; 
            
            if(array[s] == x) {
                if(s == 0)
                    return s;   
                else {
                    if(array[s - 1] == x)
                        last = s - 1; 
                    else
                        return s;  
                }
            }
            else if(array[s] > x)  
                last = s - 1;
 
            else
                first = s + 1;
        
        }
        return first;
    }
    
    public static void main(String[] args) {
        
        int number_of_sets = sc.nextInt(); 
        
        for(int i = 0; i < number_of_sets; i++) {
            
            int amount_of_numbers = sc.nextInt(); 
            int[] array = new int[amount_of_numbers];
            
            for(int j = 0; j < amount_of_numbers; j++) 
                array[j] = sc.nextInt(); 
            
            int amount_of_search_numbers = sc.nextInt(); 
            for(int j = 0; j < amount_of_search_numbers; j++) {
                int x = sc.nextInt(); //szukane elementy
                
                int first_element = find_the_number(x, array, amount_of_numbers);
                int next_element = find_the_number(x + 1, array, amount_of_numbers);
                
                int before = first_element;
                int quantity = next_element - first_element;
                System.out.print("(" + before + "," + quantity + ") ");
                
            }
            System.out.print("\n");
            int counter = 1;
            System.out.print(array[0] + " ");
            for(int a = 1; a < amount_of_numbers; a++) {
                if(array[a-1] != array[a] && counter < 200) { 
                    if(counter == 50 || counter == 100 || counter == 150)
                        System.out.print("\n");
                    System.out.print(array[a] + " ");
                    counter++;
                }
            }
            System.out.print("\n");
        }
    }
}

/***
    Input
    8
    12
    -1 1 2 2 2 3 5 5 7 7 9 9
    10
    1 2 3 -1 4 9 5 6 7 8
    10
    1 2 2 2 2 2 3 3 3 3
    5
    1 2 3 4 0
    10
    0 0 0 0 0 0 0 0 0 1
    4
    0 -1 1 2
    10
    1 1 1 1 1 1 1 1 1 1
    3
    1 0 2
    24
    -9559 -8170 -6214 -5112 -5112 -3969 -3698 -2143 -2143 -2143 461 461 688 2365 2386 2722 4572 4572 6527 6527 6527 6905 8979 9840
    3
    -193120 461 688    
    10
    -6470 -4448 -3194 -2601 -2601 -2601 -663 12 3932 5953
    8
    42772 -4448 -3194 22536 -2601 -2601 56628 12
    24
    -9568 -9221 -8927 -7621 -6986 -6870 -6870 -6787 -3695 -3188 -2812 -2069 853 853 862 865 865 1094 1094 1094 3436 8516 9081 9188
    3
    200743 -2069 853
    8
    -1 -1 0 0 1 1 2 2
    5
    -1 0 1 2 3

    Output
    (1,1) (2,3) (5,1) (0,1) (6,0) (10,2) (6,2) (8,0) (8,2) (10,0)
    -1 1 2 3 5 7 9
    (0,1) (1,5) (6,4) (10,0) (0,0)
    1 2 3
    (0,9) (0,0) (9,1) (10,0)
    0 1
    (0,10) (0,0) (10,0)
    1
    (0,0) (10,2) (12,1)
    -9559 -8170 -6214 -5112 -3969 -3698 -2143 461 688 2365 2386 2722 4572 6527 6905 8979 9840
    (10,0) (1,1) (2,1) (10,0) (3,3) (3,3) (10,0) (7,1)
    -6470 -4448 -3194 -2601 -663 12 3932 5953 
    (24,0) (11,1) (12,2)
    -9568 -9221 -8927 -7621 -6986 -6870 -6787 -3695 -3188 -2812 -2069 853 862 865 1094 3436 8516 9081 9188 
    (0,2) (2,2) (4,2) (6,2) (8,0)
    -1 0 1 2 
    
***/

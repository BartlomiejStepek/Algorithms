/*** Bartlomiej Stepek - Binary Search in 1D array ***/
//package source;
import java.util.Scanner;

public class Source {

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
            else if(array[s] > x) {   
                last = s - 1;
            }
            else if(array[s] < x) {
                first = s + 1;
            }
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

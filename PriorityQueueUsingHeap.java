/*** Bartlomiej Stepek - gr.5 - Priority Queue using Heap ***/
package priorityqueue;
import java.util.Scanner; 

public class PriorityQueueUsingHeap {

    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
                
        int number_of_sets = in.nextInt();

        for(int a = 0; a < number_of_sets; a++) {
            
            int N = in.nextInt();
            int P = in.nextInt();
            String order = in.next();
            Element[] array = new Element[N];
            int counter_numbers = 0;
            int counter_all = 0;
            while(!order.equals("s")) {
                
                if(order.equals("i")) {
                    
                    int quantity = in.nextInt();
                    for(int i = 0; i < quantity; i++) {
                        int number = in.nextInt(); 
                        
                        if(counter_all < P) {
                            int n;
                            for(n = 0; n < counter_numbers; n++) {
                                if(array[n].value == number) {
                                        array[n].occurancy++;
                                        counter_all++;
                                        upHeap(n,array);
                                    break; 
                                }
                            }
                            if(n == counter_numbers && counter_numbers < N) {
                                Element x = new Element(number);
                                x.occurancy = 1;
                                array[n] = x;
                                counter_numbers++;
                                counter_all++;
                                upHeap(n,array);
                            }
                        }
                    }
                }
                else if(order.equals("g")) {
                    
                    int number = in.nextInt();
                    if(array[0] != null){
                        if(number < array[0].occurancy) {
                            array[0].occurancy -= number;
                            counter_all -= number;
                            System.out.println(array[0].value + " " + number);
                            downHeap(0, counter_numbers, array);
                        }
                        else {
                            System.out.println(array[0].value + " " + array[0].occurancy);
                            counter_all -= array[0].occurancy;
                            array[0].occurancy = 0;
                            DeleteMax(counter_numbers, array);
                            counter_numbers--;
                            array[counter_numbers] = null;
                        }
                    } else {
                        System.out.println("0 0");
                    }
                }
                order = in.next();
            }
            if(array[0] == null)
                System.out.println("0 0");
            else {
                HeapSort(counter_numbers, array);
                for(int i = counter_numbers-1; i >= 0; i--) {
                    System.out.print(array[i].value + " " + array[i].occurancy + " ");
                }
                System.out.println();
            }
            
        }

    }
    
    static class Element {
        int occurancy = 0;
        int value;
        
        Element(int value_) {
            value = value_;
        }
    }
    
    static public boolean conditionHeap(Element k, Element m) {
        if(k.occurancy < m.occurancy) 
            return true;
        else if(k.occurancy > m.occurancy)
            return false;
        
        return k.value < m.value;
    }
    
    static void upHeap (int k, Element[] tab) {
        int i = (k-1)/2;
        Element tmp = tab[k];
        while(k > 0 && conditionHeap(tab[i],tmp)) {
            tab[k] = tab[i];
            k = i;
            i = (i-1)/2;
        }
        tab[k] = tmp;
    }
    
    static void downHeap(int k, int n, Element[] tab) {
        int j; Element tmp = tab[k];
        while(k < n/2) {
            j = 2*k + 1;
            if(j < n-1 && conditionHeap(tab[j], tab[j+1])) j++;
            if(!conditionHeap(tmp, tab[j])) break;
            tab[k] = tab[j];
            k = j;
        }
        tab[k] = tmp;
    }
    
    static void DeleteMax(int n, Element[] tab) {
        n--;
        tab[0] = tab[n];
        downHeap(0, n, tab);
    }
    
    static void HeapSort(int n, Element[] tab) {
        for(int k = (n-2)/2; k >= 0; k--)
            downHeap(k, n, tab);
        while(n > 0) {
            swap(0, n-1, tab);
            n--;
            downHeap(0, n, tab);
        }
    }
    
    static void swap(int k, int m, Element[] tab) {
        Element tmp = tab[k];
        tab[k] = tab[m];
        tab[m] = tmp;
    }
}

/*
test.in
3
5 15
g 2
i 2 1 1
g 1
i 3 2 3 4
g 2
i 7 4 4 4 4 4 1 3
g 3
i 9 2 6 1 6 7 7 7 7 2
g 2
s
8 15
i 10 1 2 3 4 5 6 7 8 9 10
g 2
g 1
g 5
i 10 1 1 1 1 1 1 1 1 4 4
g 3
i 15 1 1 1 1 1 1 1 1 1 1 1 1 3 3 3
g 30
g 2
i 11 3 3 3 5 5 5 5 10 10 2 1
s
10 15
i 10 5 5 5 5 5 10 9 8 7 6
g 5
g 5
g 5
g 5
g 5
g 5
g 1
i 3 1 1 1
g 2
g 1
i 5 4 3 2 1 1
g 1
g 1
g 1 
g 1
g 1
i 16 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
s

test.out
0 0
1 1
4 1
4 3
2 2
1 3 6 2 4 2 3 2 2 1
8 1
7 1
6 1
1 3
1 9
4 2
5 5 3 4 10 2 2 2 4 1 1 1
5 5
10 1
9 1
8 1
7 1
6 1
0 0
1 2
1 1
1 1
4 1
3 1
2 1
1 1
10 1 9 1 8 1 7 1 6 1 5 1 4 1 3 1 2 1 1 1
*/
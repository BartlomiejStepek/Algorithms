/*** Bartlomiej Stepek - Selection ***/
package selection;
import java.util.Scanner; 
public class Selection {

    public static void InsertionSort(int[] tab, int n, int L) {
        int j, v;
        for (int i = L+1;  i < L+n ; i++)   {        
            v = tab[i];                        
            j = i-1;                       
            while ( j >= L  &&  v < tab[j] ) 
            {                                         
                tab[j+1] = tab[j];            
                j-- ;       
            }                                       
            tab[j+1] = v ;                           
        }
    }
    
    public static void swap(int[] tab, int i, int j)  {  
        int tmp = tab[i];  tab[i] = tab[j]; tab[j] = tmp;               
    }
    
    static int Partition(int []tab, int L, int R, int M) {                                                        
        int i = L, j = R;
        while(true) {
            while(tab[i]<M)
                i++;
            
            while(tab[j]>M)
                j--;
            
            if(i < j) {
                if(tab[i] == tab[j])
                    j--;
                swap(tab, i, j);
            }
            else 
                break;
        }
        return i;  
    }
    
    public static int Select2 (int[] array, int n, int L){
        int number_of_sub_array = n/5;
        int counter = L;
        int a = number_of_sub_array * 5;
        for(int i = 0; i < number_of_sub_array; i++)
            InsertionSort(array, 5, L+i*5);
        if(a != n)
            InsertionSort(array, n-a, L+a);
         
        for(int i = 0; i < number_of_sub_array; i++) {
            swap(array, counter, 2+5*i+L);
            counter++;
        }
        if(a != n) {
            swap(array, counter, a + ((n-a)/2) + L);
            counter++;
        }
        
        
        int M = 0;
        counter -= L;
        if(counter >= 50)
            return Select2(array, counter, L);
        else {
            InsertionSort(array, counter, L);
            M = array[counter/2 + L];
        }
        return M;
    }
    
    public static Scanner in= new Scanner(System.in);
    public static void main(String[] args) {

        int number_of_sets = in.nextInt(); 
        for(int a = 0; a < number_of_sets; a++) {
            int length_array = in.nextInt(); 
            int[] array = new int [length_array];
            for(int i = 0; i < length_array; i++)
                array[i] = in.nextInt(); 
            int number_of_queries = in.nextInt();  
            for(int i = 0; i < number_of_queries; i++) {
                
                int x = in.nextInt();
                int M = 0, L = 0, R = length_array -1, index;
                if(x < 1 || x > length_array)
                    System.out.println(x + " lack");
                else {
                    if(length_array < 50) {  
                        InsertionSort(array, length_array, 0);
                        M = array[x-1];
                    } else {
                        while(true) {
                            M = Select2(array, (R-L) + 1, L); 
                            index = Partition(array, L, R, M);   
                            if( x-1 < index ) 
                                R = index - 1;
                            else if( x-1 > index ) 
                                L = index + 1;
                            else { 
                                M = array[x-1];
                                break;
                            }
                        }
                    }
                    System.out.println(x + " " + M);
                }
            }
        }
    }   
}

/*
test.in

4
5  
1 2 3 4 5 
3  
1 2 3 
5  
5 3 4 4 3
5 
2 5 1 3 4
10
1 1 1 1 1 1 1 1 1 1
5
1 10 0 -1 11
60
70 92 57 44 83 10 4 33 20 20 41 35 81 58 34 81 16 17 4 2 66 69 61 43 59 29 78 96 31 9 13 65 90 75 99 48 27 79 92 45 16 59 32 74 33 4 54 56 46 39 18 82 25 26 1 92 51 93 11 36
12
-1 0 60 59 39 28 48 18 5 29 48 10
55
39 28 17 37 3 84 28 57 19 10 83 17 39 375 474 92 829 27 4737 20 1298 248 1294 727 717 37 17 64 83 30 12847 8838 477 24 81 8 1 85 81 40 722 828 74 62 77 55 33 88 99 50 19 38 26 17 50
59
-20 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 100

test.out
1 1 
2 2 
3 3 
2 3 
5 5 
1 3 
3 4 
4 4 
1 1
10 1
0 brak 
-1 brak 
11 brak
12
-1 0 60 59 39 28 48 18 5 29 48 10
-1 brak
0 brak
60 99
59 96
39 59
28 41
48 78
18 27
5 4
29 43
48 78
10 16
-20 brak
0 brak
1 1
2 3
3 8
4 10
5 17
6 17
7 17
8 17
9 19
10 19
11 20
12 24
13 26
14 27
15 28
16 28
17 30
18 33
19 37
20 37
21 38
22 39
23 39
24 40
25 50
26 50
27 55
28 57
29 62
30 64
31 74
32 77
33 81
34 81
35 83
36 83
37 84
38 85
39 88
40 92
41 99
42 248
43 375
44 474
45 477
46 717
47 722
48 727
49 828
50 829
51 1294
52 1298
53 4737
54 8838
55 12847
56 brak
100 brak
*/
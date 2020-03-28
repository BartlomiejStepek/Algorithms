/*** Bartlomiej Stepek - Linked List as list of trains ***/
package linkedlist;
import java.util.Scanner;

public class LinkedList {

    static class Locomotive {
        String name;
        Locomotive next;
        Car first;
        Car last;
        
        Locomotive(String _name, String _car) {
            Car car = new Car(_car);
            name = _name;
            next = null;
            first = car;
            last = car;
        }
    }

    static class Car {
        String name;
        Car next;
        Car prev;
        
        Car(String _name) {
            name = _name;
            next = null;
            prev = null;
        }
    }
    
    static class ListOfTrains {
        Locomotive first;
        Locomotive last;
        
        public ListOfTrains() {
            first = null;
            last = null;
        }
    }
    
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        int number_of_sets = sc.nextInt(); 
        for(int i = 0; i < number_of_sets; i++) {
            
            int number_of_commands = sc.nextInt(); 
            sc.nextLine();
            ListOfTrains list = new ListOfTrains();
            for(int j = 0; j < number_of_commands; j++) {
                
                String command = sc.nextLine();
                String words[] = command.split(" ");
                
                if(words.length == 2) {
                    
                    if(words[0].equals("Display")){
                        Locomotive loc = list.first;
                        
                        while(!(loc.name.equals(words[1]))) {
                            loc = loc.next;
                        }
                        
                        System.out.print(loc.name + ": ");

                        Car one_before = null;
                        Car car = loc.first;
                        
                        while(car != null) { 
                            System.out.print(car.name + " ");
                            
                            if(car.prev == one_before) { 
                                one_before = car;
                                car = car.next;
                            }
                            else if(car.next == one_before){
                                one_before = car;
                                car = car.prev;
                            }
                        }
                        
                        System.out.println();

                    }
                    else if (words[0].equals("Reverse")) {
                        Locomotive loc = list.first;
                        
                        while(!(loc.name.equals(words[1]))) {
                            loc = loc.next;
                        }

                        Car car;
                        car = loc.first;
                        loc.first = loc.last;
                        loc.last = car;
                    }
                }
                
                else if(words.length == 3) {
                    
                    switch (words[0]) {
                        case "New":
                            {
                                Locomotive loc = new Locomotive(words[1], words[2]);
                                if(list.first == null)
                                    list.first = loc;
                                else
                                    list.last.next = loc;
                                list.last = loc;
                                break;
                            }
                        case "InsertFirst":
                            {
                                Locomotive loc = list.first;
                                while(!(loc.name.equals(words[1]))) { 
                                    loc = loc.next;
                                }       Car car = new Car(words[2]);
                                if(loc.first.prev == null) {
                                    loc.first.prev = car;
                                    car.next = loc.first;
                                    car.prev = null;
                                }
                                else {              
                                    loc.first.next = car;
                                    car.prev = loc.first;
                                    car.next = null;
                                }       loc.first = car;
                                break;
                            }
                        case "InsertLast":
                            {
                                Locomotive loc = list.first;
                                while(!(loc.name.equals(words[1]))) { 
                                    loc = loc.next;
                                }       Car car = new Car(words[2]);
                                if(loc.last.next == null) { 
                                    loc.last.next = car;
                                    car.prev = loc.last;
                                    car.next = null;
                                }
                                else {                    
                                    loc.last.prev = car;
                                    car.next = loc.last;
                                    car.prev = null;
                                }       loc.last = car;
                                break;
                            }
                        case "Union":
                            Locomotive tmp_1 = list.first;
                            Locomotive tmp_2 = list.first;
                            Locomotive one_before_2 = null;
                            while(tmp_1 != null && tmp_2 != null) {
                                if(tmp_1.name.equals(words[1]) && tmp_2.name.equals(words[2])) {
                                    
                                    if(tmp_1.last.next == null) 
                                        tmp_1.last.next = tmp_2.first;
                                    else
                                        tmp_1.last.prev = tmp_2.first;
                                    
                                    if(tmp_2.first.prev == null)
                                        tmp_2.first.prev = tmp_1.last;
                                    else
                                        tmp_2.first.next = tmp_1.last;
                                    
                                    tmp_1.last = tmp_2.last;   
                                    
                                    if(one_before_2 != null)   
                                        one_before_2.next = tmp_2.next; 
                                    else 
                                        list.first = tmp_2.next;
                                    
                                    if(list.last == tmp_2) { 
                                        list.last = one_before_2;
                                        one_before_2.next = null;
                                    }
                                    break;  
                                }
                                
                                else if (tmp_1.name.equals(words[1]) && !(tmp_2.name.equals(words[2]))) {
                                    one_before_2 = tmp_2;
                                    tmp_2 = tmp_2.next;
                                }
                                else if (!(tmp_1.name.equals(words[1])) && tmp_2.name.equals(words[2]))
                                    tmp_1 = tmp_1.next;
                                else {
                                    tmp_1 = tmp_1.next;
                                    one_before_2 = tmp_2;
                                    tmp_2 = tmp_2.next;
                                }
                            }   break;
                        case "DelFirst":
                            {
                                Locomotive loc = list.first;
                                Locomotive one_before = null;
                                while(!(loc.name.equals(words[1]))) { 
                                    one_before = loc;
                                    loc = loc.next;
                                }       Locomotive new_loc = new Locomotive(words[2], loc.first.name);
                                if(loc.first == loc.last) { 
                                    if(one_before != null) { 
                                        one_before.next = loc.next;
                                        if(list.last == loc) { 
                                            list.last = one_before;
                                        }
                                    }
                                    else { 
                                        list.first = loc.next;
                                    }
                                }
                                else {
                                    if(loc.first.prev == null) { 
                                        if(loc.first.next.prev == loc.first) 
                                            loc.first.next.prev = null;   
                                        else
                                            loc.first.next.next = null;
                                        loc.first = loc.first.next;
                                    }
                                    else {              
                                        if(loc.first.prev.next == loc.first)
                                            loc.first.prev.next = null;
                                        else
                                            loc.first.prev.prev = null;
                                        loc.first = loc.first.prev;
                                    }
                                }       if(list.first == null) {
                                    list.first = new_loc;
                                    list.last = new_loc;
                                }
                                else
                                    list.last.next = new_loc;
                                list.last = new_loc;
                                break;
                            }
                        case "DelLast":
                            {
                                Locomotive loc = list.first;
                                Locomotive one_before = null;
                                while(!(loc.name.equals(words[1]))) { 
                                    one_before = loc;
                                    loc = loc.next;
                                }       Locomotive new_loc = new Locomotive(words[2], loc.last.name);
                                if(loc.first == loc.last) { 
                                    if(one_before != null) { 
                                        one_before.next = loc.next;
                                        if(list.last == loc) {
                                            list.last = one_before;
                                        }
                                    }
                                    else {
                                        list.first = loc.next;
                                    }
                                }
                                else {
                                    if(loc.last.next == null) { 
                                        if(loc.last.prev.next == loc.last)  
                                            loc.last.prev.next = null;   
                                        else
                                            loc.last.prev.prev = null;
                                        loc.last = loc.last.prev;
                                    }
                                    else {                  
                                        if(loc.last.next.next == loc.last)
                                            loc.last.next.next = null;
                                        else
                                            loc.last.next.prev = null;
                                        loc.last = loc.last.next;
                                    }
                                }       if(list.first == null) {
                                    list.first = new_loc;
                                    list.last = new_loc;
                                }
                                else
                                    list.last.next = new_loc;
                                list.last = new_loc;
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
        }
    }
}

/***
    Example Input
    3
    21
    New T1 W1
    InsertLast T1 W2
    Display T1
    InsertFirst T1 W0
    Display T1
    DelFirst T1 T2
    Display T1
    Display T2
    DelLast T1 T3
    Display T1
    Display T3
    New T4 Z1
    InsertLast T4 Z2
    Reverse T4
    Display T4
    Union T3 T4
    Display T3
    Union T3 T2
    Display T3
    Reverse T3
    Display T3
    15
    New T1 W1
    InsertLast T1 W2
    InsertFirst T1 W0
    Display T1
    InsertFirst T1 Z0
    InsertLast T1 W3
    InsertFirst T1 W4
    Display T1
    InsertLast T1 W5
    InsertLast T1 W6
    InsertFirst T1 W7
    InsertFirst T1 W8
    Display T1 
    Reverse T1
    Display T1 
    20
    New T1 W0
    InsertLast T1 W1
    InsertLast T1 W2
    New T2 Z0
    InsertLast T2 Z1
    InsertLast T2 Z2
    Reverse T2
    Reverse T1
    Union T1 T2
    Display T1
    New T3 K1
    InsertFirst T3 K0
    InsertLast T3 K2
    Union T3 T1
    Display T3
    DelLast T3 T4
    DelFirst T3 T5
    Display T3
    Display T4
    Display T5

    Example output
    T1: W1 W2
    T1: W0 W1 W2
    T1: W1 W2
    T2: W0
    T1: W1
    T3: W2
    T4: Z2 Z1
    T3: W2 Z2 Z1
    T3: W2 Z2 Z1 W0
    T3: W0 Z1 Z2 W2 
    T1: W0 W1 W2 
    T1: W4 Z0 W0 W1 W2 W3 
    T1: W8 W7 W4 Z0 W0 W1 W2 W3 W5 W6 
    T1: W6 W5 W3 W2 W1 W0 Z0 W4 W7 W8
    T1: W2 W1 W0 Z2 Z1 Z0 
    T3: K0 K1 K2 W2 W1 W0 Z2 Z1 Z0 
    T3: K1 K2 W2 W1 W0 Z2 Z1
    T4: Z0
    T5: K0 
***/
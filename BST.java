/*** Bartlomiej Stepek - BST ***/
package bst;
import java.util.Scanner;

public class BST {

    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        
        int number_of_sets = in.nextInt();
        for(int a = 0; a < number_of_sets; a++) {
            
            System.out.println("SET: " + (a+1));
            int quantity = in.nextInt(); 
            int[] array = new int[quantity];
            String order = in.next();
            for(int i = 0; i < quantity; i++) 
                array[i] = in.nextInt(); 
            int number_of_action = in.nextInt();  
            Index index1 = new Index(0); 
            Index index2 = new Index(array.length - 1);
            Node head;
            
            if(order.equals("PREORDER")) {
                
                TreeBST_Preorder tree =  new TreeBST_Preorder();
                head = tree.createTree(array, index1, 0, array.length - 1);
            } else {
                
                TreeBST_Postorder tree = new TreeBST_Postorder();
                head = tree.createTree(array, index2, -2147483647, 2147483647);
            }
            for(int i = 0; i < number_of_action; i++) {
                String action = in.next();
                int x=0; if(action.equals("PARENT") || action.equals("INSERT") || action.equals("DELETE") 
                                            || action.equals("SUCCESSOR") || action.equals("PREDECESSOR"))
                            x = in.nextInt();
                switch (action) {
                    case "PREORDER":
                        PreOrder(head);
                        break;
                    case "INORDER":
                        InOrder(head);
                        break;
                    case "POSTORDER":
                        PostOrder(head);
                        break;
                    case "LEVELORDER":
                        LevelOrder(head);
                        break;
                    case "PARENT":
                        Node node = Parent(head,x);
                        if(node != null) System.out.println("PARENT " + x + ": " + node.info);
                        else System.out.println("PARENT " + x + ": LACK");
                        break;
                    case "INSERT":
                        Insert(head, x);
                        break;
                    case "DELETE":
                        Delete(head,x);
                        break;
                    case "SUCCESSOR":
                        Successor(head,x);
                        break;
                    case "PREDECESSOR":
                        Predecessor(head,x);
                        break;
                    default:
                        break;
                }
            }   
        }
    }
    
    static class Node {    
        public int info;
        public Node left;  
        public Node right;   
        public Node(int info) {         
            this.info  = info;
            this.left  = null;         
            this.right = null;    
        }
    }
    
    static class Index {
        int index;
        Index(int a) {
            index = a;
        } 
    }
    
    static class TreeBST_Preorder {
        
        Node createTree(int[] tab, Index id, int L, int R) { 
        
            if (id.index >= tab.length || L > R)
                return null;  

            Node node = new Node(tab[id.index]); 
            id.index += 1; 

            if (L == R)
                return node; 

            int i;
            for (i = L; i <= R; i++)
                if (tab[i] > node.info)
                    break; 

            node.left = createTree(tab, id, L + 1,  i - 1); 
            node.right = createTree(tab, id, i, R); 

            return node; 
        } 
    }   
    
    static class TreeBST_Postorder {

        Node createTree(int tab[], Index id, int L, int R) { 

            if (id.index < 0) 
                return null; 
            
            int value = tab[id.index];

            Node node = null;
            
            if( value > L && value < R) { 

                node = new Node(value);
                id.index -= 1; 

                if (id.index >= 0) { 

                    node.right = createTree(tab, id, value, R); 
                    node.left = createTree(tab, id, L, value); 
                } 
            }
            return node;
        }  
    }

    // 1.Print 2. Left 3. Right
    public static void PreOrder(Node head) {

        System.out.print("PREORDER: ");
        if(head == null) { System.out.println(); return; }
        Stack stack = new Stack(1000); 
        stack.push(head); 
  
        while (!stack.isEmpty()) { 

            Node node = stack.top();
            System.out.print(node.info + " "); 
            stack.pop(); 

            if (node.right != null)  
                stack.push(node.right); 
            if (node.left != null) 
                stack.push(node.left);
        }
        System.out.println();
    } 
    
    //1. Left 2. Right 3. Print
    public static void InOrder(Node head) {
        System.out.print("INORDER: ");                
        if(head == null) { System.out.println(); return; }
        Stack stack = new Stack(1000);
        Node node = head;
        while(node != null || !stack.isEmpty()) {
            
            while (node !=  null) { 
                stack.push(node);
                node = node.left; 
            } 
  
            node = stack.top(); 
            stack.pop(); 
            System.out.print(node.info + " ");
            node = node.right;
        }
        System.out.println();
    }
    
    //1. Left 2. Right 3. Print
    public static void PostOrder(Node head) {
        System.out.print("POSTORDER: ");
        if(head == null) { System.out.println(); return; }
        Stack stack1 = new Stack(1000);
        Stack stack2 = new Stack(1000);
 
        stack1.push(head); 

        while (!stack1.isEmpty()) { 

            Node node = stack1.top(); 
            stack1.pop();
            stack2.push(node); 

            if (node.left != null)
                stack1.push(node.left); 
            if (node.right != null) 
                stack1.push(node.right); 
        } 

        while (!stack2.isEmpty()) { 
            Node node = stack2.top();   
            stack2.pop();
            System.out.print(node.info + " "); 
        }
        System.out.println();
    }
    
    public static void LevelOrder(Node head) {
        System.out.print("LEVELORDER: ");
        if(head == null) { System.out.println(); return; }
        Queue queue = new Queue(1000);
        queue.enQueue(head);
        while(!(queue.isEmpty())) {
            Node node = queue.deQueue();
            System.out.print(node.info + " "); 
            if(node.left != null)
                queue.enQueue(node.left);  
            if(node.right != null)
                queue.enQueue(node.right); 
        }
        System.out.println();
    }
    
    public static Node Parent(Node head, int x) {
        Stack stack = new Stack(1000); 
        stack.push(head); 
  
        while (!stack.isEmpty()) {

            Node node = stack.top();
            stack.pop(); 

            if (node.right != null) { 
                if(node.right.info == x)
                    return node;              
                stack.push(node.right); 
            } 
            if (node.left != null) { 
                if(node.left.info == x)
                    return node;
                stack.push(node.left); 
            } 
        }
        return null;
    }
    
    public static void Insert(Node head, int x) {
        Node node = head;
        Node new_node = new Node(x);
        while(true) {
            if(x > node.info) {        
                if(node.right == null) {  
                    node.right = new_node;
                    break;
                } else 
                    node = node.right;
            } else if(x < node.info) {
                if(node.left == null) { 
                    node.left = new_node;
                    break;
                } else 
                    node = node.left;   
            } else  
                return;
        }
    }
    
    public static void Delete(Node head, int x) {
 
        Stack stack = new Stack(1000); 
        stack.push(head); 
  
        while (!stack.isEmpty()) { 

            Node node = stack.top();
            stack.pop(); 
            if(node.info == x) {
                if(node.left != null && node.right != null) { 
                    Node node2 = node.right; 
                    while(true) {            
                        if(node2.left != null)
                            node2 = node2.left;
                        else 
                            break;
                    }
                    Node parent = Parent(head,node2.info); 
                    if(node2.right != null) { 
                        if(parent.left == node2)
                            parent.left = node2.right;
                        else
                            parent.right = node2.right;
                    }
                    else { 
                        if(parent.left == node2)
                            parent.left = null;
                        else 
                            parent.right = null;
                    }

                    node.info = node2.info; 
                    return;
                    
                    
                } else if(node.left == null && node.right != null) {
                    Node parent = Parent(head,x);
                    if(parent != null)  {
                        if(node.info > parent.info)
                            parent.right = node.right;
                        else
                            parent.left = node.right;
                    }
                    else { 
                        node.info = node.right.info; 
                        if(node.right.left != null)
                            node.left = node.right.left;
                        else 
                            node.left = null;
                        if(node.right.right != null) 
                            node.right = node.right.right;
                        else 
                            node.right = null;
                    }
                    return;
                                        
                } else if(node.left != null && node.right == null) {
                    Node parent = Parent(head,x);
                    if(parent != null) {  
                        if(node.info > parent.info)
                            parent.right = node.left;
                        else
                            parent.left = node.left;
                    }
                    else {
                        node.info = node.left.info;
                        if(node.left.right != null) 
                            node.right = node.left.right;
                        else 
                            node.right = null;
                        if(node.left.left != null)
                            node.left = node.left.left;
                        else 
                            node.left = null;
                    }
                    return;
                    
                    
                } else {
                    Node parent = Parent(head,x); 
                        if(node.info > parent.info)
                            parent.right = null;
                        else
                            parent.left = null;
                    return;
                
                }
            }
            
            else {
                if (node.right != null) 
                    stack.push(node.right); 
                if (node.left != null) 
                    stack.push(node.left); 
            }
        }
    }
    
    public static void Successor(Node head, int x) {
        Stack stack = new Stack(1000); 
        stack.push(head); 
  
        while (!stack.isEmpty()) { 

            Node node = stack.top();
            stack.pop(); 

            if(node.info == x) { 
                Node node2;
                if(node.right != null) node2 = node.right; 
                else { 
                    Node parent = Parent(head,node.info);  
                    if(parent != null) {
                        if(parent.left == node) { 
                            System.out.println("SUCCESSOR " + x + ": " + parent.info);
                            return;
                        }
                        while(true) {
                            if(parent == head) {
                                System.out.println("SUCCESSOR " + x + ": LACK");
                                return;
                            }
                            Node parent2 = Parent(head,parent.info); 
                            if(parent2.left == parent) {
                                System.out.println("SUCCESSOR " + x + ": " + parent2.info);
                                return;
                            }
                            else 
                                parent = parent2;
                        }
                    } else break;
                }
                while(true) {
                    if(node2.left != null) 
                        node2 = node2.left;
                    else 
                        break;
                }
                System.out.println("SUCCESSOR " + x + ": " + node2.info);
                return;
            }
            else{
                if (node.right != null) { 
                    stack.push(node.right); 
                } 
                if (node.left != null) { 
                    stack.push(node.left); 
                } 
            }
        }        
        System.out.println("SUCCESSOR " + x + ": LACK"); 
    }
    
    public static void Predecessor(Node head, int x) {
        Stack stack = new Stack(1000); 
        stack.push(head); 
  
        while (!stack.isEmpty()) { 

            Node node = stack.top();
            stack.pop(); 

            if(node.info == x) {
                Node node2;
                if(node.left != null) node2 = node.left;
                else { 
                    Node parent = Parent(head,node.info);
                    if(parent != null) {
                        if(parent.right == node) {
                            System.out.println("PREDECESSOR " + x + ": " + parent.info);
                            return;
                        }
                        while(true) {
                            if(parent == head) { 
                                System.out.println("PREDECESSOR " + x + ": BRAK");
                                return;
                            }
                            Node parent2 = Parent(head,parent.info);
                            if(parent2.right == parent) { 
                                System.out.println("PREDECESSOR " + x + ": " + parent2.info);
                                return;
                            }
                            else
                                parent = parent2;
                        }
                    } else break;
                }
                while(true) { 
                    if(node2.right != null)
                        node2 = node2.right;
                    else
                        break;
                }
                System.out.println("PREDECESSOR " + x + ": " + node2.info);  
                return;
            }
            else {
                if (node.right != null) { 
                    stack.push(node.right); 
                } 
                if (node.left != null) { 
                    stack.push(node.left); 
                } 
            }
        }
        System.out.println("PREDECESSOR " + x + ": LACK"); 
    }

    static class Queue {    
        private int maxSize;    
        private Node[] Elem;    
        private int front;   
        private int rear;                                    

        private int  Addone (int i) {      
               return ( i+1) % maxSize;
        } 

        public Queue(int size){               
            maxSize = size;    
            Elem = new Node[maxSize];       
            front = 0;      
            rear = 0;       
        } 

        public void enQueue(Node x) {      
            if (isFull()) 
                System.out.println ("queue is full");      
            else {                 
                Elem[rear] = x;
                rear = Addone(rear);
            }
        }                      
        public Node deQueue() {         
            if(isEmpty()){                        
                System.out.println("queue is empty");
                return null;                    
            }                      
            else {                
                Node temp = Elem[front];                   
                front = Addone(front);                    
                return temp;   
            }
        }

        public Node Front() {         
            if(isEmpty()){                        
                System.out.println("queue is empty");
                return null;                       
            }                    
            else return Elem[front];       
        }

        public boolean isEmpty() {            
            return (rear == front);       
        }

        public boolean isFull() {         
            return (Addone(rear) == front);       
        } 
    }
    
    static class Stack{
        private int maxSize;
        public Node[] Elem;
        private int top;
        
        public Stack(int size) {
            maxSize = size;
            Elem = new Node[maxSize];
            top = -1;
        }
        
        public void push(Node x){
            Elem[top + 1] = x;
            top++;
        }
        
        public void pop(){
            Elem[top] = null;
            top--;
        }
        
        public Node top(){
            return Elem[top];
        }
        
        public boolean isEmpty(){
            return top == -1;
        }
        
        public boolean isFull(){
            return top+1 == maxSize;
        }
    }
}
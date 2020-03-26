/*** Bartlomiej Stepek - InfixPostfixConverter ***/
package infixpostfixconverter;
import java.util.Scanner;

public class InfixPostfixConverter {
    
    public static boolean onp_machine(char[] array) {
        int counter_operand = 0;
        int counter_operator = 0;
        for(int i = 5; i < array.length; i++) {
                if(array[i] >= 97 && array[i] <= 122)
                    counter_operand++;

                else if(array[i] == '<' || array[i] == '>' || array[i] == '+' || array[i] == '-' ||
                        array[i] == '*' || array[i] == '/' || array[i] == '%' || array[i] == '=' ||
                        array[i] == '^')
                    counter_operator++;
        }
        return counter_operand == counter_operator + 1;
        
    }
    
    public static boolean inf_machine(char[] array) {
        boolean is_operator = false;
        int state = 0;
        int counter_operand = 0;
        int counter_operator = 0;
        char first_bracket = '(';
        char last_bracket = ')';
        int counter_first_bracket = 0;
        int counter_last_bracket = 0;
        for(int i = 5; i < array.length; i++) {
            if(array[i] >= 97 && array[i] <= 122) {
                if(state == 0 || state == 2) state = 1;
                else { state = -1; break; }
                counter_operand++;
            }
            else if(array[i] == '<' || array[i] == '>' || array[i] == '+' || array[i] == '-' ||
                array[i] == '*' || array[i] == '/' || array[i] == '%' || array[i] == '=' ||
                array[i] == '^'){
                        if(state == 1) state = 0;
                        else { state = -1; break; }
                        if(array[i] == '=' && is_operator == true) { state = -1; break; }
                        else if(array[i] != '=') is_operator = true;
                        counter_operator++;
            }
            else if(array[i] == '~') {
                if(state == 0 || state == 2) state = 2;
                else { state = -1; break; }
            }
            else if(array[i] == '('){
                if(state == 0 || state == 2) state = 0;
                else { state = -1; break; }
                if(counter_first_bracket == 0 && counter_last_bracket == 0) first_bracket = '(';
                last_bracket = '(';
                counter_first_bracket++;
            }
            else if(array[i] == ')'){
                if(state == 1) state = 1;
                else { state = -1; break; }
                if(counter_first_bracket == 0 && counter_last_bracket == 0) first_bracket = ')';
                last_bracket = ')';
                counter_last_bracket++;
            }
        }
        return (state == 1) && (counter_first_bracket == counter_last_bracket) &&
                first_bracket == '(' && last_bracket == ')' && (counter_operand == counter_operator + 1);
    }
    
    public static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        int number_of_sets = sc.nextInt();
        sc.nextLine();
        
        for(int i = 0; i < number_of_sets; i++) {
            String s = sc.nextLine();
            
            char array[] = s.toCharArray();
            String result = "";
            
            if(array[0] == 'I' && array[1] == 'N' && array[2] == 'F' && array[3] == ':' && array[4] == ' ') {
                boolean INF_is_ok = inf_machine(array);
                if(INF_is_ok == false)
                    System.out.println("ONP: error");

                else{
                    StackArray stack = new StackArray(256);    
                    for(int j = 5; j < array.length; j++) {

                            if(array[j] >= 97 && array[j] <= 122)
                                result += array[j];

                            else if(array[j] == '=' || array[j] == '^') {
                                for(int k = stack.top; k >= 0; k--) {
                                    if(Priority(array[j]) < Priority(stack.Elem[k])){
                                                result += stack.Elem[k];
                                                stack.pop();
                                    }
                                    else if(stack.Elem[k] == '(')
                                        break;
                                }
                                stack.push(array[j]);
                            }
                            else if(array[j] == '<' || array[j] == '>' || array[j] == '+' || array[j] == '-' ||
                                    array[j] == '*' || array[j] == '/' || array[j] == '%'){
                                for(int k = stack.top; k >= 0; k--) {
                                    if(Priority(array[j]) <= Priority(stack.Elem[k])){
                                                result += stack.Elem[k];
                                                stack.pop();
                                    }
                                    else if(stack.Elem[k] == '(')
                                        break;
                                }
                                stack.push(array[j]);
                            }                          
                            else if(array[j] == '~' || array[j] == '(')
                                stack.push(array[j]);

                            else if(array[j] == ')'){
                                for(int k = stack.top; k>=0; k--){
                                    if(stack.Elem[stack.top] != '('){
                                        result += stack.Elem[stack.top];
                                        stack.pop();
                                    }
                                    else{
                                        stack.pop();
                                        break;
                                    }
                                }
                            }
                        }
                    while(stack.top > -1) {
                        result += stack.Elem[stack.top];
                        stack.pop();
                    }

                    System.out.println("ONP: " + result);
                }
            }
            
            else if(array[0] == 'O' && array[1] == 'N' && array[2] == 'P' && array[3] == ':' && array[4] == ' ') {
                boolean ONP_is_ok = onp_machine(array);
                if(ONP_is_ok == false)
                    System.out.println("INF: error");
                
                else{
                    String actual_result= "";
                    StackArrayString stack = new StackArrayString(256);
                    StackArrayPriority stack_pr = new StackArrayPriority(256);
                    for(int j = 5; j < array.length; j++) {
                        if(array[j] >= 97 && array[j] <= 122) {
                            stack.push(String.valueOf(array[j]));
                            stack_pr.push(Priority(array[j]));
                        }
                        else if((array[j] == '<' || array[j] == '>' || array[j] == '+' || array[j] == '-' ||
                            array[j] == '*' || array[j] == '/' || array[j] == '%' || array[j] == '=' ||
                            array[j] == '^' || array[j] == '~') && stack.top >= 0 && stack_pr.top >= 0){

                                int actual_priority = Priority(array[j]);
                                int expression_priority = stack_pr.Elem[stack_pr.top];
                                stack_pr.pop();
                                String expression1 = stack.Elem[stack.top];
                                stack.pop();

                                if(array[j] != '~'){
                                    if(array[j] == '=' || array[j] == '^')
                                        if(actual_priority > expression_priority)
                                            expression1 = "(" + expression1 + ")";

                                    else
                                        if(actual_priority >= expression_priority)
                                            expression1 = "(" + expression1 + ")";

                                    expression_priority = stack_pr.Elem[stack_pr.top];
                                    stack_pr.pop();
                                    String expression2 = stack.Elem[stack.top];
                                    stack.pop();
                                    if(array[j] == '=' || array[j] == '^') 
                                        if(actual_priority >= expression_priority)
                                            expression2 = "(" + expression2 + ")";

                                    else
                                        if(actual_priority > expression_priority)
                                            expression2 = "(" + expression2 + ")";

                                    actual_result = expression2 + array[j] + expression1;
                                }
                                else{
                                    if(actual_priority > expression_priority)
                                        expression1 = '(' + expression1 + ')';
                                    actual_result = array[j] + expression1;
                                }
                                stack_pr.push(actual_priority);
                                stack.push(actual_result);
                        }
                    }
                    System.out.println("INF: " + actual_result);
                    }
                }
        }
    }
    
    public static int Priority(char x){
        if(x == '=') return 0;
        else if(x == '>' || x == '<') return 1;
        else if(x == '+' || x == '-') return 2;
        else if(x == '*' || x == '/' || x == '%') return 3;
        else if(x == '^') return 4;
        else if(x == '~') return 5;
        else if(x >= 97 && x <= 122) return 6;
        else return -1;
    }

    static class StackArray {
        private int maxSize;
        public char[] Elem;
        private int top;
        
        public StackArray(int size) {
            maxSize = size;
            Elem = new char[maxSize];
            top = -1;
        }
        
        public void push(char x){
            Elem[top + 1] = x;
            top++;
        }
        
        public void pop(){
            Elem[top] = ' ';
            top--;
        }
        
        public char top(){
            return Elem[top];
        }
        
        public boolean isEmpty(){
            return top == -1;
        }
        
        public boolean isFull(){
            return top+1 == maxSize;
        }
    }

    static class StackArrayString {
        private int maxSize;
        public String[] Elem;
        private int top;
        
        public StackArrayString(int size) {
            maxSize = size;
            Elem = new String[maxSize];
            top = -1;
        }
        
        public void push(String x){
            Elem[top + 1] = x;
            top++;
        }
        
        public void pop(){
            Elem[top] = "";
            top--;
        }
        
        public String top(){
            return Elem[top];
        }
        
        public boolean isEmpty(){
            return top == -1;
        }
        
        public boolean isFull(){
            return top+1 == maxSize;
        }
    }
    
    static class StackArrayPriority{
        private int maxSize;
        public int[] Elem;
        private int top;
        
        public StackArrayPriority(int size) {
            maxSize = size;
            Elem = new int[maxSize];
            top = -1;
        }
        
        public void push(int x){
            Elem[top + 1] = x;
            top++;
        }
        
        public void pop(){
            Elem[top] = -1;
            top--;
        }
        
        public int top(){
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

/*
    #test.in
42
ONP: xabc**=
ONP: ab+a~a-+
INF: a+b+(~a-a)
INF: x=~~a+b*c
INF: t=~a<x<~b
INF: ( a,+ b)/..[c3
ONP: ( a,b,.).c;-,*
ONP: abc++def++g+++
INF: x=a=b=c
ONP: xabc=== 
INF: a)+(b
ONP: ab+a~a-+
INF: a+b+(~a-a)
INF: x=~~a+b*c
INF: t=~a<x<~b
INF: ~a-~~b<c+d&!p|!!q
INF: a^b*c-d<xp|q+x
INF: x=~a*b/c-d+e%~f
ONP: xabcdefg++++++=
ONP: ab+c+d+e+f+g+
ONP: abc++def++g++
ONP: abc++def++g+++
INF: x=a=b=c
ONP: xabc===
INF: x=a^b^c
INF: x=a=b=c^d^e 
INF: x=(a=(b=c^(d^e)))
ONP: xa~~~~~~=
INF: x=~~~~a
INF: x=~(~(~(~a)))
ONP: xa~~~~=
INF: x=a+(b-c+d)
ONP: xabc-d++=
INF: x=a+(((a-b)+c))
ONP: xaab-c++= 
INF: a~+b 
INF: a~~ 
INF: a+b~ 
INF: ()a+b 
INF: (a+b)+() 
INF: ~()a
ONP: xabcde^^=== 

    #test.out
INF: x=a*(b*c)
INF: a+b+(~a-a)
ONP: ab+a~a-+
ONP: xa~~bc*+=
ONP: ta~x<b~<=
ONP: ab+c/
INF: a*(b-c)
INF: error
ONP: xabc===
INF: x=a=b=c 
ONP: error
INF: a+b+(~a-a)
ONP: ab+a~a-+
ONP: xa~~bc*+=
ONP: ta~x<b~<=
ONP: error
ONP: error
ONP: xa~b*c/d-ef~%+=
INF: x=a+(b+(c+(d+(e+(f+g)))))
INF: a+b+c+d+e+f+g
INF: a+(b+c)+(d+(e+f)+g)
INF: error
ONP: xabc===
INF: x=a=b=c
ONP: xabc^^=
ONP: xabcde^^=== 
ONP: xabcde^^===
INF: x=~~~~~~a
ONP: xa~~~~=
ONP: xa~~~~=
INF: x=~~~~a
ONP: xabc-d++=
INF: x=a+(b-c+d)
ONP: xaab-c++=
INF: x=a+(a-b+c)
ONP: error
ONP: error
ONP: error
ONP: error
ONP: error
ONP: error
INF: x=a=b=c^d^e
*/

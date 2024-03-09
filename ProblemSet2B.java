package ProblemSets;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class ProblemSet2B {

    public static void main(String[] args) {
        System.out.println(isOpenBracket('(')); // return true
        System.out.println(isOpenBracket('a')); // return false
        System.out.println(isCloseBracket(']')); //return true
        System.out.println(isCloseBracket('a')); // return false
        System.out.println(isMatchBracket('(', ')')); // return true
        System.out.println(isMatchBracket('a', 'a')); // return false
        System.out.println(isBalancedBracket("()[]()")); // return true
        System.out.println(isBalancedBracket("([]())")); // return true
    }

    // Question 3: isBalancedBrackets()
    // todo: a static method getBalancedBracket() where it takes in a String that contains
    //      open brackets, close brackets and characters which are letters of alphabets.
    //      Assume the string is max 20 characters.
    //      If the brackets in the string are balanced, return true, else return false.

    // todo: static helper methods
    // isOpenBracket method
    public static boolean isOpenBracket(char c) {
        String brackets = "({[";

        if (brackets.contains(String.valueOf(c))) {
            return true;
        }
        return false;
    }

    // isCloseBracket method
    public static boolean isCloseBracket(char c) {
        String brackets = ")]}";

        if (brackets.contains(String.valueOf(c))) {
            return true;
        }
        return false;
    }

    // isMatchBracket method
    public static boolean isMatchBracket(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }

    // isBalancedBracket method
    public static boolean isBalancedBracket(String string) {
        StackImpl<Character> stack = new StackImpl<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (isOpenBracket(c)) {
                stack.push(c);
            } else if (isCloseBracket(c)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char d = stack.pop();
                if (isMatchBracket(d, c)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return (stack.isEmpty());
    }
}

// Question 1: Fibonacci Series
class Fibonacci {
    // todo: initializing required variables
    public int max;
    public int calls;
    int[] data;

    // constructor
    public Fibonacci(int max) {
        this.max = max;
        data = new int[max];
        data[0] = 0;
        data[1] = 1;
    }

    // getFibonacciNumber method
    public int getFibonacciNumber(int n) {
        // todo: instantiate calls variable to 0. Keep track of the # of times getFibRecursive is called
        calls = 0;
        if (n > max || n == max) {
            return -1;
        }
        return fibRecursive(n);
    }

    // fibRecursive method
    private int fibRecursive(int n) {
        calls += 1;
        if (n == 0 || n == 1) {
            return data[n];
        } else {
            if (data[n] != 0) {
                return data[n];
            }
            data[n] = fibRecursive(n - 1) + fibRecursive(n - 2);
        }
        return data[n];
    }

    // getCalls method
    public int getCalls() {
        return calls;
    }

    // getData method
    public int[] getData() {
        return data;
    }
}

// Question 2: Custom Stack
interface CustomStack<T> {
    void push(T t);

    T pop();

    int size();

    T peek();

    boolean isEmpty();
}
class StackImpl<T> implements CustomStack<T> {
    private List<T> myList = new ArrayList<T>();

    public StackImpl() {}

    // push method
    // todo: adds element t to the top of the stack
    @Override
    public void push(T value) {
        myList.add(value);
    }

    // pop method
    // todo: removes the element at the top of the stack and returns it. If stack is empty return null.
    @Override
    public T pop() {
        if (myList.isEmpty()) {
            return null;
        }
        T result = myList.get(myList.size() - 1);
        myList.remove(myList.size() - 1);
        return result;
    }

    // peek method
    // todo: returns the element at the top of the stack but does not remove it. If stack is empty return null.
    @Override
    public T peek() {
        if (myList.isEmpty()) {
            return null;
        }
        return myList.get(myList.size() - 1);
    }

    // size method
    // todo: return the # of elements in the stack
    @Override
    public int size() {
        return myList.size();
    }

    // isEmpty method
    public boolean isEmpty() {
        return myList.isEmpty();
    }
}

// Question 4: isValidString

abstract class FixExpression {
    private String expression;
    private String validChars = "0123456789+-*/";

    FixExpression(String expression) {
        this.expression = expression;
    }

    public boolean isValidString() {
        for (char c : expression.toCharArray()){
            if (validChars.contains(Character.toString(c))){
                continue;
            }
            return false;
        }
        return true;
    }

    public String getExpression() {
        return expression;
    }

    public String getValidChars() {
        return validChars;
    }

    public abstract int evaluateExpression();
}

class TrivialImplExpression extends FixExpression{
    TrivialImplExpression(String string){
        super(string);
    }

    // todo: provide a trivial implementation of this abstract method
    @Override
    public int evaluateExpression(){
        return 0;
    }
}

class TestingFixExpression{
    public static void main(String[] args) {
        FixExpression fixExpression1 = new TrivialImplExpression("1+2");
        System.out.println(fixExpression1.isValidString()); // true

        FixExpression fixExpression2 = new TrivialImplExpression("abc");
        System.out.println(fixExpression2.isValidString()); // false
    }

}

// Question 5: PostFixExpression
class PostFixExpression extends FixExpression {
    PostFixExpression(String expression){
        super(expression);
    }

    @Override
    public int evaluateExpression(){
        StackImpl<Integer> postFix = new StackImpl<>();

        for (char c : getExpression().toCharArray()){
            if (isOperand(c)) {
                postFix.push(Character.getNumericValue(c));
            } else if (isOperator(c)) {
                int p2 = postFix.pop();
                int p1 = postFix.pop();
                int p3 = getValue(p1, p2, c);
                postFix.push(p3);
            }
        }

        return postFix.pop();
    }

    // isOperator method
    private boolean isOperator(char c){
        if (c == '+' || c == '-' || c == '*' || c == '/'){
            return true;
        }
        return false;
    }

    // isOperand method
    private boolean isOperand(char c){
        if (Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    // getValue method
    private int getValue(int p1, int p2, char c){
        if (c == '+'){
            return p1 + p2;
        } else if (c == '-') {
            return p1 - p2;
        }
        else if (c == '*'){
            return p1 * p2;
        } else if (c == '/') {
            return p1 / p2;
        }
        return 0;
    }
}

class TestPostfixExpression {
    public static void main(String[] args) {
        FixExpression f1 = new PostFixExpression("12+");
        System.out.println(f1.evaluateExpression()); // 3

        FixExpression f2 = new PostFixExpression("234*+");
        System.out.println(f2.evaluateExpression()); // 14

        FixExpression f3 = new PostFixExpression("1");
        System.out.println(f3.evaluateExpression()); // 1
    }
}
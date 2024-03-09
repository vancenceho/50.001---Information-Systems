package ProblemSets;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProblemSet2A {
    public static void main(String[] args) {
        ArrayList<Octagon1> l = new ArrayList<Octagon1>();
        l.add(new Octagon1(2));
        l.add(new Octagon1(3));
        l.add(new Octagon1(1));
        Collections.sort(l, new OctagonComparator());
        for (Octagon1 o : l){
            System.out.println(o.getSide());
        }

        AirPoulltionAlert singaporeAlert = new AirPoulltionAlert();
        Subscriber man = new Subscriber("man", singaporeAlert);
        Subscriber simon = new Subscriber("simon", singaporeAlert);
        singaporeAlert.setAirPollutionIndex(200);
        singaporeAlert.setAirPollutionIndex(50);
        singaporeAlert.setAirPollutionIndex(120);
        singaporeAlert.unregister(man);
        singaporeAlert.setAirPollutionIndex(300);
    }
}

// Question 1: Comparable Interface
// todo: modify octagon class to implement Comparable<Octagon> interface to allow sorting of
//      Octagon objects based on their parameters.
class Octagon implements Comparable<Octagon>{
    private double side;
    public Octagon(double side){
        this.side = side;
    }
    public double getSide(){
        return side;
    }

    @Override
    public int compareTo(Octagon octagon){
        if (side == octagon.side){
            return 0;
        } else if (side > octagon.side) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public static void main(String[] args) {
        ArrayList<Octagon> l = new ArrayList<Octagon>();
        l.add(new Octagon(2));
        l.add(new Octagon(3));
        l.add(new Octagon(1));
        Collections.sort(l);
        for (Octagon o : l){
            System.out.println(o.getSide());
        }
    }
}

// Question 2: Comparator Interface
// todo: given same code & assumptions from previous question
//      implement a OctagonComparator class to implement a Comparable<Octagon> interface to allow
//      sorting of Octagon object based on their parameters.
class Octagon1 {
    private double side;
    public Octagon1 (double side){
        this.side = side;
    }
    public double getSide(){
        return side;
    }
}

class OctagonComparator implements Comparator<Octagon1> {
    @Override
    public int compare(Octagon1 o1, Octagon1 o2){
        Octagon1 obj1 = (Octagon1) o1;
        Octagon1 obj2 = (Octagon1) o2;

        if (obj1.getSide() == obj2.getSide()){
            return 0;
        } else if (obj1.getSide() > obj2.getSide()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

// Question 3: Observer
interface Observer {
    void update(double airPollutionIndex);
}

interface Subject{
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();
}

class Subscriber implements Observer {
    private Subject subject;
    private String observerId;
    public static String outputMsg = "";

    // constructor.
    public Subscriber(String observerId, Subject subject){
        this.subject = subject;
        this.observerId = observerId;
        this.subject.register(this); // register itself
    }

    @Override
    public void update(double airPollutionIndex){
        String s = this.observerId + " received notification: " + airPollutionIndex;
        System.out.println(s);
        outputMsg += (s + " ");
    }
}

// todo: modify AirPollutionAlert to implement interface Subject, under Observer design pattern
class AirPoulltionAlert implements Subject{
    private double airPollutionIndex;
    private List<Observer> observers;

    public AirPoulltionAlert(){
        this.observers = new ArrayList<>();
    }
    public void setAirPollutionIndex(double airPollutionIndex){
        this.airPollutionIndex = airPollutionIndex;
        if (airPollutionIndex > 100){
            notifyObservers();
        }
    }

    @Override
    public void register(Observer o){
        observers.add(o);
    }

    @Override
    public void unregister(Observer o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(){
        for (Observer observer : observers){
            // notify the observers
            observer.update(this.airPollutionIndex);
        }
    }
}

// Question 4
interface I1 {
    int p1();
}

interface I2 {
    int p2();
}

interface I3 {
    int p3();
}

interface I4 extends I1, I2, I3 {
    int p4();
}

interface I5 extends I3 {
    int p5();
}

abstract class C1 implements I4 {
    abstract int q1();
}

class C2 extends C1 implements I5 {
    @Override
    public int p4(){
        return 0;
    }

    @Override
    public int p1(){
        return 0;
    }

    @Override
    public int p2(){
        return 0;
    }

    @Override
    public int p3(){
        return 0;
    }

    @Override
    int q1(){
        return 0;
    }

    @Override
    public int p5(){
        return 0;
    }
}

class C3 implements I5 {
    @Override
    public int p5(){
        return 0;
    }

    @Override
    public int p3(){
        return 0;
    }
}

// Question 5: Palindrome
class Palindrome{

    static boolean checkPalindrome(String str, int i, int j){
        if (i == j){
            return true;
        }
        if ((str.charAt(i)) != (str.charAt(j))){
            return false;
        }
        if (i < j + 1){
            return checkPalindrome(str, i + 1, j -1);
        }
        return true;
    }
    static boolean isPalindrome(String input){
        int n = input.length();
        if(n == 0){
            return true;
        }
        return checkPalindrome(input, 0, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abba")); // true
        System.out.println(isPalindrome("adbcba")); // false
        System.out.println(isPalindrome("ZZaZZ"));  // true
        System.out.println(isPalindrome("123421")); // false
    }
}

// Question 6: Permutation
class Permutation{
    private final String in;
    private ArrayList<String> a = new ArrayList<String>();

    Permutation(final String str){
        in = str;
    }

    public void permute(){
        permuteHelper("", in);
    }

    private void permuteHelper(String prefix, String postfix){
        // todo: produce the permuted sequences of 'in' and store in array a, recursively
        int n = postfix.length();
        // base case
        if (n == 0){
            a.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++){
                permuteHelper(prefix + postfix.charAt(i),
                        postfix.substring(0, i) + postfix.substring(i + 1, n));
            }
        }

    }

    public ArrayList<String> getA() {
        return a;
    }

    public static void main(String[] args) {
        ArrayList<String> v;
        Permutation p = new Permutation("ABC");
        p.permute();
        v = p.getA();
        System.out.println(v);
    }
}

// Question 8: ArrayIndexOutOfBoundsException
class Exception{
    public static String tstException(int idx, String[] y){
        String result;
        // todo: try catch to get ArrayIndexOutOfBounds
        try {
            result = y[idx];
        }
        catch (ArrayIndexOutOfBoundsException e){
            result = "Out of Bounds";
        }
        return result;
    }

    public static void main(String[] args) {
        String[] in = {"hello", "good night", "good morning"};
        String ret = tstException(2, in);
        System.out.println(ret);
        ret = tstException(-1, in);
        System.out.println(ret);
    }
}


// SINGLETON IMPLEMENTATION EXAMPLE
class Connection {
    // make your constructor private to prevent instantiation by constructor
    // prevent inheritance, because super() would not work
    private Connection(){}

    // static factory method (factory -> make objects)
    private static Connection connection;

    // either return connection, or instantiate and return
    public static Connection getInstance(){
        if (connection == null){
            connection = new Connection();
        }
        return connection;
    }
}


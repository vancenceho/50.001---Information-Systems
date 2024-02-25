package week1;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.HashSet;
import java.util.Set;

public class ProblemSet1A {
    public static void main(String[] args) {
        // Cohort Session 1 test cases.
        System.out.println(fibonacciSequence(13));

        // Cohort Session 2 test cases.
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(iterator(list));

        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);
        list1.add(8);
        list1.add(9);
        list1.add(10);
        System.out.println(forIterator(list1));

        // Question 1 test cases.
        int[] testCase1 = {4, 7, 14, 23, 99};
        List<Integer> results = new ArrayList<Integer>();
        for (int testCases : testCase1){
            results.add(isPrime(testCases));
        }
        System.out.println(results);

    }

    // Cohort Session 1.
    public static String fibonacciSequence(int n) {
        StringBuilder result = new StringBuilder();
        int firstTerm = 0;
        int secondTerm = 1;

        for (int i = 0; i < n; i++) {
            result.append(firstTerm);

            if (i < n - 1){
                result.append(",");
            }

            int next = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = next;
        }

        return result.toString();
    }

    // Cohort Session 2
    // Part 1: Iterating using Iterator
    static int iterator(List<Integer> integer) {
        int total = 0;

        ListIterator<Integer> iter = integer.listIterator();
        while (iter.hasNext()) {
            total += iter.next();
        }
        return total;
    }

    // Part 2: For-each Loop
    static int forIterator(List<Integer> integer) {
        int total = 0;

        for (int element : integer) {
            total += element;
        }

        return total;
    }

    // Question 1: Prime Number Checker
    public static int isPrime(int n){
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0){
                return 0;
            }
        }
        return 1;
    }

}

// Cohort Session 3
class Account {

    // ID for the account
    private int id = 0;
    // Stores the balance for the account
    private double balance = 0.0;
    // Stores the current interest rate in %. Assume all accounts have the same interest rate.
    private static double annualInterestRate = 0.0;
    // Stores the date when the account was created.
    private LocalDate dateCreated;

    // constructor with default values.
    public Account() {}

    // constructor with ID and initial balance specified.
    public Account(int ID, double initialBalance) {
        this.id = ID;
        this.balance = initialBalance;
    }

    // getter for id.
    public int getId() {
        return id;
    }

    // setter for id.
    public void setId(int ID) {
        this.id = ID;
    }

    // getter for balance.
    public double getBalance() {
        return balance;
    }

    // setter for balance.
    public void setBalance(double initialBalance) {
        this.balance = initialBalance;
    }

    // getter for annual interest rate.
    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    // setter for annual interest rate.
    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    // getter for date account created.
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    // method to calculate and return the monthly interest rate in %.
    public static double getMonthlyInterestRate() {
        return getAnnualInterestRate() / 12;
    }

    // method to calculate monthly interest in $.
    public double getMonthlyInterest() {
        double interestRate = getMonthlyInterestRate() / 100;
        return getBalance() * interestRate;
    }

    // method to withdraw amount.
    public void withdraw(double amount) {
        setBalance(getBalance() - amount);
    }

    // method to deposit amount.
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public static void main(String[] args) {
        Account account = new Account(1122, 20000);
        Account.setAnnualInterestRate(4.5);

        account.withdraw(2500);
        account.deposit(3000);
        System.out.println("Balance is " + account.getBalance());
        System.out.println("Monthly interest is " + account.getMonthlyInterest());
    }
}

// Question 2: MyRectangle2D Class
class MyRectangle2D {
    // Defining the point (x, y) where the rectangle will be created. Default: (0, 0)
    private double x;
    private double y;
    // getter for x point.
    public double getX(){
        return x;
    }
    // setter for x point.
    public void setX(double x){
        this.x = x;
    }
    // getter for y point.
    public double getY(){
        return y;
    }
    // setter for y point.
    public void setY(double y){
        this.y = y;
    }
    // Defining the width and height of the rectangle. Default: 1.
    private double width;
    private double height;
    // getter for width
    public double getWidth(){
        return width;
    }
    // setter for width.
    public void setWidth(double width){
        this.width = width;
    }
    // getter for height.
    public double getHeight(){
        return height;
    }
    // setter for height.
    public void setHeight(double height){
      this.height = height;
    }

    // constructor for default rectangle
    public MyRectangle2D(){
        this(0.0, 0.0, 1.0, 1.0);
    };

    // constructor with specified values
    public MyRectangle2D(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // method to calculate the area of rectangle.
    public double getArea(){
        return width * height;
    }

    // method to calculate perimeter of rectangle.
    public double getPerimeter(){
        return 2 * (width + height);
    }

    // method to determine if a point is in the rectangle.
    // for the point to be in the rectangle have to be within the bounds of the (x + width) & (y + height).
    public boolean contains(double x, double y){
        return x >= this.x - width / 2 &&
                x <= this.x + width / 2 &&
                y >= this.y - height / 2 &&
                y <= this.y + height + 2;
    }

    // method to check if a rectangle is inside the rectangle.
    public boolean contains(MyRectangle2D r){
        double rX = r.getX();
        double rY = r.getY();
        double rWidth = r.getWidth();
        double rHeight = r.getHeight();

        return (contains(rX - rWidth / 2, rY - rHeight / 2) &&
                contains(rX + rWidth / 2, rY - rHeight / 2) &&
                contains(rX - rWidth / 2, rY + rHeight / 2) &&
                contains(rX + rWidth / 2, rY + rHeight / 2));
    }


    // method to check if a rectangle overlaps.
    public boolean overlaps(MyRectangle2D r){
        return !((x + width / 2) < (r.x - r.width / 2) ||
                (r.x + r.width / 2) < (x - width / 2) ||
                (y + height / 2) < (r.y - r.height / 2) ||
                (r.y + r.height / 2) < (y - height / 2));
    }
}

// Week 2
// Question 1: 2x2 Linear Equations
class LinearEquation{
    // Declaration of private data fields for the coefficients.
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    // constructor with arguments.
    public LinearEquation(double A, double B, double C, double D, double E, double F){
        this.a = A;
        this.b = B;
        this.c = C;
        this.d = D;
        this.e = E;
        this.f = F;
    }

    // getter methods for the 6 coefficients.
    public double getA(){
        return this.a;
    }
    public double getB(){
        return this.b;
    }
    public double getC(){
        return this.c;
    }
    public double getD(){
        return this.d;
    }
    public double getE(){
        return this.e;
    }
    public double getF(){
        return this.f;
    }

    // method: isSolvable to find if the 2x2 linear equation is solvable using the determinant (ad - cb)
    // if determinant == 0 means that the linear equation is unsolvable and therefore return false vice versa.
    public boolean isSolvable(){
        return (getA() * getD()) - (getB() * getC()) != 0;
    }

    // method to get solutions to the equations: getX() & getY().
    public double getX(){
        // using Cramer's Rule to solve for x. D = determinant (ad - cb), dX = (e * d) - (f * b)
        // x = dX / D; by replacing coefficients of x with the constants.
        double determinant = (getA() * getD()) - (getB() * getC());
        double dX = (getE() * getD()) - (getB() * getF());
        return (dX / determinant);
    }
    public double getY(){
        // using Cramer's Rule to solve for y. D = determinant (ad - cb), dY = (a * e) - (c * f)
        // y = dY / D; by replacing coefficients of y with the constants.
        double determinant = (getA() * getD()) - (getB() * getC());
        double dY = (getA() * getF()) - (getE() * getC());
        return (dY / determinant);
    }

    public static void main(String[] args) {
        double a = 1.0;
        double b = 2.0;
        double c = 3.0;
        double d = 5.0;
        double e = 6.0;
        double f = 7.0;

        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);
        if (equation.isSolvable()){
            System.out.println("x is " + equation.getX() + " and y is " + equation.getY());
        }
        else {
            System.out.println("The equation has no solution.");
        }

        LinearEquation equation2 = new LinearEquation(1.25, 2.0, 2.0, 4.2, 3.0, 6.0);
        if (equation2.isSolvable()){
            System.out.println("x is " + equation2.getX() + " and y is " + equation2.getY());
        }

        LinearEquation equation3 = new LinearEquation(1.0, 2.0, 2.0, 4.0, 3.0, 6.0);
        System.out.println(equation3.isSolvable());
    }

}

// Question 2: The Triangle Class
class GeometricObject{
    private String color;
    GeometricObject(){
        this("Green");
    }
    GeometricObject(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getInfo(){
        return ("Geometric Object of color " + this.color);
    }
}

class Triangle extends GeometricObject{
    // Default values of the 3 sides of a triangle.
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    // default constructor.
    public Triangle(){};

    // constructor with specified sides.
    public Triangle(double s1, double s2, double s3){
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
    }

    // method to get the perimeter of the triangle: getPerimeter()
    public double getPerimeter(){
        return (this.side1 + this.side2 + this.side3);
    }

    // method to get the area of the triangle: getArea()
    // using heron's formula: area = sqrt[s(s-a)(s-b)(s-c)] where s = semi-perimeter (a+b+c)/2 of the triangle.
    public double getArea(){
        double s = getPerimeter() / 2;
        return Math.sqrt((s * (s - this.side1) * (s - this.side2) * (s - this.side3)));
    }

    // method to return the description of the triangle.
    public String toString(){
        return ("Triangle: " + "side1 = " + this.side1 + " side2 = " + this.side2 + " side3 = " + this.side3);
    }
}

// Question 3: Subclasses of Account
class CheckingAccount extends Account{
    private double overdraftLimit = 5000.0;
    public CheckingAccount(int ID, double initialBalance){
        super(ID, initialBalance);
    }

    @Override
    public void withdraw(double amount){
        if (getBalance() - amount < -overdraftLimit){
            System.out.println("over limit");
        }
        else{
            super.withdraw(amount);
        }
    }

    public static void main(String[] args) {
        CheckingAccount myCheckAcc = new CheckingAccount(1024, 8000.0);
        myCheckAcc.deposit(2000);
        myCheckAcc.withdraw(15000);

        System.out.println(myCheckAcc.getBalance());
        myCheckAcc.withdraw(200);
        System.out.println(myCheckAcc.getBalance());
        myCheckAcc.deposit(7000);
        myCheckAcc.withdraw(200);
        System.out.println(myCheckAcc.getBalance());

    }
}

// Question 4: String Operation
// Part I: Design and implement a static method to determine if an input string has all unique characters.
// Assume the character set is ACII, encodes 128 characters into 7-bit binary characters.
class Pset1{
    public static boolean isAllCharacterUnique(String stringIn){
        // todo: add implementation
        // using hashset we can check the input string if it is different.

        // Create a new set to insert characters.
        Set<Character> set = new HashSet<>();

        // Get all characters from input string.
        char[] characters = stringIn.toCharArray();

        // loop through the characters and find the duplicate to add them into the set.
        for (Character c : characters){
            if (!set.add(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPermutation(String string1, String string2){
        // todo: implement a method which takes in 2 strings and compare if they are permutations
        // todo: of each other

        // Compare the length first, if length is not the same, straight away return false.
        if (string1.length() != string2.length()){
            return false;
        }

        // If length is the same, we change the string to an array and sort the array.
        char[] array1 = string1.toCharArray();
        char[] array2 = string2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);

        // After we just compare the arrays by using .equals.
        return Arrays.equals(array1, array2);
    }

    public static void main(String[] args){
        String input = "Java";
        String input1 = "Python";
        System.out.println(isAllCharacterUnique(input));   // return false
        System.out.println(isAllCharacterUnique(input1));  // return true

        String string1 = "test";
        String string2 = "ttew";
        String string3 = "ttse";
        System.out.println(isPermutation(string1, string2)); // return false
        System.out.println(isPermutation(string1, string3)); // return true
    }
}



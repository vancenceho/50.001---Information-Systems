package ProblemSets;
import java.lang.Math;

public class ProblemSet1X {

    public static void main(String[] args){
        // Question 1 test cases.
        addTime(0, 20, 0, 30);
        addTime(0, 40, 0, 30);
        addTime(1, 30, 1, 31);
        addTime(6, 30, 4, 45);

        // Question 2a test cases.
        sumUpTo(9);

        // Question 2b test cases.
        int a[] = {-12, 3, 20, 21, -15, 2};
        int b[] = {};
        int c[] = new int[10];
        sumIntArrayAll(a);
        sumIntArrayAll(b);
        sumIntArrayAll(c);

        // Question 2c test cases.
        int d[] = {-12, 3, 20, 21, -15, 22};
        int e[] = {};
        sumIntArrayTwenty(d);
        sumIntArrayTwenty(e);

        // Question 2d test cases
        int f[] = {-12, 3, 0, 21, -15, 2};
        int g[] = {};
        int h[] = {2, 3, 9, 5, 4, 1, 1};
        countEvenNumbers(f);
        countEvenNumbers(g);
        countEvenNumbers(h);

        // Question 3 test cases
        termsRequired(0.9);

        // Question 4 test cases
        binaryToDecimal("1011");
        binaryToDecimal("0");
        binaryToDecimal("1");
        binaryToDecimal("1101");
        binaryToDecimal("1111");
    }

    // Question 1
    public static void addTime(int hour1, int min1, int hour2, int min2){
        int totalMin = min1 + min2;
        int totalHour = hour1 + hour2;

        // if condition if minutes are more than 60.
        // % is used to find the remainder (e.g. 61 % 60 = 1). Therefore it is used to find the
        // remaining minutes.
        if (totalMin >= 60){
            int remainHour = totalMin / 60;
            int remainMin = totalMin % 60;
            int finalHour = totalHour + remainHour;
            System.out.println(finalHour + " hours " + remainMin + " minutes ");
        }
        else if (totalMin < 60) {
            System.out.println(totalHour + " hours " + totalMin + " minutes ");
        }
    }

    // Question 2a
    public static void sumUpTo(int n){
        int result = 0;

        for (int i = 0; i <= n; i++){
            result = result + i;
        }

        System.out.println("Question 1 answer: " + result);
    }

    // Question 2b
    public static void sumIntArrayAll(int[] array){
        int result = 0;

        for (int i = 0; i < array.length; i++){
            result = result + array[i];
        }

        System.out.println("Question 2b answer: " + result);
    }

    // Question 2c
    public static void sumIntArrayTwenty(int[] array){
        int result = 0;

        for (int i = 0; i < array.length; i++){
            if (array[i] > 20){
                result = result + array[i];
            }
        }

        System.out.println("Question 2c answer: " + result);
    }

    // Question 2d
    public static void countEvenNumbers(int[] array){
        int result = 0;

        for (int i = 0; i < array.length; i++){
            if (array[i] % 2 == 0) {
                result += 1;
            }
        }

        System.out.println("Question 2d answer: " + result);
    }

    // Question 3
    public static void termsRequired(double p){

        double fraction = (p * (Math.PI * Math.PI) / 6);
        int n = 1;
        int count = 0;
        double sum = 0.0;

        while (sum < fraction){
            sum = sum + (1 / (Math.pow(n, 2)));
            n++;
            count++;
        }

        System.out.println("Question 3 Number of Terms Required: " + count);
    }

    // Question 4
    public static char getCharFromString(String str, int index){
        return str.toCharArray()[index];
    }
    public static void binaryToDecimal(String s){
        int total = 0;
        int sLength = s.length();

        // loop through the string, determine the i position, then
        // determine if its a 1 or 0, then
        // calculate the equivalent value and add it to the total.
        // binary = 1011
        for (int i = 0; i <= sLength - 1; i++){
            char digit = getCharFromString(s, i);
            String binary = String.valueOf(digit);

            if (binary.equals("1")){
                int decimal = (int) Math.pow(2, sLength - 1 - i);
                total = total + decimal;
            }
        }

        // print result
        System.out.println("Question 4 answer: " + total);
    }

}


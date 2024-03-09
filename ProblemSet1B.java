package ProblemSets;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSet1B {
    public static void main(String[] args) {
        Person p1 = new Person("A", 90, 'F', false);
        Person p2 = new Person("B", 60, 'M', true);
        Person p3 = new Person("C", 30, 'F', true);
        Person[] p = {p1, p2, p3};
        System.out.println(Filter.seniorFilter(p));

        Animal[] animals = {new Dog(), new Cat(), new Cow(), new SiberianCat()};
        System.out.println(AnimalConcert.performConcert(animals));

        String msg = "red is sus";
        Browser b = new Browser();
        Server s = new Server();
        s.setP(97);
        s.setQ(53);
        b.establishConnection(s);

        BigInteger[] encryptedMsg = b.encryptMessage(msg);
        String decryptedMsg = s.decryptMessage(encryptedMsg);

        System.out.println(Arrays.toString(s.getPublicKey()));
        System.out.println(Arrays.toString(s.getPrivateKey()));
        System.out.println(Arrays.toString(encryptedMsg));
        System.out.println(decryptedMsg);
    }
}

// Question 1: Encapsulation
class Person{
    // attributes
    private String name;
    private int age;
    private char gender;
    private boolean sharingConsent;

    // constructor with arguments
    Person(String name, int age, char gender, boolean sharingConsent){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.sharingConsent = sharingConsent;
    }

    // getter for age
    public int getAge(){
        return age;
    }
    // setter for age
    public void setAge(int age){
        this.age = age;
    }
    // getter for name
    public String getName(){
        if (isSharingConsent()){
            return name;
        }
        return "Anonymous";
    }
    // getter for sharingConsent
    public boolean isSharingConsent(){
        return sharingConsent;
    }
}

class Filter{
    static ArrayList<String> seniorFilter(Person[] array){
        ArrayList<String> result = new ArrayList<>();
        for (Person person : array){
            if (person.getAge() >= 60){
                result.add(person.getName());
            }
        }

        return result;
    }
}

// Question 2: Polymorphism
class Animal{
    public String makeSound(){
        return "I am just an animal";
    }
}

class Dog extends Animal{
    @Override
    public String makeSound(){
        return "Woof";
    }
}

class Cat extends Animal{
    @Override
    public String makeSound(){
        return "Meow";
    }
}

class Cow extends Animal{
    @Override
    public String makeSound(){
        return "Moo";
    }
}

class SiberianCat extends Cat{}

class AnimalConcert{
    static String performConcert(Animal[] animals){
        StringBuilder result = new StringBuilder();

        for (Animal animal : animals){
            result.append(animal.makeSound() + ", ");

        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}

// Question 3: Base Integer
class BaseInteger{
    // constructor that takes in 2 inputs
    // representation - a string with digits separated by commas and no spaces "22,43,5"
    private String representation;
    private int base;
    private int[] digits;
    private int decimalValue;
    BaseInteger(String representation, int base){
        this.representation = representation;
        this.base = base;
    }

    private void convertRepresentationToArray(){
        ArrayList<Integer> dList = new ArrayList<Integer>();

        for (String elem : this.representation.split(",")){
            dList.add(Integer.parseInt(elem));
        }
        this.digits = dList.stream().mapToInt(i -> i).toArray();
    }

    private void setDecimalValue(){
        for (int i = 0; i < this.digits.length; i++){
            this.decimalValue += this.digits[i] * (Math.pow(this.base, this.digits.length - i - 1));
        }
    }

    public int getDecimalValue(){
        return this.decimalValue;
    }

    public String getDigitsString(){
        String output = "[";
        for (int i = 0; i < this.digits.length; i++){
            output += String.valueOf(this.digits[i]);
            if (i != this.digits.length - 1){
                output += ",";
            }
            else {
                output += "]";
            }
        }
        return output;
    }

    public BaseInteger add(BaseInteger other, int base){
        int sum = other.getDecimalValue() + this.getDecimalValue();
        return new BaseInteger(convertBase(sum, base), base);
    }

    private String convertBase(int decimalValue, int base){
        String repr = "";
        int baseInc = 1;
        while (decimalValue > 0){
            Integer curr = (decimalValue % (baseInc * base)) / baseInc;
            repr = curr.toString() + "," + repr;
            decimalValue -= curr * baseInc;
            baseInc *= base;
        }
        return repr.substring(0, repr.length() - 1);
    }

    private String deleteSpaces(String representation){
        return representation.replaceAll(" ","");
    }

    @Override
    public String toString(){
        return representation + " Base " + base;
    }

}

// Question 4: RSA Algorithm
class Server{
    private final int[] publicKey = new int[2];
    private final int[] privateKey = new int[2];
    private int p;
    private int q;

    // setter for P
    public void setP(int p){
        this.p = p;
    }
    // setter for q
    public void setQ(int q){
        this.q = q;
    }
    // getter for public key
    public int[] getPublicKey(){
        return publicKey;
    }
    // getter for private key
    public int[] getPrivateKey(){
        return privateKey;
    }
    // computeModInverse method
    private int computeModInverse(int e, int lambda){
        for (int d = 1; d < lambda; d++){
            if (((e % lambda) * (d % lambda)) % lambda == 1){
                return d;
            }
        }
        return 1;
    }
    // computeE method
    private int computeE(int lambda){
        for (int i = lambda - 1; i > 2; i--){
            if (lambda % i != 0 && isPrime(i)){
                return i;
            }
        }
        return 0;
    }
    // isPrime method
    private boolean isPrime(int a){
        for (int i = 2; i < a / 2; i++){
            if (a % i == 0){
                return false;
            }
        }
        return true;
    }
    // generatePublicKey method
    public void generatePublicPrivateKey(){
        // todo: compute modulus --> n = pq
        int n = p * q;
        // todo: compute lambda
        int lambda = lcm(p - 1, q -1);
        // todo: compute e
        int e = computeE(lambda);
        // todo: compute d
        int d = computeModInverse(e, lambda);
        // todo: set (n, e) as public key
        publicKey[0] = n;
        publicKey[1] = e;
        // todo: set (n, d) as private key
        privateKey[0] = n;
        privateKey[1] = d;
    }

    // decryptMessage method --> decryption by server for character m = c^d % n
    // where (n, d) are values of private key and c is the encrypted character in integer
    public String decryptMessage(BigInteger[] encryptedIntMessage){
        BigInteger[] decryptedIntMessage = new BigInteger[encryptedIntMessage.length];
        StringBuilder builder = new StringBuilder();

        // todo: decrypt each character of the message. HINT: use .modPow from BigInteger
        for (int i = 0; i < encryptedIntMessage.length; i++){
            decryptedIntMessage[i] = encryptedIntMessage[i].modPow(BigInteger.valueOf(privateKey[1]), BigInteger.valueOf(privateKey[0]));
        }
        // todo: decrypted character is an ASCII value (integer). Convert to char
        for (BigInteger element : decryptedIntMessage){
            char c = (char) element.intValue();
            builder.append(c);

        }
        // todo: concatenate characters into string
        // todo: return decrypted string message
        return builder.toString();

    }

    // least common multiple method: LCM --> |ab| / gcd(a,b)
    public static int lcm(int n1, int n2){
       int absNum = Math.abs(n1 * n2);
       int den = gcd(n1, n2);
       return absNum / den;
    }

    // greatest common divisor method: GCD --> using Euclids Algorithm
    public static int gcd(int a, int b){
        if (b == 0){
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(lcm(12, 18));
        System.out.println(gcd(2, 6));
    }
}

class Browser {
    private final int[] publicKey = new int[2];

    public void establishConnection(Server s){
        // todo: generating a public and private key by the server
        s.generatePublicPrivateKey();
        // todo: get the public key from the server and use it to set Browser object's public key
        publicKey[0] = s.getPublicKey()[0];
        publicKey[1] = s.getPublicKey()[1];
    }

    public BigInteger[] encryptMessage(String message){
        BigInteger[] encryptedIntMessage = new BigInteger[message.length()];

        // todo: loop through each character of the message, convert to its ASCII value in integer
        // todo: encrypt the value, using .modPow method from BigInteger
        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            BigInteger character = BigInteger.valueOf((int) c);
            BigInteger encryptedCharacter = character.modPow(BigInteger.valueOf(publicKey[1]), BigInteger.valueOf(publicKey[0]));
            encryptedIntMessage[i] = encryptedCharacter;
        }
        return encryptedIntMessage;
    }
}
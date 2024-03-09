package ProblemSets;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProblemSet3A {
    public static void main(String[] args) {
        Cat1 neko = new Cat1("Neko");
        Dog1 fido = new Dog1("Fido");
        Cow1 gyudon = new Cow1("Gyudon");

        System.out.println(neko.getName());
        System.out.println(fido.getName());
        System.out.println(gyudon.getName());

        System.out.println(neko.makeSound());
        System.out.println(fido.makeSound());
        System.out.println(gyudon.makeSound());
    }

}

abstract class Animal1 {
    private String name;

    Animal1(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract String makeSound();

    // TODO 1 : add toString()
    @Override
    public String toString(){
        String name = getName();
        return name;
    }

    // TODO 2 : add equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal1 animal = (Animal1) o;
        return Objects.equals(name, animal.name);
    }

    // TODO 3 : add hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// TODO 4 : Create 3 concrete classes Cat, Cow & Dog
class Cat1 extends Animal1 {
    public Cat1(String name){
        super(name);
    }

    @Override
    public String getName(){
        return "Cat:" + super.getName();
    }

    @Override
    public String makeSound(){
        return getName() + " says Meow";
    }

}

class Cow1 extends Animal1 {
    public Cow1(String name){
        super(name);
    }

    @Override
    public String getName() {
        return "Cow:" + super.getName();
    }

    @Override
    public String makeSound(){
        return getName() + " says Moo Moo";
    }
}

class Dog1 extends Animal1 {
    public Dog1(String name){
        super(name);
    }

    @Override
    public String getName() {
        return "Dog:" + super.getName();
    }

    @Override
    public String makeSound(){
        return getName() + " says Woof";
    }
}

// Requirement 2
interface AnimalFactory {
    Animal1 createAnimal(String type, String name);
}

// TODO 5 : Create a class FarmFactory which implements AnimalFactory
class FarmFactory implements AnimalFactory {
    @Override
    public Animal1 createAnimal(String type, String name) {
        if (type.equals("Cat")) {
            return new Cat1(name);
        }
        else if (type.equals("Dog")) {
            return new Dog1(name);
        }
        else if (type.equals("Cow")) {
            return new Cow1(name);
        }
        return null;
    }
}

// Requirement 3
class Zoo {
    private AnimalFactory animalFactory;
    private List<Animal1> animal1List;

    Zoo(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
        animal1List = new ArrayList<>();
    }

    // TODO 7 : Invoke the createAnimal method on the abstract animalFactory instance variable
    //  which returns an Animal object based on inputs type and name
    public void addAnimal(String type, String name){
        Animal1 animal1 = animalFactory.createAnimal(type, name);

        if (animal1 != null) {
            animal1List.add(animal1);
        }
    }

    public String performConcert(){
        StringBuilder result = new StringBuilder();
        for (Animal1 animal1 : animal1List) {
            result.append(animal1.makeSound()).append("*");
        }

        return result.toString();
    }

    // TODO 6 : Override the toString() to return the toString() of animalList
    @Override
    public String toString(){
        return animal1List.toString();
    }
}

// Test Case for Zoo.
class TestZoo {
    public static void main(String[] args) {
        Animal1 cat1 = new Cat1("ichika");
        System.out.println(cat1.getName()); // Cat:ichika
        System.out.println(cat1.makeSound()); // Cat:ichika says Meow

        Animal1 cat2 = new Cat1("nino");
        Animal1 cat3 = new Cat1("ichika");
        System.out.println(cat1.equals(cat3)); // true
        System.out.println(cat2.equals(cat3)); // false

        Animal1 dog1 = new Dog1("Fido");
        System.out.println(dog1.getName()); // Dog:Fido
        System.out.println(dog1.makeSound()); // Dog:Fido says Woof

        AnimalFactory animalFactory = new FarmFactory();
        Animal1 animal1 = animalFactory.createAnimal("Cat", "miku");
        Animal1 animal2 = animalFactory.createAnimal("Dog", "hachiko");
        System.out.println(animal1.getName()); // Cat:miku
        System.out.println(animal2.getName()); // Dog:hachiko

        Zoo zoo = new Zoo(new FarmFactory());
        System.out.println(zoo); // []
        zoo.addAnimal("Cat", "Kuroneko");
        zoo.addAnimal("Dog", "Bond");
        zoo.addAnimal("Cow", "Gyudon");
        System.out.println(zoo.performConcert()); // Cat:Kuoneko says Meow*Dog:Bond says Woof*Cow:Gyudon says Moo Moo*
        System.out.println(zoo);
    }
}
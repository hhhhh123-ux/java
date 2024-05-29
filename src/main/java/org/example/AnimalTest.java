package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalTest {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        List<Dog> dogs = new ArrayList<Dog>();
        Dog dog = new Dog();
        dog.setName("dog");
        dogs.add(dog);
        animals.addAll(dogs);
        List<Dog> filteredDogs = animals.stream()
                .filter(animal -> animal instanceof Dog)
                .map(animal -> (Dog) animal)
                .collect(Collectors.toList());
        System.out.println(filteredDogs);
    }
}

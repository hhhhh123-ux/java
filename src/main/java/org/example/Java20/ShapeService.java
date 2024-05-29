package org.example.Java20;

public interface ShapeService {
    record Circle(double radius) implements ShapeService {
    }

    record Square(double side) implements ShapeService {
    }

    record Rectangle(double length, double width) implements ShapeService {
    }

}

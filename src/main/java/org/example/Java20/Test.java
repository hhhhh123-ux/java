package org.example.Java20;


public class Test {
    public static void main(String[] args) {
        Shape circle = new Shape("Circle", 10);
        if (circle instanceof Shape shape) {
            System.out.println("Area of " + shape.type() + " is : " + Math.PI * Math.pow(shape.unit(), 2));
        }

        if (circle instanceof Shape(String type, long unit)) {
            System.out.println("Area of " + type + " is : " + Math.PI * Math.pow(unit, 2));
        }

        ShapeService shape = new ShapeService.Circle(10);
        switch (shape) {
            case ShapeService.Circle c:
                System.out.println("The shape is Circle with area: " + Math.PI * c.radius() * c.radius());
                break;

            case ShapeService.Square s:
                System.out.println("The shape is Square with area: " + s.side() * s.side());
                break;

            case ShapeService.Rectangle r:
                System.out.println("The shape is Rectangle with area: + " + r.length() * r.width());
                break;

            default:
                System.out.println("Unknown Shape");
                break;
        }

        switch(shape) {
            case ShapeService.Circle (double radius):
                System.out.println("The shape is Circle with area: " + Math.PI * radius * radius);
                break;

            case ShapeService.Square(double side):
                System.out.println("The shape is Square with area: " + side * side);
                break;

            case ShapeService.Rectangle(double length, double width):
                System.out.println("The shape is Rectangle with area: + " + length * width);
                break;

            default:
                System.out.println("Unknown Shape");
                break;
        }


    }
}

package xyz.hemangkumar.recyclerview;

import java.util.ArrayList;

/**
 * Created by Hemang on 03/09/16.
 */
public class Car {
    String name;
    int year;

    private static int CarCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Car(String name, int year) {
        CarCount++;
        this.name = name;
        this.year = year;
    }

    public static ArrayList<Car> createCarList(int num){
        ArrayList<Car> cars = new ArrayList<Car>();

        for (int i = 1; i <= num; i++) {
            cars.add(new Car("Car " + CarCount, CarCount/2));
        }

        return cars;
    }
}

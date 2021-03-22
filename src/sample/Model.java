package sample;

/*
        A = Solar Panel Cost (10$per sq ft)
        B = Square Footage ~500
        C = Production cost (constant 1mil per)
        D = Fuel Cost(1.65$ per gallon 3ton 770g/t)
        E = Launch Cost (Constant 20mil)
*/


import java.util.Scanner;


public class Model {

    private double costPerSqFootage, sqFootage, fuelTons, productionCost, satelliteNumber;

    public Model(double costPerSqFootage, double sqFootage, double fuelTons, double productionCost, int satelliteNumber) {
        this.costPerSqFootage = costPerSqFootage;
        this.sqFootage = sqFootage;
        this.fuelTons = fuelTons;
        this.productionCost = productionCost;
        this.satelliteNumber = satelliteNumber;
    }

    public double getSolarPanelCost(){
        return costPerSqFootage * sqFootage;

    }

    public double getFuelCost() {
        return fuelTons * 1270.5;
    }

    public double getProductionCost() {
        return 1000000 * productionCost;
    }

    public double getSatelliteNumber() {
        return satelliteNumber;
    }




}
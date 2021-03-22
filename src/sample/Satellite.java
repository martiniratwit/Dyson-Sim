package sample;


public class Satellite {

    private double costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost;
    
    public Satellite(double costPerSqFootage, double sqFootage, double fuelPrice, double fuelTons, double productionCost, double launchCost)
    {
        this.costPerSqFootage = costPerSqFootage;
        this.sqFootage = sqFootage;
        this.fuelPrice = fuelPrice;
        this.fuelTons = fuelTons;
        this.productionCost = productionCost;
        this.launchCost = launchCost;
    }

}

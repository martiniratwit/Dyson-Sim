package sample;

import javafx.scene.shape.Box;

public class Satellite {

    private double costPerSqFootage, sqFootage, fuelCost, fuelTons, productionCost, launchCost;
    private Box box;
    
    public Satellite(double costPerSqFootage, double sqFootage, double fuelCost, double fuelTons, double productionCost, double launchCost, Box box)
    {
        this.costPerSqFootage = costPerSqFootage;
        this.sqFootage = sqFootage;
        this.fuelCost = fuelCost;
        this.fuelTons = fuelTons;
        this.productionCost = productionCost;
        this.launchCost = launchCost;
        this.box = box;
    }
    
    public double getCostPerSqFootage() 
    {
    	return this.costPerSqFootage;
    }

    public double getSqFootage() 
    {
    	return this.sqFootage;
    }
    
    public double getFuelCost() 
    {
    	return this.fuelCost;
    }
    
    public double getFuelTons() 
    {
    	return this.fuelTons;
    }
    
    public double getProductionCost()
    {
    	return this.productionCost;
    }
    
    public double getLaunchCost() 
    {
    	return this.launchCost;
    }
    
    public Box getBox()
    {
    	return this.box;
    }
    
    public double getTotalCost()
    {
    	return this.costPerSqFootage * this.sqFootage + this.fuelCost * this.fuelTons + this.productionCost + this.launchCost;
    }
}